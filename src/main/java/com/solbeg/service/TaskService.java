package com.solbeg.service;

import com.solbeg.model.Task;
import com.solbeg.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getActiveTasksSortedByDate() {
        return taskRepository.getActiveTasksSortedByDate();
    }

    public List<Task> getCompletedTasksSortedByDate() {
        return taskRepository.getCompletedTasksSortedByDate();
    }

    public void createTask(Task task) {
        taskRepository.saveTask(task);
    }

    public void updateTask(Long id) {
        taskRepository.updateTask(id);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteTask(id);
    }
}