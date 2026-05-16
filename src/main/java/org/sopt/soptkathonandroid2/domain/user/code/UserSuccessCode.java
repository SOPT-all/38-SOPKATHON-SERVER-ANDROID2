package org.sopt.soptkathonandroid2.domain.user.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.sopt.soptkathonandroid2.global.common.code.BaseCode;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserSuccessCode implements BaseCode {

    USER_COMPLETED_MISSIONS_FETCHED(HttpStatus.OK, "유저 미션 완료 데이터 조회 성공");

    private final HttpStatus status;
    private final String message;

    @Override
    public String getCode() {
        return name();
    }
}