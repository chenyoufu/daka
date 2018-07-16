package com.youfu.sbdemo.mapper;

import com.youfu.sbdemo.domain.SignIn;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SignInMapper {

    @Select("SELECT * FROM sign_in WHERE teacher_id = #{teacherId}")
    List<SignIn> getSignInsByTeacherId(Integer teacherId);

    @Insert("INSERT INTO sign_in (teacher_id, student_id, course_id) VALUES (#{teacherId}, #{studentId}, #{courseId})")
    void insertSignIn(Integer teacherId, Integer studentId, Integer courseId);

}
