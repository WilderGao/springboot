package com.qg.springboot.mysql.dao;

import com.qg.springboot.model.Student;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author WilderGao
 * time 2018-09-13 16:10
 * motto : everything is no in vain
 * description
 */
//@Mapper
//@Repository
public interface StudentDao {

    /**
     * 查询所有学生
     * @return
     */
    List<Student> selectAllStudent();
}
