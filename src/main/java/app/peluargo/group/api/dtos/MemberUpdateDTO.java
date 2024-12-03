package app.peluargo.group.api.dtos;

import java.util.Set;
import java.util.UUID;

public record MemberUpdateDTO(
        Set<UUID> roles
) {
}
