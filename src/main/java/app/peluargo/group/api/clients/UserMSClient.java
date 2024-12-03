package app.peluargo.group.api.clients;

import app.peluargo.group.api.clients.dtos.GetAllGroupMemberDetailsRequestDTO;
import app.peluargo.group.api.commons.configurations.FeignClientConfiguration;
import app.peluargo.group.api.dtos.UserDetailsDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.UUID;

@FeignClient(
        name = "user-ms",
        configuration = FeignClientConfiguration.class
)
public interface UserMSClient {
    @RequestMapping(method = RequestMethod.GET, value = "/api/users/{id}")
    ResponseEntity<UserDetailsDTO> searchOneUser(@PathVariable("id") UUID id);

    @RequestMapping(method = RequestMethod.GET, value = "/api/users")
    ResponseEntity<Page<UserDetailsDTO>> searchAllInList(@SpringQueryMap GetAllGroupMemberDetailsRequestDTO requestDTO);
}
