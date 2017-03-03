package com.ibm.ABCDEF.config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.ibm.ABCDEF.Policy;

import lombok.Data;

/**
 * @author Marcos Barbero
 */
@Data
@ConfigurationProperties(RateLimitProperties.PREFIX)
public class RateLimitProperties {

	public static final String PREFIX = "zuul.ratelimit";

	private Map<String, Policy> policies = new LinkedHashMap<>();
	private boolean enabled;
	private boolean behindProxy;
}