package com.qg.springboot.shard.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @author WilderGao
 * time 2019-04-05 10:28
 * motto : everything is no in vain
 * description
 */
@Data
public class UserEntity {

    private Integer id;

    private Long userId;

    private Integer cityId;

    private String userName;

    private Integer age;

    private Date birth;

    private static final long serialVersionUID = 1L;
}
