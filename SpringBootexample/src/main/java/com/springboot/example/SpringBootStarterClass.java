package com.springboot.example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SpringBootStarterClass extends SpringBootServletInitializer{
	private static final Logger logger = LoggerFactory.getLogger(SpringBootStarterClass.class);
	public static void main(String[] args) {
		SpringApplication.run(SpringBootStarterClass.class, args);
		logger.info("log files are sterted");
		logger.info("information of logger");
		logger.debug("debug informatiom");

	}
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SpringBootStarterClass.class);
    }
	


}
