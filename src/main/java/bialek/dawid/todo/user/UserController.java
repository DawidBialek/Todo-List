package bialek.dawid.todo.user;

import bialek.dawid.todo.user.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final UserRepository repository;

    UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/Users")
    List<User> all() {
        return repository.findAll();
    }

    @PostMapping("/Users")
    User newUser(@RequestBody User newUser) {
        return repository.save(newUser);
    }

    @GetMapping("/Users/{id}")
    User one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException());
    }

    @PutMapping("/Users/{id}")
    User replaceUser(@RequestBody User newUser, @PathVariable Long id) {

        return repository.findById(id)
                .map(User -> {
                    User.setFirstName(newUser.getFirstName());
                    User.setLastName(newUser.getLastName());
                    User.setPhoneNumber(newUser.getPhoneNumber());
                    return repository.save(User);
                })
                .orElseGet(() -> {
                    newUser.setId(id);
                    return repository.save(newUser);
                });
    }

    @DeleteMapping("/Users/{id}")
    void deleteUser(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
