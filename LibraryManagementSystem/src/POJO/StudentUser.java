package POJO;

public class StudentUser extends User {

    public StudentUser(String user_id, String name) {

        super(user_id, name);

        setUser_type("Student");
        setBorrow_limit(3);
    }
}