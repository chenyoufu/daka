package com.youfu.sbdemo.mapper;

import com.youfu.sbdemo.domain.Course;
import com.youfu.sbdemo.domain.Student;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SignInMapper {

    @Select("SELECT * FROM students INNER JOIN sign_in on students.id= sign_in.student_id WHERE course_id = #{id}")
    List<Student> getSignInStudentsByCourse(Course course);

    @Insert("INSERT INTO sign_in (student_id, course_id) VALUES (#{studentId}, #{courseId})")
    void insertSignIn(@Param("studentId") Integer studentId, @Param("courseId") Integer courseId);

}
