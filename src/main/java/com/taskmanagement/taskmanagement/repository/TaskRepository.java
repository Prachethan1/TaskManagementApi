package com.taskmanagement.taskmanagement.repository;

import com.taskmanagement.taskmanagement.entity.Status;
import com.taskmanagement.taskmanagement.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    Page<Task> findAllByStatus(Status status, Pageable pageable);

    @Query("SELECT t FROM Task t JOIN t.tags tag WHERE tag.name = :tagName")
    Page<Task> findAllByTagName(String tagName, Pageable pageable);
}
