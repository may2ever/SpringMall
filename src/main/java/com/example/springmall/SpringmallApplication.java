package com.example.springmall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.springmall")
public class SpringmallApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringmallApplication.class, args);
	}
}
