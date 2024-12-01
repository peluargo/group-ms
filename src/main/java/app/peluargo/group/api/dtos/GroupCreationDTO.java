package app.peluargo.group.api.dtos;

import java.util.UUID;

public record GroupCreationDTO(
        UUID id,
        String name,
        String description,
        UUID createdBy
) {
}
