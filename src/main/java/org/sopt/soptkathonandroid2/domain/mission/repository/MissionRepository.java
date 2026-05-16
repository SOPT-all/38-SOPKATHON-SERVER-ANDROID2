package org.sopt.soptkathonandroid2.domain.mission.repository;

import org.sopt.soptkathonandroid2.domain.mission.entity.Mission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MissionRepository extends JpaRepository<Mission,Long> {
}
