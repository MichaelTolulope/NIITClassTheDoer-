package com.michael.thedoer;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
@Component
public class TaskController {
    private TaskDb taskDb;

    @Autowired
    public TaskController(TaskDb tasks) {
        this.taskDb = tasks;
    }

    public void createTask(Task task) {

        taskDb.getTasks().add(task);
        System.out.println("Task created");
    }
    public void getTasks() {
        ArrayList<String> tasksDisplay = new ArrayList<>();
        for (Task task : taskDb.getTasks()) {

            tasksDisplay.add(task.toString());
            System.out.println(tasksDisplay);
        }
    }

    public void deleteTask() {
    }

    public void editTask() {
    }
}
