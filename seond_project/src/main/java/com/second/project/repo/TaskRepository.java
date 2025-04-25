package com.second.project.repo;

import com.second.project.entity.TaskEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.repository.query.Param;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

    @Query("SELECT t FROM TaskEntity t WHERE t.userId = :userId")
    List<TaskEntity> findByUser_Id(@Param("userId") Long userId);

    List<TaskEntity> findByTitle(String title);

    @Query("SELECT t FROM TaskEntity t WHERE t.userId = :userId and t.completed = :completed")
    List<TaskEntity> findByUser_IdAndCompleted(@Param("userId") Long userId, boolean completed);

}
