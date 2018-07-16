package com.youfu.sbdemo.mapper;

import com.youfu.sbdemo.domain.Student;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentMapperTests {
    @Autowired
    private StudentMapper studentMapper;

    @Test
    public void contextLoads() {
    }

    @Test
    @Transactional
    public void StudentMapperTest() {

        Student student = new Student();
        student.setName("有福");
        student.setGrade("2006级");
        student.setSchool("江南大学");
        student.setSex("男");
        student.setSn("1234567890");
        student.setSpecialty("计算机科学与技术");
        student.setWechat("r00t");
        studentMapper.insertStudent(student);

        student = studentMapper.getStudentByWechat("r00t");
        Assert.assertEquals("有福", student.getName());

        student.setWechat("r11t");
        studentMapper.updateStudent(student);
        student = studentMapper.getStudentByWechat("r11t");
        Assert.assertEquals("有福", student.getName());

    }
}
