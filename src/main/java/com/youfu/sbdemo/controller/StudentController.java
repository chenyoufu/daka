package com.youfu.sbdemo.controller;

import com.youfu.sbdemo.domain.Result;
import com.youfu.sbdemo.domain.Student;
import com.youfu.sbdemo.service.StudentService;
import com.youfu.sbdemo.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class StudentController {
    private final static Logger logger = LoggerFactory.getLogger(TeacherController.class);

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(value = "/students/{wechat}")
    public Result<Student> login(@PathVariable("wechat") String wechat) {
        return ResultUtil.success(studentService.login(wechat));
    }

    @PutMapping(value = "/students/{wechat}")
    public Result<Student> teacherUpdate(@PathVariable("wechat") String wechat,
                                         @RequestBody Map<String, Object> payload) {
        String name = (String) payload.getOrDefault("name", "");
        String sex = (String) payload.getOrDefault("sex", "");
        String sn = (String) payload.getOrDefault("sn", "");
        String specialty = (String) payload.getOrDefault("specialty", "");
        String grade = (String) payload.getOrDefault("grade", "");
        String school = (String) payload.getOrDefault("school", "");
        Student student = studentService.updateProfile(wechat, name, sex, sn, specialty, grade, school);
        return ResultUtil.success(student);
    }

    @PostMapping(value = "/students/{wechat}/courses/{courseId}")
    public Result<Object> signIn(@PathVariable("wechat") String wechat,
                                  @PathVariable("courseId") Integer courseId) {
        Student student = studentService.login(wechat);
        studentService.signInCourseByWechat(student.getId(), courseId);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("wechat", student.getWechat());
        return ResultUtil.success(hashMap);
    }
}
