package com.arthurb.PlatInvest;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@EnableEncryptableProperties
public class PlatInvestApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlatInvestApplication.class, args);
	}

}
