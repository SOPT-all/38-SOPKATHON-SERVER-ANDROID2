package org.sopt.soptkathonandroid2.domain.mission.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.sopt.soptkathonandroid2.domain.mission.code.MissionSuccessCode;
import org.sopt.soptkathonandroid2.domain.mission.dto.request.MissionCompletedRequest;
import org.sopt.soptkathonandroid2.domain.mission.service.MissionService;
import org.sopt.soptkathonandroid2.domain.mission.dto.response.MissionCompletedResponse;
import org.sopt.soptkathonandroid2.global.common.response.SuccessResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Missions", description = "missions 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/missions")
public class MissionController {
    private final MissionService missionService;

    @Operation(
            summary = "미션 완료",
            description = "missionId에 해당하는 미션을 완료처리합니다."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "미션 완료 성공"),
            @ApiResponse(responseCode = "404", description = "미션을 찾을 수 없음 — 존재하지 않는 missionId로 요청한 경우"),
            @ApiResponse(responseCode = "409", description = "미션 완료 실패 — 이미 완료된 미션인 경우")
    })
    @PostMapping("/{missionId}/complete")
    public ResponseEntity<SuccessResponse<MissionCompletedResponse>> completeMission(
            @Parameter(description = "완료 처리할 미션 ID", example = "1", required = true)
            @PathVariable Long missionId,
            @Valid @RequestBody MissionCompletedRequest request) {
        MissionCompletedResponse response = missionService.completeMission(request, missionId);

        return ResponseEntity.ok(
                SuccessResponse.of(MissionSuccessCode.USER_MISSION_COMPLETED, response)
        );
    }

}
