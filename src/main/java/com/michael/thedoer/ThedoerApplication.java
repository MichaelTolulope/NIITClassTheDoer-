package com.michael.thedoer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;

@SpringBootApplication
public class ThedoerApplication {

	public static void main(String[] args) {
		ApplicationContext container = SpringApplication.run(ThedoerApplication.class, args);
        TaskController controller = container.getBean(TaskController.class);

        controller.createTask(new Task("wash plate","chores", LocalDate.now(), LocalDate.of(2025,12,2)));
        controller.createTask(new Task("take a walk","keeping fit", LocalDate.now(), LocalDate.of(2025,10,24)));
        controller.createTask(new Task("washing clothes","chores", LocalDate.now(), LocalDate.of(2025,10,20)));
        controller.getTasks();
	}

}
