package app.peluargo.group.api.dtos;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

public record MemberWithUserDetailsDTO(
        UUID id,
        String firstName,
        String lastName,
        LocalDate birthdate,
        String email,
        Set<RoleDTO> roles
) {
}
