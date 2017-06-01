package com.ibm.ABCDEF.config;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.google.common.collect.Lists;

import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisSentinelConfigurations<V> implements EnvironmentAware {

	private RelaxedPropertyResolver redisPropertyResolver;

	@Override
	public void setEnvironment(Environment environment) {
		redisPropertyResolver = new RelaxedPropertyResolver(environment, "spring.redis.");
	}

	@Bean(name="stringRedisTemplate")
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
		jedisPoolConfig.setTestWhileIdle(Boolean.TRUE);
		jedisPoolConfig.setTestOnReturn(Boolean.TRUE);

		return jedisPoolConfig;
	}

	private RedisSentinelConfiguration initRedisSentinelConfiguration() {

		String[] sentinels = StringUtils.split(redisPropertyResolver.getProperty("sentinel.nodes"), ",");
		if (sentinels == null) {
			throw new IllegalArgumentException("Must be set sentinel.nodes");
		}

		List<RedisNode> redisNodeList = Lists.newArrayList();
		for (String sentinel : sentinels) {
			String host = StringUtils.substringBefore(sentinel, ":");
			Integer port = Integer.valueOf(StringUtils.substringAfterLast(sentinel, ":"));
			RedisNode redisNode = new RedisNode(host, port);
			redisNodeList.add(redisNode);
		}

		RedisSentinelConfiguration redisSentinelConfiguration = new RedisSentinelConfiguration();
		redisSentinelConfiguration.setMaster(redisPropertyResolver.getProperty("sentinel.master"));
		redisSentinelConfiguration.setSentinels(redisNodeList);

		return redisSentinelConfiguration;
	}

	private JedisConnectionFactory initJedisConnectionFactory() {

		JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(initRedisSentinelConfiguration(),
				initJedisPoolConfig());
		return jedisConnectionFactory;
	}

}