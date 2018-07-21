package com.youfu.sbdemo.mapper;

import com.youfu.sbdemo.domain.Course;
import com.youfu.sbdemo.domain.Teacher;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface CourseMapper {

    @Select("SELECT * FROM courses")
    List<Course> getAllCourses();

    @Select("SELECT * FROM courses WHERE id=#{id}")
    Course getCoursesById(Integer id);

    @Select("SELECT * FROM courses WHERE teacher_id=#{id}")
    List<Course> getCoursesByTeacher(Teacher teacher);

    @Select("SELECT * FROM courses WHERE id = #{courseId} AND teacher_id = #{teacherId}")
    Course getCourseByIdAndTeacher(@Param("courseId") Integer courseId, @Param("teacherId") Integer teacherId);

    @Insert("INSERT INTO courses (tags, name, teacher_id, start_time, end_time) VALUES (#{tags}, #{name}, #{teacherId}, #{startTime}, #{endTime})")
    void insertCourse(Course course);

    @Update("UPDATE courses SET tags = #{tags}, name = #{name}, start_time=#{startTime}, end_time=#{endTime} WHERE id = #{id}")
    void updateCourse(Course course);

}
