package bialek.dawid.todo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertTrue;

@SpringBootTest
class HibernateTest {

    @MockBean
    private TaskRepository taskRepository;
    private List<Task> taskList;

    @BeforeEach
    public void setup(){
        // Initialize Task mock objects for testing     
        taskList = new ArrayList<Task>();

        Task task1 = new Task();
        task1.setName("task1");
        task1.setPriority(1);

        Task task2 = new Task();
        task2.setName("task2");
        task2.setPriority(2);

        Task task3 = new Task();
        task3.setName("task3");
        task3.setPriority(5);

        Task task4 = new Task();
        task4.setName("task4");
        task4.setPriority(1);

        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);
        taskList.add(task4);
    }

    @Test
    public void testSave(){
        // given
        Task task4 = new Task();
        task4.setName("task4");
        task4.setPriority(1);

        Task task5 = new Task();
        task4.setName("task5");
        task4.setPriority(1);

        TaskRepository taskRepository = Mockito.mock(TaskRepository.class);
        Mockito.when(taskRepository.save(task4)).thenReturn(task5);

        Task taskResult = taskRepository.save(task4);

        Assertions.assertEquals(task5, taskResult);

//
//        for (Iterator iterator = taskList.iterator(); iterator.hasNext();) {
//            Task Task = (Task) iterator.next();
//
//            // insert the Task object
//            taskRepository.save(Task);
//
//
//            assertTrue(Task.getName()+" is saved - Id "+Task.getId(),Task.getId() > 0);
//        }
    }

    @Test
    public void testUpdate(){
        // get a Task object from the repository
        Task TaskObj = taskRepository.findAll().get(0);

        // assert if its not null
        Assertions.assertTrue(!TaskObj.getName().isEmpty());

        // change the task name 
        TaskObj.setName("task1-Changed");

        // update the Task object
        taskRepository.save(TaskObj);

        // assert the value changed id true
        Assertions.assertEquals("task1-Changed", taskRepository.findAll().get(0).getName());
    }

    @Test
    public void testDelete(){
        // get a CASTask object from the repository
        Task TaskObjBeforeDel = taskRepository.findAll().get(0);

        // delete the Task object
        taskRepository.delete(TaskObjBeforeDel);

        // get a Task object from the repository
        Task TaskObjAfterDel = taskRepository.findAll().get(0);

        // get the Task object ID 
        Assertions.assertNull(TaskObjAfterDel);
    }

    @Test
    public void testFind(){

        // get a Task object from the repository
        Task TaskObj = taskRepository.findAll().get(1);

        // compare the id's of passed and retrieved objects.
        Assertions.assertEquals(TaskObj.getId(), 1);
        Assertions.assertTrue(TaskObj.getId() == 1);
    }

    @Test
    public void testFindAll(){
        // get all Task object from the repository 
        List<Task> taskList = taskRepository.findAll();

        // check if it returns all records from DB
        Assertions.assertTrue(taskList.size() > 0);
        Assertions.assertEquals(taskList.size(), 4);
    }

}

