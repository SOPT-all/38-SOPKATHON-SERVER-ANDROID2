package org.sopt.soptkathonandroid2.domain.userMisson.entity;

import jakarta.persistence.*;
import lombok.*;
import org.sopt.soptkathonandroid2.domain.user.entity.User;
import org.sopt.soptkathonandroid2.domain.mission.entity.Mission;
import org.sopt.soptkathonandroid2.global.common.entity.BaseCreatedEntity;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@Entity
@Table(name = "user_missions")
public class UserMission extends BaseCreatedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    private Mission mission;

    @Column(name = "completed_at")
    private LocalDate completedAt;
}
