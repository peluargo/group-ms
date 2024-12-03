package app.peluargo.group.api.dtos;

import java.util.List;
import java.util.UUID;

public record GroupDetailsDTO(
        UUID id,
        String name,
        String description,
        int quantityOfMembers,
        List<UserDetailsDTO> members,
        UserDetailsDTO createdBy
) {
}
