package app.peluargo.group.api.dtos;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public record MemberCreationDTO(
        List<UUID> memberIdList
) {
}
