package bialek.dawid.todo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {

    boolean delete(Optional<Task> l);
}
