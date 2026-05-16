package org.sopt.soptkathonandroid2.domain.user.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DubiTypes {
    AAA("굴정리형 두더지"),
    AAB("탐색준비형 두더지"),
    ABA("조심교류형 두더지"),
    ABB("첫발형 두더지x"),
    BAA("관찰산책형 두더지"),
    BAB("틈새도전형 두더지"),
    BBA("연결성장형 두더지"),
    BBB("돌진탐험형 두더지");

    private final String description;
}
