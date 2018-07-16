package com.youfu.sbdemo.service;

import com.youfu.sbdemo.domain.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentServiceTests {

    @Autowired
    private StudentService studentService;

    @Test
    public void signInCourseByWechatTest() {
        Student student = new Student();


    }
}
