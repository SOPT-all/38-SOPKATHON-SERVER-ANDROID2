package org.sopt.soptkathonandroid2.domain.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "홈 화면 완료 미션 조회 요청")
public record UserCompletedMissionsRequest(

        @Schema(description = "사용자 ID", example = "1")
        Long userId
) {
}