package com.youfu.sbdemo.mapper;

import com.youfu.sbdemo.domain.Teacher;
import org.apache.ibatis.annotations.*;
import java.util.List;


public interface TeacherMapper {

    @Select("SELECT * FROM teachers")
    List<Teacher> getAllTeachers();

    @Select("SELECT * FROM teachers WHERE wechat = #{wechat}")
    Teacher getTeacherByWechat(String wechat);

    @Insert("INSERT INTO teachers (name, wechat) VALUES (#{name}, #{wechat})")
    void insertTeacher(Teacher teacher);

    @Update("UPDATE teachers SET name = #{name}, wechat = #{wechat} WHERE id = #{id}")
    void updateTeacher(Teacher teacher);

}
