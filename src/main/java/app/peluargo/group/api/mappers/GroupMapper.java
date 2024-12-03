package app.peluargo.group.api.mappers;

import app.peluargo.group.api.dtos.*;
import app.peluargo.group.api.entities.Group;

import java.util.List;

public class GroupMapper {
    public static Group toGroup(GroupCreationDTO groupCreationDTO) {
        return Group.builder()
                .name(groupCreationDTO.name())
                .description(groupCreationDTO.description())
                .createdBy(groupCreationDTO.createdBy())
                .build();
    }

    public static Group toGroup(GroupUpdateDTO groupUpdateDTO) {
        return Group.builder()
                .name(groupUpdateDTO.name())
                .description(groupUpdateDTO.description())
                .createdBy(groupUpdateDTO.createdBy())
                .build();
    }

    public static GroupDTO toGroupDTO(Group group) {
        return new GroupDTO(
                group.getId(),
                group.getName(),
                group.getDescription(),
                group.getQuantityOfMembers()
        );
    }

    public static GroupDetailsDTO toGroupDetailsDTO(
            Group group,
            List<UserDetailsDTO> members,
            UserDetailsDTO creator
    ) {
        return new GroupDetailsDTO(
                group.getId(),
                group.getName(),
                group.getDescription(),
                group.getQuantityOfMembers(),
                members,
                creator
        );
    }
}
