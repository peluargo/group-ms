package app.peluargo.group.api.commons.dtos;

import lombok.Data;

@Data
public class ApiErrorResponseDTO {
    private final ApiErrorDTO error;

    public ApiErrorResponseDTO(Exception exception) {
        this.error = new ApiErrorDTO(exception);
    }
}
