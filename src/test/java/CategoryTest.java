import java.util.Arrays;
import org.junit.*;
import static org.junit.Assert.*;

public class CategoryTest {
//we need to be able to retrieve all Tasks saved into a specific Category. We'll begin by writing a spec:
    @Test
    public void getTasks_retrievesALlTasksFromDatabase_tasksList() {
        Category myCategory = new Category("Household chores");
        myCategory.save();
        Task firstTask = new Task("Mow the lawn", myCategory.getId());
        firstTask.save();
        Task secondTask = new Task("Do the dishes", myCategory.getId());
        secondTask.save();
        Task[] tasks = new Task[] { firstTask, secondTask };
        assertTrue(myCategory.getTasks().containsAll(Arrays.asList(tasks)));
    }
    //Next, we need to be able to compare Category objects using the .equals() method like we discussed in the previous lesson. This is a brand new behavior in the Category class, so we'll begin with a spec:
    @Test
    public void equals_returnsTrueIfNamesAretheSame() {
        Category firstCategory = new Category("Household chores");
        Category secondCategory = new Category("Household chores");
        assertTrue(firstCategory.equals(secondCategory));
    }
    //With an overridden equals() method in place, we can now test saving new Category objects into the database. Because we'll create a new method to insert Category object into the database, we'll begin with a test:
    @Test
    public void save_savesIntoDatabase_true() {
        Category myCategory = new Category("Household chores");
        myCategory.save();
        assertTrue(Category.all().get(0).equals(myCategory));
    }
    //When our Category is saved into the database, it is assigned a unique id. We want Category objects to have the same id as their data in the database. We'll write a spec to test this behavior:
    @Test
    public void save_assignsIdToObject() {
        Category myCategory = new Category("Household chores");
        myCategory.save();
        Category savedCategory = Category.all().get(0);
        assertEquals(myCategory.getId(), savedCategory.getId());
    }
    @Test
    public void category_instantiatesCorrectly_true() {
        Category testCategory = new Category("Home");
        assertEquals(true, testCategory instanceof Category);
    }
    @Test
    public void getName_categoryInstantiatesWithName_Home() {
        Category testCategory = new Category("Home");
        assertEquals("Home", testCategory.getName());
    }
    //We can also update one of our existing tests to use our new .save() method:
    @Test
    public void all_returnsAllInstancesOfCategory_true() {
        Category firstCategory = new Category("Home");
        firstCategory.save();
        Category secondCategory = new Category("Work");
        secondCategory.save();
        assertEquals(true, Category.all().get(0).equals(firstCategory));
        assertEquals(true, Category.all().get(1).equals(secondCategory));
    }
    // Now that we have a save() method to save Category objects to our database, we'll alter this test slightly:
    @Test
    public void find_returnsCategoryWithSameId_secondCategory() {
        Category firstCategory = new Category("Home");
        firstCategory.save();
        Category secondCategory = new Category("Work");
        secondCategory.save();
        assertEquals(Category.find(secondCategory.getId()), secondCategory);
    }

    @Test
    public void getId_categoriesInstantiateWithAnId_1() {
        Category testCategory = new Category("Home");
        testCategory.save();
        assertTrue(testCategory.getId()>0);
    }

}
