package com.ibm.ABCDEF.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ibm.ABCDEF.RateLimitFilter;
import com.ibm.ABCDEF.RateLimiter;

/**
 * @author Marcos Barbero
 */
@Configuration
@EnableConfigurationProperties(RateLimitProperties.class)
@ConditionalOnProperty(name = RateLimitProperties.PREFIX + ".enabled", havingValue = "true")
public class RateLimitAutoConfiguration {

	@Bean
	public RateLimitFilter rateLimiterFilter(RateLimiter rateLimiter, RateLimitProperties rateLimitProperties,
			RouteLocator routeLocator) {
		return new RateLimitFilter(rateLimiter, rateLimitProperties, routeLocator);
	}
	
	// @ConditionalOnClass(RedisTemplate.class)
	// public static class RedisConfiguration {
	// @Bean
	// public StringRedisTemplate redisTemplate(RedisConnectionFactory cf) {
	// return new StringRedisTemplate(cf);
	// }
	//
	// @Bean
	// public RateLimiter redisRateLimiter(RedisTemplate redisTemplate) {
	// return new RedisRateLimiter();
	// }
	// }

}
