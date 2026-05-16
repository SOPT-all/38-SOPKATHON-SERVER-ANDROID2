package org.sopt.soptkathonandroid2.domain.userMisson.repository;

import org.sopt.soptkathonandroid2.domain.userMisson.entity.UserMission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMissionRepository extends JpaRepository<UserMission, Long> {
}
