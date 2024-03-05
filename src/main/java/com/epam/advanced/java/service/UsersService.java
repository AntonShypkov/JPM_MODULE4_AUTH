package com.epam.advanced.java.service;

import com.epam.advanced.java.domain.bo.User;
import com.epam.advanced.java.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UsersService {

    private final UserRepository userRepository;
    private final LoginAttemptService loginAttemptService;

    public List<String> getBlockedUsers() {
        log.info("Discover all bloocked users");
        return userRepository.findAll().stream()
                .map(User::getUsername)
                .peek(username -> log.info("Check if {} is blocked", username))
                .filter(loginAttemptService::isBlocked)
                .peek(username -> log.info("Checked that {} is blocked", username))
                .collect(Collectors.toList());
    }
}
