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

	private Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		SpringApplication.run(TodoApplication.class, args);
	}

	@Override
	public void run(String... args){
		while(true){
			System.out.println("\nMain menu options:");
			System.out.println("[0] - Show tasks");
			System.out.println("[1] - Add task");
			System.out.println("[2] - Modify task");
			System.out.println("[3] - Delete task");

			System.out.print("Input option here: ");

			int option = scanner.nextInt();
			System.out.println("You choose option " + option);
			scanner.nextLine();

			if(option == 0){
				showTasks();
			} else if(option == 1){
				addTask();
			} else if(option == 2){
				modifyTask();
			} else if(option == 3){
				deleteTask();
			}
		}

	}

	private void showTasks(){
		List<Task> tasks = taskRepository.findAll();
		tasks.forEach(System.out::println);
	}

	private void addTask(){
		System.out.print("Task: ");
		String name = scanner.nextLine();
		System.out.print("Priority: ");
		int priority = scanner.nextInt();

		System.out.println("Task: " + name + " | Priority: " + priority);
		taskRepository.save(new Task(name, priority));
		System.out.println("Task was added to the database!");

	}

	private void modifyTask(){
		System.out.print("Input task id: ");
		Long id = scanner.nextLong();
		System.out.println("After scanning for long");
		Task task = taskRepository.findById(id).get();
		System.out.println("What to modify?: ");
		System.out.println("[0] - Name");
		System.out.println("[1] - Priority");

		int option = scanner.nextInt();
		System.out.println("You choose option " + option);

		if(option == 0){
			System.out.print("Existing name: ");
			System.out.println(task.getName());
			scanner.nextLine();
			System.out.print("Set to: ");
			String name = scanner.nextLine();
			task.setName(name);

			taskRepository.save(task);

		} else if(option == 1){
			System.out.print("Existing priority: ");
			System.out.println(task.getPriority());
			System.out.print("Set to: ");
			scanner.nextLine();
			int priority = scanner.nextInt();
			task.setPriority(priority);
			taskRepository.save(task);
		}
		System.out.println("Task " + task.getName() + " modified!");
	}

	private void deleteTask(){
		System.out.print("Input task id: ");
		Long id = scanner.nextLong();
		Task task = taskRepository.findById(id).get();
		taskRepository.delete(task);
		System.out.println("Task " + task.getName() + " deleted!");
	}

}
