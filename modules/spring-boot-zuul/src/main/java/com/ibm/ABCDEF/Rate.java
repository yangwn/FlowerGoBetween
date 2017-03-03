package com.ibm.ABCDEF;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a view of rate limit in a giving time for a user.
 * <p>
 * limit - How many requests can be executed by the user. Maps to
 * X-RateLimit-Limit header.
 * <p>
 * remaining - How many requests are still left on the current window. Maps to
 * X-RateLimit-Remaining header.
 * <p>
 * reset - Epoch when the rate is replenished by limit. Maps to
 * X-RateLimit-Reset header
 * <p>
 * 
 * @author Marcos Barbero
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rate {

	private Long limit;
	private Long remaining;
	private Long reset;

}
