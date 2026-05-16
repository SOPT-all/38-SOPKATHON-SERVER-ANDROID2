package org.sopt.soptkathonandroid2.domain.userMisson.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MissionStatus {
    IN_PROGRESS("진행중"),
    COMPLETED("완료됨");

    private final String description;
}
