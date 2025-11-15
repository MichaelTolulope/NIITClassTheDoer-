package com.michael.thedoer.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateTaskDto {
    @JsonProperty(required = true)
    @NotBlank(message = "userId is required")
    private Long userId;
    @JsonProperty(required = true)
    @NotBlank(message = "title is required")
    private String title;

    private String description;
    @JsonProperty(required = true)
    @NotBlank(message = "end date is required")
    @Future
    private LocalDate endDate;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

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

    public void setEndTime(LocalDate endDate) {
        this.endDate = endDate;
    }
}
