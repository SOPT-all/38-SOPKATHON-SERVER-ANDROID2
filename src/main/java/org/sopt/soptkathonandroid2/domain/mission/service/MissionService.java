package org.sopt.soptkathonandroid2.domain.mission.service;

import lombok.RequiredArgsConstructor;
import org.sopt.soptkathonandroid2.domain.mission.repository.MissionRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionService {
    private final MissionRepository missionRepository;
}
