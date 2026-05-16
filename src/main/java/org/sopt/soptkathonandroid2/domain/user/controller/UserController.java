package org.sopt.soptkathonandroid2.domain.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.sopt.soptkathonandroid2.domain.user.code.UserSuccessCode;
import org.sopt.soptkathonandroid2.domain.user.dto.request.UserCompletedMissionsRequest;
import org.sopt.soptkathonandroid2.domain.user.dto.response.UserCompletedMissionsResponse;
import org.sopt.soptkathonandroid2.domain.user.service.UserService;
import org.sopt.soptkathonandroid2.global.common.response.SuccessResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@Tag(name = "User", description = "사용자 API")
public class UserController {

    private final UserService userService;

    @Operation(
            summary = "홈 화면 데이터 가져오기",
            description = "사용자의 완료 미션 개수, 레벨, 이동 거리, 완료 미션 목록을 조회합니다.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    description = "홈 화면 완료 미션 조회 요청",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UserCompletedMissionsRequest.class)
                    )
            )
    )
    @GetMapping("/completed-missions/{userId}")
    public ResponseEntity<SuccessResponse<UserCompletedMissionsResponse>> getCompletedMissions(
            @PathVariable Long userId
    ) {
        UserCompletedMissionsResponse response = userService.getCompletedMissions(userId);

        return ResponseEntity.ok(
                SuccessResponse.of(UserSuccessCode.USER_COMPLETED_MISSIONS_FETCHED, response)
        );
    }
}