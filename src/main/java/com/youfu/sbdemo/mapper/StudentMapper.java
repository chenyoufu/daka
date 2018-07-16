package com.youfu.sbdemo.mapper;

import com.youfu.sbdemo.domain.Student;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;


public interface StudentMapper {

    @Select("SELECT * FROM students")
    List<Student> getAllStudents();

    @Select("SELECT * FROM students WHERE wechat = #{wechat}")
    Student getStudentByWechat(String wechat);

    @Insert("INSERT INTO students (name, sex, sn, specialty, grade, school, wechat) " +
            "VALUES (#{name}, #{sex}, #{sn}, #{specialty}, #{grade}, #{school}, #{wechat})")
    void insertStudent(Student student);

    @Update("UPDATE students SET name = #{name}, wechat = #{wechat} WHERE id = #{id}")
    void updateStudent(Student student);

}
