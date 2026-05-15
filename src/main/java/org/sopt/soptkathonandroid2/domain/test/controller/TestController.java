package org.sopt.soptkathonandroid2.domain.test.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.sopt.soptkathonandroid2.domain.test.code.HealthSuccessCode;
import org.sopt.soptkathonandroid2.domain.test.dto.response.HeathResponse;
import org.sopt.soptkathonandroid2.global.common.response.SuccessResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/test")
@Tag(name = "Test", description = "테스트용 API")
public class TestController {

    @Operation(summary = "헬스 체크", description = "서버 상태를 확인합니다.")
    @GetMapping("/health")
    public ResponseEntity<SuccessResponse<HeathResponse>> healthCheck() {
        return ResponseEntity
                .ok(SuccessResponse.of(HealthSuccessCode.HEALTH_SUCCESS, HeathResponse.create()));
    }
}
