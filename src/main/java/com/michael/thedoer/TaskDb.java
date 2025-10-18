package com.michael.thedoer;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
@Component
public class TaskDb {
    private ArrayList<Task> tasks = new ArrayList<>();


    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

}
