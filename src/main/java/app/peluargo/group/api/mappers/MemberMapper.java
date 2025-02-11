package app.peluargo.group.api.mappers;

import app.peluargo.group.api.dtos.*;
import app.peluargo.group.api.models.Member;
import app.peluargo.group.api.models.MemberId;

import java.util.UUID;
import java.util.stream.Collectors;

public class MemberMapper {
    public static MemberId toMemberId(UUID groupId, UUID memberId) {
        return new MemberId(groupId, memberId);
    }

    public static Member toMember(MemberCreationDTO memberCreationDTO) {
        return Member.builder()
                .id(MemberMapper.toMemberId(memberCreationDTO.userId(), memberCreationDTO.groupId()))
                .build();
    }

    public static Member toMember(MemberUpdateDTO memberUpdateDTO) {
        return Member.builder()
                .roles(RoleMapper.toRoles(memberUpdateDTO.roles()))
                .build();
    }

    public static MemberDTO toMemberDTO(Member member) {
        return new MemberDTO(
                member.getId().getUserId(),
                member.getRoles().stream().map(RoleMapper::toRoleDTO).collect(Collectors.toSet())
        );
    }

    public static MemberWithUserDetailsDTO toMemberWithUserDetailsDTO(Member member, UserDetailsDTO userDetails) {
        return new MemberWithUserDetailsDTO(
                userDetails.id(),
                userDetails.firstName(),
                userDetails.lastName(),
                userDetails.birthdate(),
                userDetails.email(),
                member.getRoles().stream().map(RoleMapper::toRoleDTO).collect(Collectors.toSet())
        );
    }
}
