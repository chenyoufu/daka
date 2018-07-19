package com.youfu.sbdemo.service;

import com.youfu.sbdemo.domain.Course;
import com.youfu.sbdemo.domain.Student;
import com.youfu.sbdemo.domain.Teacher;
import com.youfu.sbdemo.mapper.CourseMapper;
import com.youfu.sbdemo.mapper.SignInMapper;
import com.youfu.sbdemo.mapper.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class TeacherService {

    private TeacherMapper teacherMapper;
    private CourseMapper courseMapper;
    private SignInMapper signInMapper;

    @Autowired
    public TeacherService(TeacherMapper teacherMapper, CourseMapper courseMapper, SignInMapper signInMapper) {
        this.teacherMapper = teacherMapper;
        this.courseMapper = courseMapper;
        this.signInMapper = signInMapper;
    }

    public Teacher getTeacher(String wechat) {
        return teacherMapper.getTeacherByWechat(wechat);
    }

    @Transactional
    public List<Course> getTeacherCourses(String wechat){
        Teacher teacher = teacherMapper.getTeacherByWechat(wechat);
        return courseMapper.getCoursesByTeacher(teacher);
    }

    @Transactional
    public List<Student> getTeacherCourseStudents(String courseName, String wechat){
        Teacher teacher = teacherMapper.getTeacherByWechat(wechat);
        Course course = courseMapper.getCourseByNameAndTeacher(courseName, teacher.getId());
        List<Student> students = signInMapper.getSignInStudentsByCourse(course);
        return students;
    }

    @Transactional
    public Teacher updateProfile(String wechat, String name) {
        Teacher teacher = teacherMapper.getTeacherByWechat(wechat);
        teacher.setName(name);
        teacherMapper.updateTeacher(teacher);
        teacher = teacherMapper.getTeacherByWechat(wechat);
        return teacher;
    }

    @Transactional
    public Teacher login(String wechat) {
        Teacher teacher = teacherMapper.getTeacherByWechat(wechat);
        if (teacher != null) {
            System.out.println("createTIme: " + teacher.getCreateTime());
            return teacher;
        }
        teacher = new Teacher();
        teacher.setWechat(wechat);
        teacherMapper.insertTeacher(teacher);
        teacher = teacherMapper.getTeacherByWechat(wechat);
        return teacher;
    }

    @Transactional
    public Course createCourse(Teacher teacher, String courseName, String startTime, String tags) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Course course = new Course();
        course.setTeacherId(teacher.getId());
        course.setName(courseName);
        course.setStartTime(sdf.parse(startTime));
        course.setTags(tags);
        courseMapper.insertCourse(course);
        return course;
    }
}
