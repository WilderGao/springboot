package com.qg.springboot;

import com.qg.springboot.redis.StudentService;
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

	@Autowired
	private StudentService studentService;

	@Test
	public void contextLoads() {
		System.out.println(studentService.findAll());
	}

}
