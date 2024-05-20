package com.sparta.schedulemanagement_spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ScheduleManagementSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScheduleManagementSpringApplication.class, args);
    }

}
