package com.swe.project.eventprocessor.service;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class BadgeNotificationService {

    private final StringRedisTemplate redisTemplate;

    public BadgeNotificationService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public Long incrementBadge(String learnerId) {
        return redisTemplate.opsForValue().increment("badge:" + learnerId);
    }
}
