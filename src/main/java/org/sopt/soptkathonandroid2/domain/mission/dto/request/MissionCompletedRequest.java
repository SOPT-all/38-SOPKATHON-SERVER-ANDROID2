package org.sopt.soptkathonandroid2.domain.mission.dto.request;

import jakarta.validation.constraints.NotNull;

public record MissionCompletedRequest(
        @NotNull
        Long userId
){ }
