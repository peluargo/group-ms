package app.peluargo.group.api.dtos;

import java.util.Set;
import java.util.UUID;

public record GroupCreationDTO(
        UUID id,
        String name,
        String description,
        Set<UUID> userIdList,
        UUID createdBy
) {
}
