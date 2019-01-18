package com.mojito.mojitoboot;

import com.mojito.mojitoboot.common.mapper.UserDOMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = {"com.mojito.mojitoboot"})
@MapperScan(basePackages = "com.mojito.mojitoboot.common.mapper")
public class MojitoBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(MojitoBootApplication.class, args);
	}

}

