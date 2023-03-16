package firstTask;

import org.junit.*;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class DatabaseHandlerTest{

    private static DatabaseHandler database;

    @BeforeClass
    public static void setUp() throws Exception {
        database = new DatabaseHandler();
    }

    @AfterClass
    public static void tearDown() throws Exception {
        database.dbConnection.close();
    }

    // проверка подключения бд (пункт 3)
    @Test
    public void getDbConnection() throws SQLException, ClassNotFoundException{
        Assert.assertNotNull(database.getDbConnection());
    }

    // добавление данных в бд (пункт 4)
    @Test
    public void singUpUser() throws SQLException, ClassNotFoundException{
        Assert.assertTrue(database.singUpUser(new User("CXahah", "123", true)));
    }

    // проверка существования пользователя в бд (пункт 2) // позитивный тест
    @Test
    public void getUserByLoginPositive() throws SQLException, ClassNotFoundException{
        Assert.assertTrue(database.getUserByLogin("cxmv"));
    }

    // проверка существования пользователя в бд (пункт 2) // негативный тест
    @Test
    public void getUserByLoginNegative() throws SQLException, ClassNotFoundException{
        Assert.assertTrue(database.getUserByLogin("ahahah"));
    }


    @Test
    public void isUserOnlinePositive() throws SQLException, ClassNotFoundException{
        Assert.assertEquals(database.isUserOnline("cxmv"), true);
    }

    @Test
    public void isUserOnlineNegative() throws SQLException, ClassNotFoundException{
        Assert.assertFalse(database.isUserOnline("cxmv"));
    }
}