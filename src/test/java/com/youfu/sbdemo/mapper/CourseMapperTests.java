package com.youfu.sbdemo.mapper;

import com.youfu.sbdemo.domain.Course;
import com.youfu.sbdemo.domain.Teacher;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseMapperTests {
    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private TeacherMapper teacherMapper;

    @Test
//    @Transactional
    public void CourseMapperTest() {

        String wechat = "ihackx";
        Teacher teacher = teacherMapper.getTeacherByWechat(wechat);

        Course course = new Course();
        course.setName("历史");
        course.setTeacherId(teacher.getId());
        Date startTime = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String startTime = sdf.format(dt);
        course.setStartTime(startTime);
        System.out.println(course.getStartTime());
        courseMapper.insertCourse(course);

        course = courseMapper.getCourseByNameAndTeacher("历史", teacher.getId());
        Assert.assertEquals("历史", course.getName());

        course.setName("地理");
        course.setTags("选修");
        courseMapper.updateCourse(course);

        course = courseMapper.getCourseByNameAndTeacher("地理", teacher.getId());
        Assert.assertEquals("地理", course.getName());

    }
}
