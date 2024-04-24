package com.clockwise.api;

import com.clockwise.api.dto.UserDto;
import com.clockwise.api.service.RootService;
import com.clockwise.api.util.InitRoot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;

@SpringBootApplication
public class CwApiApplication {

	public static void main(String[] args) {

		SpringApplication.run(CwApiApplication.class, args);

		InitRoot initRoot = new InitRoot();

		initRoot.init();

		initRoot = null;

	}

}
