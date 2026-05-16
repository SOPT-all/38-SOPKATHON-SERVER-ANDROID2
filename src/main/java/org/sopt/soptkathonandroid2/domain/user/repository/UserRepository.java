package org.sopt.soptkathonandroid2.domain.user.repository;

import org.sopt.soptkathonandroid2.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
