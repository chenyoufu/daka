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

    @Transactional
    public Student updateProfile(String wechat, String name, String sex, String sn, String specialty, String grade, String school) {
        Student student = studentMapper.getStudentByWechat(wechat);
        student.setName(name);
        student.setSpecialty(specialty);
        student.setSn(sn);
        student.setSex(sex);
        student.setGrade(grade);
        student.setSchool(school);
        studentMapper.updateStudent(student);
        return student;
    }

    @Transactional
    public Student login(String wechat) {
        Student student = studentMapper.getStudentByWechat(wechat);
        if (student != null) {
            return student;
        }
        student = new Student();
        student.setWechat(wechat);
        studentMapper.insertStudent(student);
        student = studentMapper.getStudentByWechat(wechat);
        return student;
    }

    @Transactional
    public void signInCourseByWechat(Integer studentId, Integer courseId) {
        signInMapper.insertSignIn(studentId, courseId);
    }
}
