package org.sopt.soptkathonandroid2.domain.mission.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.sopt.soptkathonandroid2.global.common.code.BaseCode;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MissionErrorCode implements BaseCode {
    MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 미션입니다."),
    MISSION_ALREADY_COMPLETED(HttpStatus.CONFLICT, "이미 완료된 미션입니다."),
    MISSION_NOT_STARTED(HttpStatus.BAD_REQUEST, "시작되지 않은 미션입니다.");

    private final HttpStatus status;
    private final String message;

    @Override
    public String getCode() { return name(); }
}
