package firstTask;

import java.sql.SQLException;

public class User {

    private static DatabaseHandler database = new DatabaseHandler();
    private String user_name;
    private String user_password;
    private Boolean is_online;


    public User(String user_name, String user_password, Boolean is_online) {
        this.user_name = user_name;
        this.user_password = user_password;
        this.is_online = is_online;
    }

    public Boolean getIs_online() {
        return is_online;
    }

    public void setIs_online(Boolean is_online) {
        this.is_online = is_online;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }
}
