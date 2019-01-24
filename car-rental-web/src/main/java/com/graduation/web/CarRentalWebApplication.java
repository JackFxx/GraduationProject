package com.graduation.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.graduation.dao.mapper")
@ComponentScan("com.graduation.service")
@ComponentScan("com.graduation.res")
public class CarRentalWebApplication {
	public static void main(String[] args) {
		SpringApplication.run(CarRentalWebApplication.class, args);
	}

}

