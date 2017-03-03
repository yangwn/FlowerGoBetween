package com.ibm.ABCDEF;

import static java.util.concurrent.TimeUnit.SECONDS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author Marcos Barbero
 */
@Component
public class RedisRateLimiter implements RateLimiter {

	@Autowired
	private RedisTemplate<String, String> stringRedisTemplate;

	@Override
	public Rate consume(final Policy policy, final String key) {
		final Long limit = policy.getLimit();
		
		final Long refreshInterval = policy.getRefreshInterval();
		final Long current = this.stringRedisTemplate.boundValueOps(key).increment(1L);
		Long expire = this.stringRedisTemplate.getExpire(key);
		if (expire == null || expire == -1) {
			this.stringRedisTemplate.expire(key, refreshInterval, SECONDS);
			expire = refreshInterval;
		}
		return new Rate(limit, Math.max(-1, limit - current), SECONDS.toMillis(expire));
	}
}
