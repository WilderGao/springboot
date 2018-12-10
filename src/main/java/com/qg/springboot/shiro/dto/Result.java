package com.qg.springboot.shiro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author WilderGao
 * time 2018-12-04 19:58
 * motto : everything is no in vain
 * description 返回给前端的结果
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
    /**
     * 状态码
     */
    private int status;

    /**
     * 具体内容
     */
    private T data;

}
