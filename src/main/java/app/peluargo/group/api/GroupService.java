package app.peluargo.group.api;

import app.peluargo.group.api.dtos.GroupCreationDTO;
import app.peluargo.group.api.dtos.GroupDTO;
import app.peluargo.group.api.dtos.GroupUpdateDTO;
import app.peluargo.group.api.exceptions.GroupNotFoundException;
import app.peluargo.group.api.exceptions.InvalidUserIdException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GroupService {
    @Autowired
    private GroupRepository groupRepository;

    public GroupDTO create(GroupCreationDTO groupCreationDTO) {
        if (!this.isUserIdValid(groupCreationDTO.createdBy())) {
            throw new InvalidUserIdException("Creator is not a valid user");
        }

        Group createdGroup = this.groupRepository.save(GroupMapper.toGroup(groupCreationDTO));

        return GroupMapper.toGroupDTO(createdGroup);
    }

    public Page<GroupDTO> searchAll(Pageable pageable) {
        return this.groupRepository.findAll(pageable).map(GroupMapper::toGroupDTO);
    }

    public GroupDTO searchOne(UUID id) {
        Group Group = this.groupRepository.findById(id).orElseThrow(GroupNotFoundException::new);
        return GroupMapper.toGroupDTO(Group);
    }

    public GroupDTO update(UUID id, GroupUpdateDTO groupUpdateDTO) {
        Group Group = this.groupRepository.findById(id).orElseThrow(GroupNotFoundException::new);

        if (groupUpdateDTO.name() != null) {
            Group.setName(groupUpdateDTO.name());
        }

        if (groupUpdateDTO.description() != null) {
            Group.setDescription(groupUpdateDTO.description());
        }

        Group updatedGroup = this.groupRepository.save(Group);

        return GroupMapper.toGroupDTO(updatedGroup);
    }

    public void delete(UUID id) {
        this.groupRepository.deleteById(id);
    }

    public boolean isUserIdValid(UUID id) {
        return true;
    }
}
