package com.youfu.sbdemo.service;

import com.youfu.sbdemo.domain.Course;
import com.youfu.sbdemo.domain.Student;
import com.youfu.sbdemo.domain.Teacher;
import com.youfu.sbdemo.mapper.CourseMapper;
import com.youfu.sbdemo.mapper.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TeacherService {

    private TeacherMapper teacherMapper;
    private CourseMapper courseMapper;

    @Autowired
    public TeacherService(TeacherMapper teacherMapper, CourseMapper courseMapper) {
        this.teacherMapper = teacherMapper;
        this.courseMapper = courseMapper;
    }

    public Teacher getTeacher(String wechat) {
        return teacherMapper.getTeacherByWechat(wechat);
    }

    @Transactional
    public List<Course> getTeacherCourses(String wechat){
        Teacher teacher = teacherMapper.getTeacherByWechat(wechat);
        return courseMapper.getCoursesByTeacher(teacher);
    }

    public List<Student> getTeacherCourseStudents(String courseName, String wechat){
        return null;
    }

}
