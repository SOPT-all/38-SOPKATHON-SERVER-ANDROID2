package org.sopt.soptkathonandroid2.domain.user.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.sopt.soptkathonandroid2.global.common.code.BaseCode;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements BaseCode {
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 유저입니다.");

    private final HttpStatus status;
    private final String message;

    @Override
    public String getCode() { return name(); }
}
