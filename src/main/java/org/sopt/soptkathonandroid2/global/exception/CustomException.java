package org.sopt.soptkathonandroid2.global.exception;

import lombok.Getter;
import org.sopt.soptkathonandroid2.global.common.code.BaseCode;

@Getter
public class CustomException extends RuntimeException {
    private final BaseCode errorCode;

    public CustomException(BaseCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
