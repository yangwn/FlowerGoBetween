package com.ibm.ABCDEF.config;

import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisCacheConfiguration<V> implements EnvironmentAware {

	private RelaxedPropertyResolver redisPropertyResolver;

	@Override
	public void setEnvironment(Environment environment) {
		redisPropertyResolver = new RelaxedPropertyResolver(environment, "spring.redis.");
	}

	@Bean(name = "stringRedisTemplate")
	public RedisTemplate<String, String> configStringRedisTemplate() {

		RedisTemplate<String, String> stringRedisTemplate = new RedisTemplate<String, String>();
		stringRedisTemplate.setConnectionFactory(initJedisConnectionFactory());
		stringRedisTemplate.setKeySerializer(new StringRedisSerializer());
		stringRedisTemplate.setValueSerializer(new StringRedisSerializer());
		return stringRedisTemplate;
	}

	private JedisPoolConfig initJedisPoolConfig() {

		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMinIdle(Integer.valueOf(redisPropertyResolver.getProperty("pool.min-idle")));
		jedisPoolConfig.setMaxIdle(Integer.valueOf(redisPropertyResolver.getProperty("pool.max-idle")));
		jedisPoolConfig.setMaxTotal(Integer.valueOf(redisPropertyResolver.getProperty("pool.max-active")));
		jedisPoolConfig.setMaxWaitMillis(Long.valueOf(redisPropertyResolver.getProperty("pool.max-wait")));
		jedisPoolConfig.setTestOnBorrow(Boolean.TRUE);
		return jedisPoolConfig;
	}

	private JedisConnectionFactory initJedisConnectionFactory() {

		JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
		jedisConnectionFactory.setHostName(redisPropertyResolver.getProperty("host"));
		jedisConnectionFactory.setDatabase(Integer.valueOf(redisPropertyResolver.getProperty("database")));
		jedisConnectionFactory.setPort(Integer.valueOf(redisPropertyResolver.getProperty("port")));
		jedisConnectionFactory.setPassword(redisPropertyResolver.getProperty("password"));
		jedisConnectionFactory.setTimeout(Integer.valueOf(redisPropertyResolver.getProperty("timeout")));
		jedisConnectionFactory.setUsePool(Boolean.TRUE);
		jedisConnectionFactory.setPoolConfig(initJedisPoolConfig());
		return jedisConnectionFactory;
	}

}
