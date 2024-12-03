package app.peluargo.group.api.controllers;

import app.peluargo.group.api.dtos.MemberWithUserDetailsDTO;
import app.peluargo.group.api.dtos.UserDetailsDTO;
import app.peluargo.group.api.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/groups/{groupId}/members")
public class MemberController {
    @Autowired
    private MemberService memberService;

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
