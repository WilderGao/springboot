package com.qg.springboot.shard;

import com.qg.springboot.shard.mapper.UserMapper;
import com.qg.springboot.shard.pojo.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



/**
 * @author WilderGao
 * time 2019-04-05 10:43
 * motto : everything is no in vain
 * description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ShardingTest {

    @Autowired
    private UserService userService;

    @Test
    public void getOneSlave() throws Exception {

        userService.updateWithFail();
    }
}
