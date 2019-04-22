//import java.lang.String;
//import org.sql2o.*;
//import org.junit.*;
//import static org.junit.Assert.*;
//import java.time.LocalDateTime;
//public class TaskTest {
//
//
//    @Before
//    public void setUp() {
//        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/to_do_test", "alpha", "pw*0711937973");
//    }
//    @After
//    public void tearDown() {
//        try(Connection con = DB.sql2o.open()) {
//            String deleteTasksQuery = "DELETE FROM tasks *;";
//            String deleteCategoriesQuery = "DELETE FROM categories *;";
//            con.createQuery(deleteTasksQuery).executeUpdate();
//            con.createQuery(deleteCategoriesQuery).executeUpdate();
//        }
//    }
//    //Next, in order to compare objects we retrieve from the database with our code above, we must override the .equals() method
//    @Test
//    public void equals_returnsTrueIfDescriptionsAretheSame() {
//        Task firstTask = new Task("Mow the lawn",2);
//        Task secondTask = new Task("Mow the lawn",2);
//        assertTrue(firstTask.equals(secondTask));
//    }
//    //We'll create a brand new method to save objects into our database. But, as always, we must begin with a test, This test, therefore, confirms that we can successfully create a Task l, save it, and return the same object from our database.
//    @Test
//    public void save_returnsTrueIfDescriptionsAretheSame() {
//        Task myTask = new Task("Mow the lawn",2);
//        myTask.save();
//        assertTrue(Task.all().get(0).equals(myTask));
//    }
//    //we'll write a unit test that ensures we can save a categoryId into our tasks table, thereby associating a Task with its Category:
//    @Test
//    public void save_savesCategoryIdIntoDB_true() {
//        Category myCategory = new Category("Household chores");
//        myCategory.save();
//        Task myTask = new Task("Mow the lawn", myCategory.getId());
//        myTask.save();
//        //In the Task constructor we use myCategory.getId() to reference an actual Category id saved in our database.
//        Task savedTask = Task.find(myTask.getId());
//        //then, we find our new Task and compare its categoryId with myCategory.getId() to ensure it correctly contains the ID for the Category it belongs to.
//        assertEquals(savedTask.getCategoryId(), myCategory.getId());
//    }
//    @Test
//    public void Task_instantiatesCorrectly_true() {
//        Task myTask = new Task("Mow the lawn",2);
//        assertEquals(true, myTask instanceof Task);
//    }
//    @Test
//    public void Task_instantiatesWithDescription_String() {
//        Task myTask = new Task("Mow the lawn",2);
//        assertEquals("Mow the lawn", myTask.getDescription());
//    }
//    @Test
//    public void isCompleted_isFalseAfterInstantiation_false() {
//        Task myTask = new Task("Mow the lawn",2);
//        assertEquals(false, myTask.isCompleted());
//    }
//    @Test
//    public void getCreatedAt_instantiatesWithCurrentTime_today() {
//        Task myTask = new Task("Mow the lawn",2);
//        assertEquals(LocalDateTime.now().getDayOfWeek(), myTask.getCreatedAt().getDayOfWeek());
//    }
//    //We'll also update one of our existing tests to use our new .save() method:
//    @Test
//    public void all_returnsAllInstancesOfCategory_true() {
//        Category firstCategory = new Category("Home");
//        firstCategory.save();
//        Category secondCategory = new Category("Work");
//        secondCategory.save();
//        assertEquals(true, Category.all().get(0).equals(firstCategory));
//        assertEquals(true, Category.all().get(1).equals(secondCategory));
//    }
//    //When our Task is saved into the database, it is assigned a unique id. Task objects in our application should have the same id as their rows of corresponding data in our database. That way, we'll be able to easily locate an object's data in our database with its id property.
//    @Test
//    public void save_assignsIdToObject() {
//        Category myCategory = new Category("Household chores");
//        myCategory.save();
//        Category savedCategory = Category.all().get(0);
//        assertEquals(myCategory.getId(), savedCategory.getId());
//    }
//    //Database-assigned IDs don't always restart at 1 after we clear our database, so to prevent erroneously passing tests, this test will simply confirm that the Task possesses an id property greater than 0.
//    @Test
//    public void getId_tasksInstantiateWithAnID() {
////        Category myCategory = new Category("Household chores");
////        myCategory.save();
//        Task myTask = new Task("Mow the lawn");
//        myTask.save();
//        assertTrue(myTask.getId() > 0);
//    }
//    //Now, we also need to be able to locate specific items from our database. We already have a test for our previous find() method. Now that we have a save() method to insert Tasks to our database let's alter this test:
//    @Test
//    public void find_returnsTaskWithSameId_secondTask() {
//        Task firstTask = new Task("Mow the lawn",2);
//        firstTask.save();
//        Task secondTask = new Task("Buy groceries",2);
//        secondTask.save();
//        //Our Task.find(myTask.getId()) uses the Task's id to query the database and return a Task object.
//        assertEquals(Task.find(secondTask.getId()), secondTask);
//    }
//
//
//}

