package app.peluargo.group.api.services;

import app.peluargo.group.api.dtos.*;
import app.peluargo.group.api.entities.Group;
import app.peluargo.group.api.exceptions.GroupNotFoundException;
import app.peluargo.group.api.exceptions.InvalidUserIdException;
import app.peluargo.group.api.mappers.GroupMapper;
import app.peluargo.group.api.repositories.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class GroupService {
    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private MemberService memberService;

    public GroupDTO create(GroupCreationDTO groupCreationDTO) {
        if (this.memberService.userIdIsInvalid(groupCreationDTO.createdBy())) {
            throw new InvalidUserIdException("Creator is not a valid user");
        }

        Group createdGroup = this.groupRepository.save(GroupMapper.toGroup(groupCreationDTO));

        return GroupMapper.toGroupDTO(createdGroup);
    }

    public Page<GroupDTO> searchAll(Pageable pageable) {
        return this.groupRepository.findAll(pageable).map(GroupMapper::toGroupDTO);
    }

    public GroupDetailsDTO searchOne(UUID id) {
        Group group = this.groupRepository.findById(id).orElseThrow(GroupNotFoundException::new);

        // in order to get the detailed information about the members, we will need to create a new request to the
        // user microservice passing all the members ids as a list
        List<UUID> membersIdList = new ArrayList<>(group.getMembers().stream().map(
                member -> member.getId().getUserId()).toList()
        );

        // since we will be searching for the details of all the group members AND group creator, we need to verify
        // if the creator is also a member of the group, so we can fetch his information using the same request
        UUID creatorId = group.getCreatedBy();
        if (!membersIdList.contains(creatorId)) {
            membersIdList.add(creatorId);
        }

        // now we call the user microservice passing the id list
        Page<UserDetailsDTO> pageOfMembers = this.memberService.getUserDetailsList(membersIdList);

        List<UserDetailsDTO> members = pageOfMembers.getContent();

        // here we retrieve the creator information
        UserDetailsDTO creator = members
                .stream()
                .filter(member -> creatorId.equals(member.id()))
                .findFirst()
                .orElseThrow();

        // since the same request was used to retrieve both members and creator details now we need to remove the
        // creator from the members list if he is not a member
        members = members.stream().filter(member -> !creatorId.equals(member.id())).toList();

        return GroupMapper.toGroupDetailsDTO(group, members, creator);
    }

    public GroupDTO update(UUID id, GroupUpdateDTO groupUpdateDTO) {
        Group group = this.groupRepository.findById(id).orElseThrow(GroupNotFoundException::new);

        if (groupUpdateDTO.name() != null) {
            group.setName(groupUpdateDTO.name());
        }

        if (groupUpdateDTO.description() != null) {
            group.setDescription(groupUpdateDTO.description());
        }

        Group updatedGroup = this.groupRepository.save(group);

        return GroupMapper.toGroupDTO(updatedGroup);
    }

    public void delete(UUID id) {
        this.groupRepository.deleteById(id);
    }
}
