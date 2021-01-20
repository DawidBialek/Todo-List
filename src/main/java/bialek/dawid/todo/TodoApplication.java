package bialek.dawid.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class TodoApplication implements CommandLineRunner {

	@Autowired
	TaskRepository taskRepository;

	public static void main(String[] args) {
		SpringApplication.run(TodoApplication.class, args);
	}

	@Override
	public void run(String... args){
		List<Task> tasks = taskRepository.findAll();
		tasks.forEach(System.out::println);

		addTask();

	}

	private void addTask(){
		Scanner in = new Scanner(System.in);
		System.out.println("Task: ");
		String name = in.nextLine();
		System.out.println("Priority: ");
		int priority = in.nextInt();

		System.out.println("Task: " + name + " | Priority: " + priority);
		taskRepository.save(new Task(name, priority));

	}

}
