package com.youfu.sbdemo.service;

import com.youfu.sbdemo.domain.Course;
import com.youfu.sbdemo.domain.Student;
import com.youfu.sbdemo.mapper.SignInMapper;
import com.youfu.sbdemo.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentService {

    private SignInMapper signInMapper;
    private StudentMapper studentMapper;

    @Autowired
    public StudentService(SignInMapper signInMapper, StudentMapper studentMapper) {
        this.signInMapper = signInMapper;
        this.studentMapper = studentMapper;
    }

    public void login(String wechat, String name) {

    }

    @Transactional
    public void signInCourseByWechat(String wechat, Course course) {
        Student student = studentMapper.getStudentByWechat(wechat);
        signInMapper.insertSignIn(student.getId(), course.getId());
    }
}
