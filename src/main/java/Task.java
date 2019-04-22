//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//import org.sql2o.*;
//
//public class Task {
//    private String description;
//    private boolean completed;
//    private LocalDateTime createdAt;
//    private int id;
//    private int categoryId;
////We'll update our Task class now. Remember, in a one-to-many relationship the object there are 'many' of (Task) holds the ID of the object there is one of (Category). Task objects will therefore contain an ID to reference the Category they belong to.
//
//    public Task(String description, int categoryId) {
//        this.description = description;
//        completed = false;
//        createdAt = LocalDateTime.now();
//        this.categoryId = categoryId;
//    }
//
////We need to update our Task.all() method's SQL query to include categoryId.
//    public static List<Task> all() {
//        String sql = "SELECT id, description, categoryId FROM tasks";
//        try(Connection con = DB.sql2o.open()) {
//            return con.createQuery(sql).executeAndFetch(Task.class);
//        }
//    }
//    //we need to update .equals() method to take this property into account when comparing Taskobjects:
//    @Override
//    public boolean equals (Object otherTask){
//        if(!(otherTask instanceof Task)){
//            return false;
//        }else {
//            Task newTask = (Task) otherTask;
//            // in addition to comparing the description attribute of two Task objects, we can compare their id too:
//            return this.getDescription().equals(newTask.getDescription()) &&
//                    this.getId() == newTask.getId() &&
//                    this.getCategoryId() == newTask.getCategoryId();
//        }
//    }
//
//    //Now that our test is in place, we can create this .save() method:
//    public void save() {
//        try(Connection con = DB.sql2o.open()) {
//            String sql = "INSERT INTO tasks(description,categoryId) VALUES (:description,categoryId)";
//           //By including this.id = before the entire chain of methods, we set this Tasks id property to the return value of this logic.
//            this.id = (int) con.createQuery(sql, true)
//                    .addParameter("description", this.description)
//                    .addParameter("categoryId",this.categoryId)
//                    .executeUpdate()
//                    .getKey();
//        }
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public boolean isCompleted() {
//        return completed;
//    }
//
//    public LocalDateTime getCreatedAt() {
//        return createdAt;
//    }
//
//    public int getId() {
//        return id;
//    }
////Our Task.find(myTask.getId()) uses the Task's id to query the database and return a Task object.
//    public static Task find(int id) {
//        try(Connection con = DB.sql2o.open()){
//            String sql = "SELECT * FROM tasks where id=:id";
//            Task task = con.createQuery(sql)
//                    .addParameter("id",id)
//                    .executeAndFetchFirst(Task.class);
//            return task;
//        }
//    }
//
//    public int getCategoryId() {
//        return categoryId;
//    }
//
//}


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;

public class Task {
    private String description;
    private boolean completed;
    private LocalDateTime createdAt;
    private int id;
    private int categoryId;

    public Task(String description,int categoryId) {
        this.description = description;
        completed = false;
        createdAt = LocalDateTime.now();
        this.categoryId = categoryId;

    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public int getId() {
        return id;
    }
    public int getCategoryId() {
        return categoryId;
    }

    public static Task find(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM tasks where id=:id";
            Task task = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Task.class);
            return task;
        }

    }

    public static List<Task> all() {
        String sql = "SELECT id, description, categoryId FROM tasks";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Task.class);
        }
    }
    @Override
    public boolean equals(Object otherTask){
        if (!(otherTask instanceof Task)) {
            return false;
        } else {
            Task newTask = (Task) otherTask;
            return this.getDescription().equals(newTask.getDescription()) &&
                    this.getId() == newTask.getId() &&
                    this.getCategoryId() == newTask.getCategoryId();
        }
    }
    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO tasks(description, categoryId) VALUES (:description, :categoryId)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("description", this.description)
                    .addParameter("categoryId", this.categoryId)
                    .executeUpdate()
                    .getKey();
        }
    }
    //
    public void update(String description) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "UPDATE tasks SET description = :description WHERE id = :id";
            con.createQuery(sql)
                    .addParameter("description", description)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }
    //Here, we call a delete() method upon an instance of Task. Then, we check it has been successfully deleted by confirming we receive null upon attempting to retrieve it from our database.
    public void delete() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "DELETE FROM tasks WHERE id = :id;";
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }


}







