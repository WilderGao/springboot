package com.qg.springboot.shard;

import com.qg.springboot.shard.mapper.UserMapper;
import com.qg.springboot.shard.pojo.UserEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author WilderGao
 * time 2019-04-05 10:41
 * motto : everything is no in vain
 * description
 */
@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    private Long userId;

    public Long getUserId() {
        return userId;
    }

    @Transactional
    public void updateWithFail() {
        UserEntity user = new UserEntity();
        user.setCityId(2);
        user.setUserName("insertTest");
        user.setAge(10);
        user.setBirth(new Date());

        userMapper.insertSlave(user);
        userId = user.getUserId();

        user.setUserName("insertTest2");
        userMapper.update(user);
//        throw new IllegalArgumentException("failed");
    }
}
