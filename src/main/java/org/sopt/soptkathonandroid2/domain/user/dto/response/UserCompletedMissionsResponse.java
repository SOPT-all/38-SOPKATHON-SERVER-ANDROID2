package org.sopt.soptkathonandroid2.domain.user.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalTime;
import java.util.List;

@Schema(description = "홈 화면 완료 미션 조회 응답")
public record UserCompletedMissionsResponse(

        @Schema(description = "사용자 ID", example = "1")
        Long userId,

        @Schema(description = "완료된 미션 개수", example = "7")
        String missionCompletedCount,

        @Schema(description = "사용자의 누적 이동 거리에 따른 레벨", example = "4")
        int level,

        @Schema(description = "사용자의 누적 점수 x 10", example = "100")
        int movedDistance,

        @Schema(description = "완료한 미션 목록")
        List<MissionCompletedResponse> missionCompleted
) {

    @Schema(description = "완료한 미션 정보")
    public record MissionCompletedResponse(

            @Schema(description = "미션 ID", example = "1")
            Long missionId,

            @Schema(description = "미션 상태", example = "COMPLETED")
            String status,

            @Schema(description = "미션 설명", example = "하늘 사진 찍기")
            String description,

            @Schema(description = "미션 난이도", example = "HARD")
            String difficulty,

            @Schema(description = "미션 완료 시간", example = "18:00", type = "string")
            @JsonFormat(pattern = "HH:mm")
            LocalTime completedAt
    ) {
    }
}