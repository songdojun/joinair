package com.example.joinair;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.joinair.mapper")
public class JoinairApplication {

	public static void main(String[] args) {
		SpringApplication.run(JoinairApplication.class, args);
	}
}
