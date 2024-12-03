package app.peluargo.group.api.dtos;

import java.util.Set;
import java.util.UUID;

public record MemberDTO(
        UUID id,
        Set<RoleDTO> roles
) {
}
