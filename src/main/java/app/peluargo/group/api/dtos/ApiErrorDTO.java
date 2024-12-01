package app.peluargo.group.api.dtos;

import lombok.Data;

import java.time.Instant;

@Data
public class ApiErrorDTO {
    private final String type;
    private final String message;
    private final Instant timestamp;

    public ApiErrorDTO(Exception exception) {
        this.type = exception.getClass().getSimpleName();
        this.message = exception.getMessage();
        this.timestamp = Instant.now();
    }
}
