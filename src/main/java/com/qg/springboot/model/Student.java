package com.qg.springboot.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author WilderGao
 * time 2018-09-13 17:00
 * motto : everything is no in vain
 * description
 */
@Data
public class Student implements Serializable {
    private int id;
    private String studentId;
    private String name;
}
