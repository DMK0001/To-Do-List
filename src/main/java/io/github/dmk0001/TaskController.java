package io.github.dmk0001;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class TaskController {

    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("/")
    public String showIndex(Model model) {
        model.addAttribute("tasks", taskRepository.findAll());
        model.addAttribute("task", new Task());
        return "index";
    }

    @PostMapping("/tasks/add")
    public String addTask(@Valid @ModelAttribute Task task, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()){
            model.addAttribute("tasks", taskRepository.findAll());
            return "index";
        }
        taskRepository.save(task);
        return "redirect:/";
    }

    @PostMapping("/tasks/{id}/update")
    public String updateTask(@PathVariable Long id){
        Task task = taskRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not exist!"));
        task.setDone(!task.isDone());
        taskRepository.save(task);
        return "redirect:/";
    }

    @PostMapping("/tasks/{id}/delete")
    public String deleteTask(@PathVariable Long id){
        if (!taskRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found!");
        }
        taskRepository.deleteById(id);
        return "redirect:/";
    }
}