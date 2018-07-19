package com.youfu.sbdemo.service;

import com.youfu.sbdemo.domain.Course;
import com.youfu.sbdemo.domain.Student;
import com.youfu.sbdemo.mapper.CourseMapper;
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
    @Autowired
    private CourseMapper courseMapper;

    @Test
    public void StudentServiceTest() {
        String wechat = "balabala";
        studentService.login(wechat);

        String name = "吴语安";
        String sex = "男";
        String sn = "20180423";
        String specialty = "计算机科学与技术"; // 系
        String grade = "2018级"; // 年级
        String school = "江南大学";
        studentService.updateProfile(wechat, name, sex, sn, specialty, grade, school);

        Student student = studentService.login(wechat);
        String courseName = "C语言";
        Integer teacherId = 20;
        Course course = courseMapper.getCourseByNameAndTeacher(courseName, teacherId);
        studentService.signInCourseByWechat(student.getId(), course.getId());

    }
}
