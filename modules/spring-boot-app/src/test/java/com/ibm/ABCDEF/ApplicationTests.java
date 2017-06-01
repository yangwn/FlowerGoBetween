package com.ibm.ABCDEF;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.ibm.ABCDEF.config.BlogProperties;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ApplicationTests {

	@Autowired
	private BlogProperties blogProperties;

	@Autowired
	private RedisTemplate<String, String> stringRedisTemplate;

	// @Test
	public void getHello() throws Exception {
		assertThat(blogProperties.getName()).isEqualTo("程序猿DD");
		assertThat(blogProperties.getTitle()).isEqualTo("Spring Boot教程");
	}

	@Test
	public void operationRedis() {
		for (;;) {
			try {
				TimeUnit.MILLISECONDS.sleep(500);
				stringRedisTemplate.boundValueOps("name").set(String.valueOf(System.currentTimeMillis()));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}