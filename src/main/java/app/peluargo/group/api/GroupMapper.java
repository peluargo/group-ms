package app.peluargo.group.api;

import app.peluargo.group.api.dtos.GroupCreationDTO;
import app.peluargo.group.api.dtos.GroupDTO;
import app.peluargo.group.api.dtos.GroupUpdateDTO;

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
                group.getCreatedBy()
        );
    }
}
