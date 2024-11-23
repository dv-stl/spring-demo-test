package com.example.simple;

import com.example.simple.services.InputTransformer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SimpleApplication {

	public static void main(String[] args)
	{
		System.out.println("I am SimpleApplication");
		ConfigurableApplicationContext context = SpringApplication.run(SimpleApplication.class, args);

		InputTransformer inputTransformer = context.getBean(InputTransformer.class);
		System.out.println("The magic number is: " + inputTransformer.transform());
	}

}
