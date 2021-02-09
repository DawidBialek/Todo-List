package bialek.dawid.todo;

import bialek.dawid.todo.task.Task;
import bialek.dawid.todo.task.TaskRepository;
import bialek.dawid.todo.user.User;
import bialek.dawid.todo.user.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;

@SpringBootTest
class HibernateTest {

    @MockBean
    private TaskRepository taskRepository;
    @MockBean
    private UserRepository userRepository;
    private List<Task> taskList;

    @Test
    public void testSaveTask(){
        //given
        Task task1 = new Task();
        task1.setName("task1");
        task1.setPriority(1);

        Task task2 = new Task();
        task2.setName("task2");
        task2.setPriority(1);

        TaskRepository taskRepository = Mockito.mock(TaskRepository.class);
        given(taskRepository.save(task1)).willReturn(task2);
        //when
        Task taskResult = taskRepository.save(task1);
        //then
        Assertions.assertEquals(task2, taskResult);
        
    }

    @Test
    public void testSaveUser(){
        //given
        User user1 = new User();
        user1.setFirstName("Jan");
        user1.setLastName("Kowalski");
        user1.setPhoneNumber("620115452");

        User user2 = new User();
        user1.setFirstName("Adam");
        user1.setLastName("Nowy");
        user1.setPhoneNumber("788555120");

        UserRepository userRepository = Mockito.mock(UserRepository.class);
        given(userRepository.save(user1)).willReturn(user2);
        //when
        User userResult = userRepository.save(user1);
        //then
        Assertions.assertEquals(user2, userResult);

    }

    @Test
    public void testModifyTask(){
        //given
        Task task1 = new Task();
        task1.setName("task1");
        task1.setPriority(1);

        Task task2 = new Task();
        task2.setName("task2");
        task2.setPriority(5);

        TaskRepository taskRepository = Mockito.mock(TaskRepository.class);
        given(taskRepository.save(task1)).willReturn(task2);
        //when
        task1.setPriority(5);
        Task taskResult = taskRepository.save(task1);
        //then
        Assertions.assertEquals(5, taskResult.getPriority());
    }

    @Test
    public void testModifyUser(){
        //given
        User user1 = new User();
        user1.setFirstName("Jan");
        user1.setLastName("Kowalski");
        user1.setPhoneNumber("620115452");

        User user2 = new User();
        user2.setFirstName("Jan");
        user2.setLastName("Kowalski");
        user2.setPhoneNumber("556214368");

        UserRepository userRepository = Mockito.mock(UserRepository.class);
        given(userRepository.save(user1)).willReturn(user2);
        //when
        user1.setPhoneNumber("556214368");
        User userResult = userRepository.save(user1);
        //then
        Assertions.assertEquals("556214368", userResult.getPhoneNumber());
    }

//    public boolean delete(Long id){
//        Task byId = taskRepository.findById(id).orElseThrow(() -> new RuntimeException());
//        if (byId != null) {
//            taskRepository.delete(byId);
//        }
//        Task removed = taskRepository.findById(id).orElseThrow(() -> new RuntimeException());
//        System.out.println(removed);
//        if (removed.equals(null)) {
//            return false;
//        }
//        return true;
//    }
//
//    @Test
//    public void testDelete(){
//        //given
//        Task task1 = new Task();
//        task1.setName("task1");
//        task1.setPriority(1);
//
//        Task task2 = new Task();
//        task2.setName("task2");
//        task2.setPriority(5);
//
//        TaskRepository taskRepository = Mockito.mock(TaskRepository.class);
//        given(taskRepository.findById(1L)).willReturn(Optional.of(task2)).willReturn(null);
//        //when
//        boolean result = delete(1L);
//        //then
//        Assertions.assertEquals(false, result);
//    }

    @Test
    public void testFind(){

        //given
        Task task1 = new Task();
        task1.setName("task1");
        task1.setPriority(1);

        Task task2 = new Task();
        task2.setName("task2");
        task2.setPriority(5);

        TaskRepository taskRepository = Mockito.mock(TaskRepository.class);
        given(taskRepository.findById(1L)).willReturn(Optional.of(task2));
        //when
        Task taskResult = taskRepository.findById(1L).get();
        //then
        Assertions.assertEquals(task2, taskResult);
    }

    @Test
    public void testFindAll(){
        //given
        TaskRepository taskRepository = Mockito.mock(TaskRepository.class);
        given(taskRepository.findAll()).willReturn(prepareMockTasksData());

        // get all Task object from the repository
        List<Task> taskList = taskRepository.findAll();

        // check if it returns all records from DB
        Assertions.assertTrue(taskList.size() > 0);
        Assertions.assertEquals(taskList.size(), 3);
    }

    private List<Task> prepareMockTasksData(){
        Task task1 = new Task();
        task1.setName("task1");
        task1.setPriority(1);

        Task task2 = new Task();
        task2.setName("task2");
        task2.setPriority(5);

        Task task3 = new Task();
        task3.setName("task3");
        task3.setPriority(6);

        List<Task> taskList = new ArrayList();
        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);

        return taskList;
    }

}

