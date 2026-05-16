package org.sopt.soptkathonandroid2.domain.mission.service;

import lombok.RequiredArgsConstructor;
import org.sopt.soptkathonandroid2.domain.mission.code.MissionErrorCode;
import org.sopt.soptkathonandroid2.domain.mission.dto.request.MissionCompletedRequest;
import org.sopt.soptkathonandroid2.domain.mission.dto.response.MissionCompletedResponse;
import org.sopt.soptkathonandroid2.domain.mission.entity.Mission;
import org.sopt.soptkathonandroid2.domain.mission.repository.MissionRepository;
import org.sopt.soptkathonandroid2.domain.user.code.UserErrorCode;
import org.sopt.soptkathonandroid2.domain.user.entity.User;
import org.sopt.soptkathonandroid2.domain.user.repository.UserRepository;
import org.sopt.soptkathonandroid2.domain.userMisson.entity.MissionStatus;
import org.sopt.soptkathonandroid2.domain.userMisson.entity.UserMission;
import org.sopt.soptkathonandroid2.domain.userMisson.repository.UserMissionRepository;
import org.sopt.soptkathonandroid2.global.exception.CustomException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionService {
    private final MissionRepository missionRepository;
    private final UserMissionRepository userMissionRepository;
    private final UserRepository userRepository;

    @Transactional
    public MissionCompletedResponse completeMission(MissionCompletedRequest request, Long missionId){
        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new CustomException(UserErrorCode.USER_NOT_FOUND));

        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new CustomException(MissionErrorCode.MISSION_NOT_FOUND));

        UserMission userMission = userMissionRepository.findByUserIdAndMissionId(request.userId(), missionId)
                .orElseThrow(() -> new CustomException(MissionErrorCode.MISSION_NOT_STARTED));

        if (userMission.getStatus() == MissionStatus.COMPLETED)
            throw new CustomException(MissionErrorCode.MISSION_ALREADY_COMPLETED);

        user.increaseScore(mission.getRewardScore());

        userMission.complete();

        List<UserMission> completedUserMissions = userMissionRepository.findAllByUserIdAndStatus(request.userId(), MissionStatus.COMPLETED);

        return MissionCompletedResponse.of(request.userId(), user.getScore(), completedUserMissions);
    }
}
