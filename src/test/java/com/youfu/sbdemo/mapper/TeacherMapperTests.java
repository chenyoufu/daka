package com.youfu.sbdemo.mapper;

import com.youfu.sbdemo.domain.Teacher;
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
public class TeacherMapperTests {
    @Autowired
    private TeacherMapper teacherMapper;

    @Test
    public void contextLoads() {
    }

    @Test
    @Transactional
    public void TeacherMapperTest() {

        String wechat = "ihackd";
        Teacher teacher = new Teacher();
        teacher.setName("fuyou");
        teacher.setWechat(wechat);
        teacherMapper.insertTeacher(teacher);

        teacher = teacherMapper.getTeacherByWechat(wechat);
        Assert.assertEquals("fuyou", teacher.getName());

        teacher.setName("youfu");
        teacherMapper.updateTeacher(teacher);
        teacher = teacherMapper.getTeacherByWechat(wechat);
        Assert.assertEquals("youfu", teacher.getName());

        List<Teacher> teachers = teacherMapper.getAllTeachers();
        for (Teacher t : teachers) {
            System.out.println(t.getName());
        }

    }
}
