package config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

import chap09.HelloController;

@Configuration
public class ControllerConfig {

	@Bean
	public HelloController helloController() {
		return new HelloController();
	}
}
