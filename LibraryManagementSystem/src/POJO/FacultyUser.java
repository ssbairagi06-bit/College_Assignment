package POJO;

public class FacultyUser extends User {

    public FacultyUser(String user_id, String name) {

        super(user_id, name);

        setUser_type("Faculty");
        setBorrow_limit(5);
    }
}