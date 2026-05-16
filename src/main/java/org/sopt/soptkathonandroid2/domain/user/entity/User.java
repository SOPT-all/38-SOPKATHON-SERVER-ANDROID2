package org.sopt.soptkathonandroid2.domain.user.entity;

import jakarta.persistence.*;
import lombok.*;
import org.sopt.soptkathonandroid2.global.common.entity.BaseTimeEntity;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@Entity
@Table(name = "users")
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;

    @Enumerated(EnumType.STRING)
    @Column(name = "dubi_type")
    private DubiTypes dubiType;

    private int score;
}
