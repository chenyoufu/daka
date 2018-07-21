package com.youfu.sbdemo.controller;

import com.youfu.sbdemo.domain.Course;
import com.youfu.sbdemo.domain.Result;
import com.youfu.sbdemo.domain.Student;
import com.youfu.sbdemo.domain.Teacher;
import com.youfu.sbdemo.service.CourseService;
import com.youfu.sbdemo.service.TeacherService;
import com.youfu.sbdemo.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TeacherController {
    private final static Logger logger = LoggerFactory.getLogger(TeacherController.class);

    private final TeacherService teacherService;
    private final CourseService courseService;

    @Autowired
    public TeacherController(TeacherService teacherService, CourseService courseService) {
        this.teacherService = teacherService;
        this.courseService = courseService;
    }

    // 登陆
    @GetMapping(value = "/teachers/{wechat}")
    public Result<Teacher> login(@PathVariable("wechat") String wechat) {
        return ResultUtil.success(teacherService.login(wechat));
    }

    // 获取课程信息
    @GetMapping(value = "/teachers/{wechat}/courses")
    public List<Course> courseList(@PathVariable("wechat") String wechat) {
        logger.info("courseList");
        return teacherService.getTeacherCourses(wechat);
    }

    // 更新个人信息
    @PutMapping(value = "/teachers/{wechat}")
    public Result<Teacher> teacherUpdate(@PathVariable("wechat") String wechat,
                                         @RequestBody Map<String, Object> payload) {
        String name = (String) payload.getOrDefault("name", "");
        if (name.equals("")) {
            return ResultUtil.error(100, "姓名不能为空");
        }
        Teacher teacher = teacherService.updateProfile(wechat, name);
        return ResultUtil.success(teacher);
    }

    // 添加课程
    @PostMapping(value = "/teachers/{wechat}/course")
    public Result<Object> courseAdd(@PathVariable("wechat") String wechat,
                                    @RequestBody Map<String, Object> payload) {
        String courseName = (String) payload.getOrDefault("courseName", "");
        if (courseName.equals("")) {
            return ResultUtil.error(100, "课程名不能为空");
        }
        String courseStartTime = (String) payload.getOrDefault("courseStartTime", "");
        if (courseStartTime.equals("")) {
            return ResultUtil.error(100, "上课时间不能为空");
        }
        String courseTags = (String) payload.getOrDefault("courseTags", "");

        Teacher teacher = teacherService.getTeacher(wechat);
        try {
            Course course = teacherService.createCourse(teacher, courseName, courseStartTime, courseTags);
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("wechat", teacher.getWechat());
            hashMap.put("courseName", course.getName());
            hashMap.put("courseStartTime", course.getStartTime());
            hashMap.put("teacherName", teacher.getName());
            return ResultUtil.success(hashMap);
        } catch (ParseException e) {
            return ResultUtil.error(100, "时间格式错误");
        }
    }

    // 获取某个课程的学生打卡信息
    @GetMapping(value = "/teachers/{wechat}/courses/{courseId}/students")
    public Result<Object> getSignInStudents(@PathVariable("wechat") String wechat,
                                            @PathVariable("courseId") Integer courseId) {

        Teacher teacher = teacherService.getTeacher(wechat);
        Course course = courseService.getCourseById(courseId);
        List<Student> students = teacherService.getTeacherCourseStudents(courseId, wechat);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("teacher", teacher.getName());
        hashMap.put("wechat", wechat);
        hashMap.put("course", course.getName());
        hashMap.put("students", students);
        return ResultUtil.success(hashMap);
    }

}
