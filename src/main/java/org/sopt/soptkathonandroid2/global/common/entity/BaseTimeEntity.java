package org.sopt.soptkathonandroid2.global.common.entity;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
public abstract class BaseTimeEntity extends BaseCreatedEntity {
    @LastModifiedDate
    private LocalDateTime updatedAt;
}
