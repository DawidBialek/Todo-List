package bialek.dawid.todo;

import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.List;

@RestController
public class TaskController {

    private final TaskRepository repository;

    TaskController(TaskRepository repository) {
        this.repository = repository;
    }


    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/Tasks")
    List<Task> all() {
        return repository.findAll();
    }
    // end::get-aggregate-root[]

    @PostMapping("/Tasks")
    Task newTask(@RequestBody Task newTask) {
        return repository.save(newTask);
    }

    // Single item

    @GetMapping("/Tasks/{id}")
    Task one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
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
