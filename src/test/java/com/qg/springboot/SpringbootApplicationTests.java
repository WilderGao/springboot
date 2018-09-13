package com.qg.springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootApplicationTests {

	@Resource
	private RedisTemplate redisTemplate;

	@Test
	public void contextLoads() {
		redisTemplate.opsForValue().set("aaa", "111");
		System.out.println(redisTemplate.opsForValue().get("aaa"));
	}

}
