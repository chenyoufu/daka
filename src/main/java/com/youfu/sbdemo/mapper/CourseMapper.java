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

    @Select("SELECT * FROM courses WHERE teacher_id=#{id}")
    List<Course> getCoursesByTeacher(Teacher teacher);

    @Select("SELECT * FROM courses WHERE name = #{name} AND teacher_id = #{teacherId}")
    Course getCourseByNameAndTeacher(@Param("name") String name, @Param("teacherId") Integer teacherId);

    @Insert("INSERT INTO courses (tags, name, teacher_id, start_time, end_time) VALUES (#{tags}, #{name}, #{teacherId}, #{startTime}, #{endTime, jdbcType=TIMESTAMP})")
    void insertCourse(Course course);

    @Update("UPDATE courses SET tags = #{tags}, name = #{name} WHERE id = #{id}")
    void updateCourse(Course course);

}
