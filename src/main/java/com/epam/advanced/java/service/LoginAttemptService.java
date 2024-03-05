package com.epam.advanced.java.service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class LoginAttemptService {

    @Value("${login.failure.max.attempts}")
    public int loginFailureMaxAttempts;

    @Value("${login.failure.block.minutes}")
    public int loginFailureBlockTime;

    private LoadingCache<String, Integer> attemptsCache;

    @PostConstruct
    public void initializeCache() {
        log.info("User block time is {} minutes", loginFailureBlockTime);
        attemptsCache = CacheBuilder.newBuilder()
                .expireAfterWrite(loginFailureBlockTime, TimeUnit.MINUTES)
                .build(new CacheLoader<>() {
                    @Override
                    public Integer load(final String username) {
                        return 0;
                    }
                });
    }

    public void loginFailed(final String username) {
        int attempts;
        try {
            attempts = attemptsCache.get(username);
        } catch (final ExecutionException e) {
            attempts = 0;
        }
        attempts++;
        attemptsCache.put(username, attempts);
    }

    public boolean isBlocked(String username) {
        try {
            int numberOfFailedAttempts = attemptsCache.get(username);
            log.info("User {} has {} failed attempts for login", username, numberOfFailedAttempts);
            return numberOfFailedAttempts >= loginFailureMaxAttempts;
        } catch (final ExecutionException e) {
            return false;
        }
    }

}