import org.junit.*;
import static org.junit.Assert.*;
import java.time.LocalDateTime;
import org.sql2o.*;
public class TaskTest {

    @Before
    public void setUp() {
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/to_do_test", "alpha", "pw*0711937973");
    }

    @After
    public void tearDown() {
        try(Connection con = DB.sql2o.open()) {
            String deleteTasksQuery = "DELETE FROM tasks *;";
            String deleteCategoriesQuery = "DELETE FROM categories *;";
            con.createQuery(deleteTasksQuery).executeUpdate();
            con.createQuery(deleteCategoriesQuery).executeUpdate();
        }
    }

    @Test
    public void Task_instantiatesCorrectly_true() {
        Task myTask = new Task("Mow the lawn",1);
        assertEquals(true, myTask instanceof Task);
    }

    @Test
    public void Task_instantiatesWithDescription_String() {
        Task myTask = new Task("Mow the lawn",1);
        assertEquals("Mow the lawn", myTask.getDescription());
    }

    @Test
    public void isCompleted_isFalseAfterInstantiation_false() {
        Task myTask = new Task("Mow the lawn",1);
        assertEquals(false, myTask.isCompleted());
    }

    @Test
    public void getCreatedAt_instantiatesWithCurrentTime_today() {
        Task myTask = new Task("Mow the lawn",1);
        assertEquals(LocalDateTime.now().getDayOfWeek(), myTask.getCreatedAt().getDayOfWeek());
    }

    @Test
    public void all_returnsAllInstancesOfTask_true() {
        Task firstTask = new Task("Mow the lawn",1);
        firstTask.save();
        Task secondTask = new Task("Buy groceries",1);
        secondTask.save();
        assertEquals(true, Task.all().get(0).equals(firstTask));
        assertEquals(true, Task.all().get(1).equals(secondTask));
    }


    @Test
    public void getId_tasksInstantiateWithAnID() {
        Task myTask = new Task("Mow the lawn",1);
        myTask.save();
        assertTrue(myTask.getId() > 0);
    }

//  @Test
//  public void find_returnsTaskWithSameId_secondTask() {
//    Task firstTask = new Task("Mow the lawn");
//    Task secondTask = new Task("Buy groceries");
//    assertEquals(Task.find(secondTask.getId()), secondTask);
//  }

    @Test
    public void equals_returnsTrueIfDescriptionsAretheSame() {
        Task firstTask = new Task("Mow the lawn",1);
        Task secondTask = new Task("Mow the lawn",1);
        assertTrue(firstTask.equals(secondTask));
    }

    @Test
    public void save_returnsTrueIfDescriptionsAretheSame() {
        Task myTask = new Task("Mow the lawn",1);
        myTask.save();
        assertTrue(Task.all().get(0).equals(myTask));
    }
//  @Test
//  public void getId_tasksInstantiateWithAnID_1() {
//
//    Task myTask = new Task("Mow the lawn",1);
//    assertEquals(1, myTask.getId());
//  }

    @Test
    public void save_assignsIdToObject() {
        Task myTask = new Task("Mow the lawn",1);
        myTask.save();
        Task savedTask = Task.all().get(0);
        assertEquals(myTask.getId(), savedTask.getId());
    }
    @Test
    public void find_returnsTaskWithSameId_secondTask() {
        Task firstTask = new Task("Mow the lawn",1);
        firstTask.save();
        Task secondTask = new Task("Buy groceries",1);
        secondTask.save();
        assertEquals(Task.find(secondTask.getId()), secondTask);
    }

    //Let's add functionality to allow users to update a particular Task in our To Do List.
    @Test
    public void update_updatesTaskDescription_true() {
        Task myTask = new Task("Mow the lawn", 1);
        myTask.save();
        myTask.update("Take a nap");
        assertEquals("Take a nap", Task.find(myTask.getId()).getDescription());
    }
    //We'll add the functionality to allow users to delete individual Taskitems in their To Do List
    @Test
    public void delete_deletesTask_true() {
        Task myTask = new Task("Mow the lawn", 1);
        myTask.save();
        int myTaskId = myTask.getId();
        myTask.delete();
        assertEquals(null, Task.find(myTaskId));
    }



}







