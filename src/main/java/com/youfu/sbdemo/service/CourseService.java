package com.youfu.sbdemo.service;

import com.youfu.sbdemo.domain.Course;
import com.youfu.sbdemo.mapper.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
    private final CourseMapper courseMapper;

    @Autowired
    public CourseService(CourseMapper courseMapper) {
        this.courseMapper = courseMapper;
    }

    public Course getCourseById(Integer id) {
        return courseMapper.getCoursesById(id);
    }
}
