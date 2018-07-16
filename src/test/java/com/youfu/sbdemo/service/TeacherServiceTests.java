package com.youfu.sbdemo.service;

import com.youfu.sbdemo.domain.Course;
import com.youfu.sbdemo.domain.Teacher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TeacherServiceTests {

    @Autowired
    private TeacherService teacherService;

    @Test
    @Transactional
    public void createCourseTest() {

        Teacher teacher = teacherService.login("ihackz", "有福");
        String courseName = "C语言";
        String startTime = "2018-07-16 09:00:00";
        String tags = "";

        try {
            Course course = teacherService.createCourse(teacher, courseName, startTime, tags);
            System.out.println(course);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
