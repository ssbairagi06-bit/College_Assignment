package POJO;

public abstract class User {

    private String user_id;
    private String name;
    private String user_type;
    private int borrow_limit;

    public User() {
    }

    public User(String user_id, String name) {
        this.user_id = user_id;
        this.name = name;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public int getBorrow_limit() {
        return borrow_limit;
    }

    public void setBorrow_limit(int borrow_limit) {
        this.borrow_limit = borrow_limit;
    }
}