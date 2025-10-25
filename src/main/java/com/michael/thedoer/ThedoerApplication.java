package com.michael.thedoer;

import com.michael.thedoer.controller.TaskController;
import com.michael.thedoer.model.Task;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;

@SpringBootApplication
public class ThedoerApplication {

	public static void main(String[] args) {
	SpringApplication.run(ThedoerApplication.class, args);

	}

}
