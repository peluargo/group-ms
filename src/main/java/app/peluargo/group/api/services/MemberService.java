package app.peluargo.group.api.services;

import app.peluargo.group.api.clients.dtos.GetAllGroupMemberDetailsRequestDTO;
import app.peluargo.group.api.dtos.*;
import app.peluargo.group.api.entities.Member;
import app.peluargo.group.api.exceptions.GroupNotFoundException;
import app.peluargo.group.api.exceptions.InvalidUserIdException;
import app.peluargo.group.api.clients.UserMSClient;
import app.peluargo.group.api.mappers.MemberMapper;
import app.peluargo.group.api.mappers.RoleMapper;
import app.peluargo.group.api.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    public MemberDTO create(MemberCreationDTO memberCreationDTO) {
        if (this.isUserIdInvalid(memberCreationDTO.userId())) {
            throw new InvalidUserIdException("User does not exist");
        }

        Member member = this.memberRepository.save(MemberMapper.toMember(memberCreationDTO));

        return MemberMapper.toMemberDTO(member);
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

    public boolean isUserIdInvalid(UUID id) {
        return this.getUserDetails(id) == null;
    }

    public UserDetailsDTO getUserDetails(UUID id) {
        ResponseEntity<UserDetailsDTO> response = this.userMSClient.searchOneUser(id);

        if (response.getStatusCode().isError()) {
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
