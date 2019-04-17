import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
public class Task {
    private String mDescription;
    private boolean mCompleted;
    private LocalDateTime mCreatedAt;
    private int mId;

    private static List<Task> instances = new ArrayList<Task>();


    public Task(String description) {
        mDescription = description;
        mCompleted = false;
        mCreatedAt = LocalDateTime.now();
        instances.add(this);
        mId = instances.size();
    }
    public static List<Task> all(){
        return instances;
    }
    public static Task find(int id) {
        return instances.get(id - 1);
    }

    public String getDescription() {
        return mDescription;
    }

    public boolean isCompleted() {
        return mCompleted;
    }

    public LocalDateTime getCreatedAt() {
        return mCreatedAt;
    }
    public static void clear() {
        instances.clear();
    }
    public int getId(){
        return mId;
    }
}