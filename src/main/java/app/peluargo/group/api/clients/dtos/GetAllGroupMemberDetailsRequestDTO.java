package app.peluargo.group.api.clients.dtos;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetAllGroupMemberDetailsRequestDTO {
    private List<UUID> ids;
    private int size;

    public GetAllGroupMemberDetailsRequestDTO(List<UUID> ids) {
        this.ids = ids;
        this.size = ids.size();
    }
}
