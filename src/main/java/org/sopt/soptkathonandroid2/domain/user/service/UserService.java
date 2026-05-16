package org.sopt.soptkathonandroid2.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.sopt.soptkathonandroid2.domain.mission.entity.Mission;
import org.sopt.soptkathonandroid2.domain.user.dto.request.UserCompletedMissionsRequest;
import org.sopt.soptkathonandroid2.domain.user.dto.response.UserCompletedMissionsResponse;
import org.sopt.soptkathonandroid2.domain.user.entity.User;
import org.sopt.soptkathonandroid2.domain.user.repository.UserRepository;
import org.sopt.soptkathonandroid2.domain.userMisson.entity.MissionStatus;
import org.sopt.soptkathonandroid2.domain.userMisson.entity.UserMission;
import org.sopt.soptkathonandroid2.domain.userMisson.repository.UserMissionRepository;
import org.sopt.soptkathonandroid2.global.common.code.GlobalErrorCode;
import org.sopt.soptkathonandroid2.global.exception.CustomException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private static final int DISTANCE_PER_SCORE = 10;

    private final UserRepository userRepository;
    private final UserMissionRepository userMissionRepository;

    public UserCompletedMissionsResponse getCompletedMissions(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(GlobalErrorCode.NOT_FOUND));

        List<UserMission> completedUserMissions =
                userMissionRepository.findAllByUser_IdAndStatusAndCompletedAtIsNotNullOrderByCompletedAtDesc(
                        user.getId(),
                        MissionStatus.COMPLETED
                );

        int movedDistance = user.getScore() * DISTANCE_PER_SCORE;
        int level = calculateLevel(user.getScore());

        List<UserCompletedMissionsResponse.MissionCompletedResponse> missionCompleted =
                completedUserMissions.stream()
                        .map(this::toMissionCompletedResponse)
                        .toList();

        return new UserCompletedMissionsResponse(
                user.getId(),
                missionCompleted.size(),
                level,
                movedDistance,
                missionCompleted
        );
    }

    private UserCompletedMissionsResponse.MissionCompletedResponse toMissionCompletedResponse(
            UserMission userMission
    ) {
        Mission mission = userMission.getMission();

        return new UserCompletedMissionsResponse.MissionCompletedResponse(
                mission.getId(),
                userMission.getStatus().name(),
                mission.getDescription(),
                mission.getDifficulty().name(),
                userMission.getCompletedAt().toLocalTime()
        );
    }

    private int calculateLevel(int movedDistance) {
        if (movedDistance >= 30) {
            return 4;
        }
        if (movedDistance >= 20) {
            return 3;
        }
        if (movedDistance >= 10) {
            return 2;
        }
        return 1;
    }
}