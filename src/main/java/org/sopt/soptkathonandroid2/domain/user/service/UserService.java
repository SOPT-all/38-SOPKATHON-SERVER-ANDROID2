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

import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private static final DateTimeFormatter COMPLETED_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("HH:mm");

    private final UserRepository userRepository;
    private final UserMissionRepository userMissionRepository;

    public UserCompletedMissionsResponse getCompletedMissions(UserCompletedMissionsRequest request) {
        if (request == null || request.userId() == null) {
            throw new CustomException(GlobalErrorCode.INVALID_INPUT_VALUE);
        }

        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new CustomException(GlobalErrorCode.NOT_FOUND));

        List<UserMission> completedUserMissions =
                userMissionRepository.findAllByUser_IdAndStatusAndCompletedAtIsNotNullOrderByCompletedAtDesc(
                        user.getId(),
                        MissionStatus.COMPLETED
                );

        int movedDistance = user.getScore() * 10;
        int level = calculateLevel(movedDistance);

        List<UserCompletedMissionsResponse.MissionCompletedResponse> missionCompleted =
                completedUserMissions.stream()
                        .map(this::toMissionCompletedResponse)
                        .toList();

        return new UserCompletedMissionsResponse(
                user.getId(),
                String.valueOf(missionCompleted.size()),
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
                userMission.getCompletedAt().format(COMPLETED_TIME_FORMATTER)
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