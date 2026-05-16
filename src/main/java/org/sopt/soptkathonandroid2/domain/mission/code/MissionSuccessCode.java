package org.sopt.soptkathonandroid2.domain.mission.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.sopt.soptkathonandroid2.global.common.code.BaseCode;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MissionSuccessCode implements BaseCode {
    USER_MISSION_COMPLETED(HttpStatus.OK, "미션 완료 처리 성공.");

    private final HttpStatus status;
    private final String message;

    @Override
    public String getCode() { return name(); }
}