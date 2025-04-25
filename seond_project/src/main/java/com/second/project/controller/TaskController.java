package com.second.project.controller;

import java.util.List;

import com.second.project.entity.TaskEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.second.project.response.APIResponse;
import com.second.project.service.ResponseService;
import com.second.project.service.TaskService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/tasks")
public class TaskController {

    @Autowired
    private ResponseService<TaskEntity> responseServiceTask;

    @Autowired
    private ResponseService<List<TaskEntity>> responseServiceList;

    @Autowired
    private TaskService taskService;

    @GetMapping("/{user_id}")
    public ResponseEntity<APIResponse<List<TaskEntity>>> getAllTasks(@PathVariable("user_id") Long userId) {
        try {
            List<TaskEntity> tasks = taskService.getTasks(userId);
            return responseServiceList.returnResponse("Tasks Fetched successfully", 200, tasks);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return responseServiceList.returnResponseError(e.getMessage());
        }
    }

    @GetMapping("/is-completed/{user_id}")
    public ResponseEntity<APIResponse<List<TaskEntity>>> getTasksByCompleted(@PathVariable("user_id") Long userId, @RequestParam("completed") boolean completed) {
        try {
            List<TaskEntity> tasks = taskService.findByUser_IdAndCompleted(userId, completed);
            return responseServiceList.returnResponse("Tasks Fetched successfully", 200, tasks);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return responseServiceList.returnResponseError(e.getMessage());
        }
    }

    @PostMapping("/{user_id}")
    public ResponseEntity<APIResponse<TaskEntity>> createTask(@Valid @RequestBody TaskEntity task, @PathVariable("user_id") Long userId) {
        try {
            TaskEntity createdTask = taskService.createTask(task, userId);
            return responseServiceTask.returnResponse("Task created successfully", 200, createdTask);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return responseServiceTask.returnResponseError(e.getMessage());
        }
    }
}
