package app.peluargo.group.api.dtos;

import java.time.LocalDate;
import java.util.UUID;

public record UserDetailsDTO(
        UUID id,
        String firstName,
        String lastName,
        LocalDate birthdate,
        String email
) {
}
