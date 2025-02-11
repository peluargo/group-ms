package app.peluargo.group.api.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "members")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    @EmbeddedId
    private MemberId id;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("groupId")
    private Group group;

    @ManyToMany
    @JoinTable(
            name = "member_roles",
            joinColumns = { @JoinColumn(name = "member.user_id"), @JoinColumn(name = "member.group_id") },
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;
}
