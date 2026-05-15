package org.sopt.soptkathonandroid2.domain.test.dto.response;

import java.time.LocalDateTime;

public record HeathResponse(LocalDateTime timestamp, String message) {
    public static HeathResponse create() {
        return new HeathResponse(
                LocalDateTime.now(),
                "서버 정상 동작 중"
        );
    }
}
