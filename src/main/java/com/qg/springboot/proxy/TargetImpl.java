package com.qg.springboot.proxy;

import lombok.extern.slf4j.Slf4j;

/**
 * @author WilderGao
 * time 2018-09-13 22:00
 * motto : everything is no in vain
 * description
 */
@Slf4j
public class TargetImpl implements Target {
    @Override
    public int test(int i) {
        return i+1;
    }
}
