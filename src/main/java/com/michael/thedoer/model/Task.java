package com.michael.thedoer.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="tasks")
public class Task {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name = "task-name", nullable = false)
    private String name;
    @Column(name = "task-description", nullable = false)
    private String description;
    @Column(name = "created-at", nullable = false)
    private LocalDate createdAt;
    @Column(name = "end-date", nullable = false )
    private LocalDate endDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
