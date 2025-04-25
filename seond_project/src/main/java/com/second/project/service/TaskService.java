package com.second.project.service;

import java.util.List;

import com.second.project.entity.TaskEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.second.project.entity.UserEntity;
import com.second.project.repo.TaskRepository;
import com.second.project.repo.UserRepository;

@Service
public class TaskService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TaskRepository taskRepository;

    public List<TaskEntity> getTasks(Long userId) {
        return taskRepository.findByUser_Id(userId);
    }

    public List<TaskEntity> findByUser_IdAndCompleted(Long userId, boolean completed){
        return taskRepository.findByUser_IdAndCompleted(userId, completed);
    }


    public TaskEntity createTask(TaskEntity task, Long userId) {
        UserEntity user = userRepository.findById(userId).get();
        if (user == null) {
            return null;
        }
        task.setUser(userId);

        return taskRepository.save(task);
    }

}
