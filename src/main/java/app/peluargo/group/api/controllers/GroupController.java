package app.peluargo.group.api.controllers;

import app.peluargo.group.api.dtos.GroupDetailsDTO;
import app.peluargo.group.api.services.GroupService;
import app.peluargo.group.api.dtos.GroupCreationDTO;
import app.peluargo.group.api.dtos.GroupDTO;
import app.peluargo.group.api.dtos.GroupUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/groups")
public class GroupController {
    @Autowired
    private GroupService groupService;

    @Transactional
    @PostMapping
    public ResponseEntity<GroupDTO> create(@RequestBody GroupCreationDTO groupCreationDTO) {
        GroupDTO groupDTO = this.groupService.create(groupCreationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(groupDTO);
    }

    @GetMapping
    public ResponseEntity<Page<GroupDTO>> searchAll(Pageable pageable) {
        Page<GroupDTO> groups = this.groupService.searchAll(pageable);
        return ResponseEntity.ok(groups);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupDetailsDTO> searchOne(@PathVariable("id") UUID id) {
        GroupDetailsDTO groupDetailsDTO = this.groupService.searchOne(id);
        return ResponseEntity.ok(groupDetailsDTO);
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<GroupDTO> update(@PathVariable("id") UUID id, @RequestBody GroupUpdateDTO groupUpdateDTO) {
        GroupDTO groupDTO = this.groupService.update(id, groupUpdateDTO);
        return ResponseEntity.ok(groupDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        this.groupService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
