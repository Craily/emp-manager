package com.example.craily;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.example.craily.dao", "com.example.craily.cust_dao"})
public class EmpManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmpManagerApplication.class, args);
	}

}
