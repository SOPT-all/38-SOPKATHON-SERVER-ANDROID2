package org.sopt.soptkathonandroid2.domain.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.sopt.soptkathonandroid2.domain.user.code.UserSuccessCode;
import org.sopt.soptkathonandroid2.domain.user.dto.request.UserCompletedMissionsRequest;
import org.sopt.soptkathonandroid2.domain.user.dto.response.UserCompletedMissionsResponse;
import org.sopt.soptkathonandroid2.domain.user.service.UserService;
import org.sopt.soptkathonandroid2.global.common.response.SuccessResponse;
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
            description = "사용자의 완료 미션 개수, 레벨, 이동 거리, 완료 미션 목록을 조회합니다."
    )
    @GetMapping("/completed-missions")
    public ResponseEntity<SuccessResponse<UserCompletedMissionsResponse>> getCompletedMissions(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "조회할 사용자 ID",
                    required = true
            )
            @RequestBody UserCompletedMissionsRequest request
    ) {
        UserCompletedMissionsResponse response = userService.getCompletedMissions(request);

        return ResponseEntity.ok(
                SuccessResponse.of(UserSuccessCode.USER_COMPLETED_MISSIONS_FETCHED, response)
        );
    }
}