package com.youfu.sbdemo.controller;

import com.youfu.sbdemo.domain.Course;
import com.youfu.sbdemo.domain.Result;
import com.youfu.sbdemo.domain.Teacher;
import com.youfu.sbdemo.service.TeacherService;
import com.youfu.sbdemo.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
public class TeacherController {
    private final static Logger logger = LoggerFactory.getLogger(TeacherController.class);

    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping(value = "/teachers/{wechat}")
    public Result<Teacher> login(@PathVariable("wechat") String wechat) {
        return ResultUtil.success(teacherService.login(wechat));
    }

    @GetMapping(value = "/teachers/{wechat}/courses")
    public List<Course> courseList(@PathVariable("wechat") String wechat) {
        logger.info("courseList");
        return teacherService.getTeacherCourses(wechat);
    }

    @PutMapping(value = "/teachers/{wechat}")
    public Result<Teacher> teacherUpdate(@PathVariable("wechat") String wechat,
                                @RequestParam("name") String name) {
        Teacher teacher = teacherService.updateProfile(wechat, name);
        return ResultUtil.success(teacher);
    }

    @PostMapping(value = "/teachers/{wechat}/course")
    public Result<Course> courseAdd(@PathVariable("wechat") String wechat,
                                    @RequestParam("courseName") String courseName,
                                    @RequestParam("courseStartTime") String courseStartTime,
                                    @RequestParam("courseTags") String courseTags) {
        Teacher teacher = teacherService.getTeacher(wechat);
        try {
            Course course = teacherService.createCourse(teacher, courseName, courseStartTime, courseTags);
            return ResultUtil.success(course);
        } catch (ParseException e) {
            return ResultUtil.error(100, "时间格式错误");
        }
    }

}
