package com.youfu.sbdemo.mapper;

import com.youfu.sbdemo.domain.Teacher;
import org.apache.ibatis.annotations.*;
import java.util.List;


public interface TeacherMapper {

    @Select("SELECT * FROM teachers")
    List<Teacher> getAllTeachers();

    @Select("SELECT * FROM teachers WHERE wechat = #{wechat}")
    Teacher getTeacherByWechat(String wechat);

    // 如果这边不添加@Param和在sql语句中明确指定teacher.name 那么无法通过teacher.getId()获得自动生成的id。
    @Insert("INSERT INTO teachers (name, wechat) VALUES (#{teacher.name}, #{teacher.wechat})")
    @Options(useGeneratedKeys = true, keyProperty = "teacher.id")
    void insertTeacher(@Param("teacher") Teacher teacher);

    @Update("UPDATE teachers SET name = #{name} WHERE id = #{id}")
    void updateTeacher(Teacher teacher);

}
