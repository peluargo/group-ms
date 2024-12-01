package app.peluargo.group.api.dtos;

import java.util.UUID;

public record GroupDTO(
        UUID id,
        String name,
        String description,
        UUID createdBy
) {
}
