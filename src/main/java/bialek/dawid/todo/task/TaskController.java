package bialek.dawid.todo.task;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {

    private final TaskRepository repository;

    TaskController(TaskRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/Tasks")
    List<Task> all() {
        return repository.findAll();
    }

    @PostMapping("/Tasks")
    Task newTask(@RequestBody Task newTask) {
        return repository.save(newTask);
    }

    @GetMapping("/Tasks/{id}")
    Task one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException());
    }

    @PutMapping("/Tasks/{id}")
    Task replaceTask(@RequestBody Task newTask, @PathVariable Long id) {

        return repository.findById(id)
                .map(Task -> {
                    Task.setName(newTask.getName());
                    Task.setPriority(newTask.getPriority());
                    return repository.save(Task);
                })
                .orElseGet(() -> {
                    newTask.setId(id);
                    return repository.save(newTask);
                });
    }

    @DeleteMapping("/Tasks/{id}")
    void deleteTask(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
