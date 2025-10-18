package com.michael.thedoer;

import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.Date;

public class Task {
    private String name;
    private String description;
    private LocalDate createdAt;
    private LocalDate endDate;


    public Task(String name, String description, LocalDate createdAt, LocalDate endDate) {
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.endDate = endDate;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                ", endDate=" + endDate +
                '}';
    }
}
