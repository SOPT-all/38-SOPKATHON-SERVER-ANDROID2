package org.sopt.soptkathonandroid2.domain.mission.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MissionDifficulty {
    EASY("쉬움"),
    MEDIUM("탐색준비형 두더지"),
    HARD("조심교류형 두더지");

    private final String description;
}
