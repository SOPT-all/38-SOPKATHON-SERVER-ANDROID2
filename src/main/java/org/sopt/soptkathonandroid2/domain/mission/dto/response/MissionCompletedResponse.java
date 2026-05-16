package org.sopt.soptkathonandroid2.domain.mission.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import org.sopt.soptkathonandroid2.domain.mission.entity.Mission;
import org.sopt.soptkathonandroid2.domain.userMisson.entity.UserMission;

import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "미션 완료 처리 응답")
public record MissionCompletedResponse(

        @Schema(description = "사용자 ID", example = "1")
        Long userId,

        @Schema(description = "완료한 미션 수", example = "7")
        Integer missionCompletedCount,

        @Schema(description = "누적 이동 거리 (사용자 누적 점수 x 10)", example = "70")
        Integer movedDistance,

        @Schema(description = "완료된 미션 목록")
        List<CompletedMission> missionCompleted

) {
    public static MissionCompletedResponse of(
            Long userId,
            Integer score,
            List<UserMission> completedUserMissions
    ) {
        List<CompletedMission> missions = completedUserMissions.stream()
                .map(CompletedMission::from)
                .toList();

        return new MissionCompletedResponse(
                userId,
                missions.size(),
                score * 10,
                missions
        );
    }

    @Schema(description = "완료된 개별 미션")
    public record CompletedMission(

            @Schema(description = "미션 ID", example = "1")
            Long missionId,

            @Schema(description = "미션 상태", example = "COMPLETED")
            String status,

            @Schema(description = "미션 설명", example = "하늘 사진 찍기")
            String description,

            @Schema(description = "미션 난이도", example = "HARD")
            String difficulty,

            @Schema(description = "완료 시각", example = "18:00")
            @JsonFormat(pattern = "HH:mm")
            LocalDateTime completedAt
    ) {
        public static CompletedMission from(UserMission userMission) {
            Mission mission = userMission.getMission();
            return new CompletedMission(
                    mission.getId(),
                    userMission.getStatus().name(),
                    mission.getDescription(),
                    mission.getDifficulty().name(),
                    userMission.getCompletedAt()
            );
        }
    }
}
