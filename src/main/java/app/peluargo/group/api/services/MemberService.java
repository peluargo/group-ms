package app.peluargo.group.api.services;

import app.peluargo.group.api.clients.dtos.GetAllGroupMemberDetailsRequestDTO;
import app.peluargo.group.api.dtos.*;
import app.peluargo.group.api.models.Member;
import app.peluargo.group.api.exceptions.GroupNotFoundException;
import app.peluargo.group.api.exceptions.InvalidUserIdException;
import app.peluargo.group.api.clients.UserMSClient;
import app.peluargo.group.api.mappers.MemberMapper;
import app.peluargo.group.api.mappers.RoleMapper;
import app.peluargo.group.api.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private UserMSClient userMSClient;

    public MemberDTO createSingleMember(UUID groupId, UUID userId) {
        if (this.userIdIsInvalid(userId)) {
            throw new InvalidUserIdException("User does not exist");
        }

//        if (this.)

        Member member = this.memberRepository.save(Member.builder().id(userId).group().build());

        return MemberMapper.toMemberDTO(member);
    }

    public List<MemberDTO> createMultipleMembers(UUID groupId, MemberCreationDTO memberCreationDTO) {
        return memberCreationDTO.memberIdList().stream().map(userId -> this.createSingleMember(groupId, userId)).toList();
    }

    public MemberWithUserDetailsDTO searchOne(UUID groupId, UUID memberId) {
        Member member = this.memberRepository.findByIdUserIdAndIdGroupId(groupId, memberId);

        UserDetailsDTO userDetails = this.getUserDetails(memberId);

        return MemberMapper.toMemberWithUserDetailsDTO(member, userDetails);
    }

    public MemberDTO update(UUID id, MemberUpdateDTO memberUpdateDTO) {
        Member member = this.memberRepository.findById(id).orElseThrow(GroupNotFoundException::new);

        if (memberUpdateDTO.roles() != null) {
            member.setRoles(RoleMapper.toRoles(memberUpdateDTO.roles()));
        }

        Member updatedMember = this.memberRepository.save(member);

        return MemberMapper.toMemberDTO(updatedMember);
    }

    public void delete(UUID id) {
        this.memberRepository.deleteById(id);
    }

    public boolean userIdIsInvalid(UUID id) {
        return this.getUserDetails(id) == null;
    }

    public UserDetailsDTO getUserDetails(UUID id) {
        ResponseEntity<UserDetailsDTO> response = this.userMSClient.searchOneUser(id);

        if (HttpStatus.NOT_FOUND.value() == response.getStatusCode().value()) {
            throw new RuntimeException("Something went wrong while fetching for member details");
        } else if (response.getStatusCode().isError()) {
            throw new RuntimeException("Something went wrong while fetching for member details");
        }

        return response.getBody();
    }

    public Page<UserDetailsDTO> getUserDetailsList(List<UUID> ids) {
        GetAllGroupMemberDetailsRequestDTO request = new GetAllGroupMemberDetailsRequestDTO(ids);

        ResponseEntity<Page<UserDetailsDTO>> response = this.userMSClient.searchAllInList(request);

        if (response.getStatusCode().isError()) {
            throw new RuntimeException("Something went wrong while fetching for members details");
        }

        return response.getBody();
    }
}
