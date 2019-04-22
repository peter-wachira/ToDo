import java.util.Arrays;
import java.util.List;
import org.sql2o.*;

public class Category {
    private String name;
    private int id;

    public Category(String name) {
        this.name = name;
    }

    //Category objects using the .equals() method like we discussed in the previous lesson.
    @Override
    public boolean equals(Object otherCategory) {
        if (!(otherCategory instanceof Category)) {
            return false;
        } else {
            Category newCategory = (Category) otherCategory;
            // we use == because int is a primitive, and therefore does not have access to .equals().
            return this.getName().equals(newCategory.getName())&& this.getId()==newCategory.getId();
        }
    }
    // saving new object to the database && assign the object the same id as its data in the database:
    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO categories(name) VALUES (:name)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .executeUpdate()
                    .getKey();
        }
    }

    public String getName() {
        return name;
    }

    //We'll add code to return all Category information from our categories database table in the all() method:
    public static List<Category> all() {
        String sql = "SELECT id, name FROM categories";
        //We create a query and perform executeAndFetch(Category.class);. This will execute the SQL command and turn each row of data returned into an object based on the argument. In this case we pass Category.class, which creates Category objects and stores them in a List<Category>.
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Category.class);
        }
    }
    public int getId() {
        return id;
    }


    public static Category find(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM categories where id=:id";
            Category category = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Category.class);
            return category;
        }
    }
    //To make a list of our in memory objects, we first construct a task array containing those objects. We then use a new method Arrays.asList(tasks) to save those items into a list.
     public List<Task> getTasks(){
        try(Connection con =  DB.sql2o.open()){
            String sql =  " SELECT * FROM   tasks where categoryId=:id";
            return  con.createQuery(sql)
                    .addParameter("id",this.id)
                    .executeAndFetch(Task.class);
        }
     }
}
