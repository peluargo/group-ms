package app.peluargo.group.api.repositories;

import app.peluargo.group.api.models.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MemberRepository extends JpaRepository<Member, UUID> {
    Member findByIdUserIdAndIdGroupId(UUID userId, UUID groupId);
}
