package com.michael.thedoer.dto;

import jakarta.validation.constraints.FutureOrPresent;

import java.time.LocalDate;

public class UpdateUserTaskDto {
    private String title;
    private String description;
    @FutureOrPresent
    private LocalDate endDate;



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
