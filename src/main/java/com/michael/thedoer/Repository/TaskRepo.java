package com.michael.thedoer.Repository;

import com.michael.thedoer.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepo extends JpaRepository<Task, Long> {

    Task findByName(String name);
}
