package app.peluargo.group.api.dtos;

import java.util.UUID;

public record GroupUpdateDTO(
        String name,
        String description,
        UUID createdBy
) {
}
