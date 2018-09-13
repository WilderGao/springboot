package com.qg.springboot.redis;

import com.qg.springboot.model.Student;
import com.qg.springboot.mysql.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author WilderGao
 * time 2018-09-13 17:15
 * motto : everything is no in vain
 * description
 */
@Service
public class StudentService {

    @Autowired
    private StudentDao studentDao;

    @Cacheable(cacheNames = "student.service.all")
    public List<Student> findAll(){
        return studentDao.selectAllStudent();
    }
}
