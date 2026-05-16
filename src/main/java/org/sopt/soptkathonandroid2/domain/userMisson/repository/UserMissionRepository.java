package org.sopt.soptkathonandroid2.domain.userMisson.repository;

import org.sopt.soptkathonandroid2.domain.userMisson.entity.MissionStatus;
import org.sopt.soptkathonandroid2.domain.userMisson.entity.UserMission;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMissionRepository extends JpaRepository<UserMission, Long> {

    @EntityGraph(attributePaths = "mission")
    List<UserMission> findAllByUser_IdAndStatusAndCompletedAtIsNotNullOrderByCompletedAtDesc(
            Long userId,
            MissionStatus status
    );
}