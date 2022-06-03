package ru.vstu.ueemodule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class UeeModuleApplication {

	public static void main(String[] args) {
		SpringApplication.run(UeeModuleApplication.class, args);
	}

}
