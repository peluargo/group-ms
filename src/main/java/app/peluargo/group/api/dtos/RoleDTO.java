package app.peluargo.group.api.dtos;

import java.util.UUID;

public record RoleDTO(
        UUID id,
        String name,
        String description
) {
}
