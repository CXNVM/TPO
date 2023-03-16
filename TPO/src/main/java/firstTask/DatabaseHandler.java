package firstTask;

import com.mysql.cj.util.StringInspector;

import java.sql.*;


public class DatabaseHandler extends Configs {
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;

        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);

        return dbConnection;
    }

    // регистрация пользователя
    public boolean singUpUser(User user) throws SQLException, ClassNotFoundException {

        // запрос
        String insert_query = "insert into users(user_name, user_password, is_online) values (?,?,?)";

        // like mysqli_query

        PreparedStatement prSt = getDbConnection().prepareStatement(insert_query);
        // заполнение вопросов
        prSt.setString(1, user.getUser_name());
        prSt.setString(2, user.getUser_password());
        prSt.setBoolean(3, user.getIs_online());
        int num_rows = prSt.executeUpdate();

        return num_rows > 0;
    }

    //
    public boolean getUserByLogin(String user_name) throws SQLException, ClassNotFoundException {
        User user = null;

        String select_query = "select * from users where user_name = ?";

        PreparedStatement statement = getDbConnection().prepareStatement(select_query);
        statement.setString(1, user_name);
        ResultSet result = statement.executeQuery();
        int size = 0;
        while (result.next()) {
            size++;
        }
        return size > 0;
    }

    public boolean isUserOnline(String user_name) throws SQLException, ClassNotFoundException{
        //если пользователь существует
        if (getUserByLogin(user_name)) {
            String select_query = "select * from users where user_name = ?";
            PreparedStatement statement = getDbConnection().prepareStatement(select_query);
            statement.setString(1, user_name);
            ResultSet result = statement.executeQuery();
            boolean is_online = true;
            while (result.next()) {
                is_online = result.getBoolean("is_online");
            }
            return is_online;
        } else {
            return false;
        }
    }



}
