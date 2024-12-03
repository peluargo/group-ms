package app.peluargo.group.api.mappers;

import app.peluargo.group.api.dtos.*;
import app.peluargo.group.api.entities.Member;
import app.peluargo.group.api.entities.Role;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class RoleMapper {
    public static Role toRole(RoleCreationDTO roleCreationDTO) {
        return Role.builder()
                .name(roleCreationDTO.name())
                .description(roleCreationDTO.description())
                .build();
    }

    public static Role toRole(RoleUpdateDTO roleUpdateDTO) {
        return Role.builder()
                .name(roleUpdateDTO.name())
                .description(roleUpdateDTO.description())
                .build();
    }

    public static Set<Role> toRoles(Set<UUID> roleIds) {
        return roleIds.stream().map(
                roleId -> Role.builder()
                        .id(roleId)
                        .build()
        ).collect(Collectors.toSet());
    }

    public static RoleDTO toRoleDTO(Role role) {
        return new RoleDTO(
                role.getId(),
                role.getName(),
                role.getDescription()
        );
    }
}
