package com.youfu.sbdemo;

import com.youfu.sbdemo.mapper.TeacherMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@MapperScan("com.youfu.sbdemo.mapper")
public class SbdemoApplication {

    private final TeacherMapper teacherMapper;

    public SbdemoApplication(TeacherMapper teacherMapper) {
        this.teacherMapper = teacherMapper;
    }

    @RequestMapping("/")
    public String index() {
        return "welcome";
    }

    public static void main(String[] args) {
        SpringApplication.run(SbdemoApplication.class, args);
    }

}
