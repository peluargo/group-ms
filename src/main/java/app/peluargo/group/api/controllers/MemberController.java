package app.peluargo.group.api.controllers;

import app.peluargo.group.api.dtos.MemberCreationDTO;
import app.peluargo.group.api.dtos.MemberWithUserDetailsDTO;
import app.peluargo.group.api.dtos.UserDetailsDTO;
import app.peluargo.group.api.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/groups/{groupId}/members")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @PostMapping
    private ResponseEntity<List<MemberWithUserDetailsDTO>> createMultiple(
            @PathVariable("groupId") UUID groupId,
            @RequestBody MemberCreationDTO memberCreationDTO
    ) {
        List<MemberWithUserDetailsDTO> members = this.memberService.createMultipleMembers(groupId, memberCreationDTOList);
    }

//    @GetMapping
//    public ResponseEntity<MemberWithUserDetailsDTO> searchAll(
//            @PathVariable("groupId") UUID groupId
//    ) {
//        MemberWithUserDetailsDTO memberWithUserDetailsDTO = this.memberService.searchAll(groupId);
//        return ResponseEntity.ok(memberWithUserDetailsDTO);
//    }

    @GetMapping("/{memberId}")
    public ResponseEntity<MemberWithUserDetailsDTO> searchOne(
            @PathVariable("groupId") UUID groupId,
            @PathVariable("memberId") UUID memberId
    ) {
        MemberWithUserDetailsDTO member = this.memberService.searchOne(groupId, memberId);
        return ResponseEntity.ok(member);
    }
}
