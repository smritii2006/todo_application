package com.sheat.todo.repository;

import com.sheat.todo.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
public interface TaskRepository extends JpaRepository <Task,Long> {

    
}
