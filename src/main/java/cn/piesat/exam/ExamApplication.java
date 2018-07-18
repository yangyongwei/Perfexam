package cn.piesat.exam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
//@MapperScan("cn.piesat.exam.mapper")
public class ExamApplication extends SpringBootServletInitializer {



    public static void main(String[] args) {
        SpringApplication.run(ExamApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ExamApplication.class);
    }
}