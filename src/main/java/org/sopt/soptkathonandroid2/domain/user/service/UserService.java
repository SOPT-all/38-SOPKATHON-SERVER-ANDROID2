package org.sopt.soptkathonandroid2.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.sopt.soptkathonandroid2.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
}
