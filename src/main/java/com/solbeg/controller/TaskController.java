package com.solbeg.controller;

import com.solbeg.model.Task;
import com.solbeg.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/")
    public String showTasks(Model model) {
        List<Task> activeTasks = taskService.getActiveTasksSortedByDate();
        List<Task> completedTasks = taskService.getCompletedTasksSortedByDate();
        model.addAttribute("activeTasks", activeTasks);
        model.addAttribute("completedTasks", completedTasks);
        return "tasks";
    }

    @GetMapping("/create")
    public String showCreateTaskForm(Model model) {
        model.addAttribute("task", new Task());
        return "taskAdd";
    }

    @PostMapping("/create")
    public String createTask(@ModelAttribute("task") Task task) {
        taskService.createTask(task);
        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return "redirect:/";
    }

    @PatchMapping("/complete/{id}")
    public String updateTask(@PathVariable Long id) {
        taskService.updateTask(id);
        return "redirect:/";
    }
}

