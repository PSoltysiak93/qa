package pl.jsystems.qa.qaapi.dbservice;

import pl.jsystems.qa.qaapi.model.UserDBTest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    public static UserDBTest getOneById(int id) {
        String sql = "select * from usertest where id = " + id;

        UserDBTest userDBTest = new UserDBTest();

        try {
            Statement statement = DatebaseConnector.getConnection().createStatement();
            ResultSet wynik = statement.executeQuery(sql);

            while (wynik.next()) {
                userDBTest.setId(wynik.getInt(1));
                userDBTest.setName(wynik.getString(2));
                userDBTest.setSurname(wynik.getString(3));
            }
            wynik.close();
            statement.close();

        }catch (SQLException e){
            e.printStackTrace();

        }
        return userDBTest;

    }
    public static List<UserDBTest> getAll() {
        List<UserDBTest> userDBTest = new ArrayList<UserDBTest>();
        try {
            Statement statement = DatebaseConnector.getConnection().createStatement();
            String sql = "select * from usertest";
            ResultSet wynik = statement.executeQuery(sql);
//            testUsers = ladujDane(wynik);
            while (wynik.next()) {
                UserDBTest userTest = new UserDBTest();
                userTest.setId(wynik.getInt(1));
                userTest.setName(wynik.getString(2));
                userTest.setSurname(wynik.getString(3));
                userDBTest.add(userTest);
            }
            wynik.close();
            statement.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return userDBTest;
    }

    public static void saveOne(UserDBTest userDBTest) {
        String sql = "insert into usertest (id, name, surname) values (" + userDBTest.getId() + ", '" + userDBTest.getName() + "', '" + userDBTest.getSurname() + "')";
        try {
            DatebaseConnector.getConnection().createStatement().executeUpdate(sql);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void update(UserDBTest userDBTest, int id) {
        String sql = "update usertest set id = " + userDBTest.getId() + ", name = '"
                + userDBTest.getName() + "', surname = '" + userDBTest.getSurname() + "' where id = " + id;
        try {
            DatebaseConnector.getConnection().createStatement().executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void delete(int id) {
        String sql = "delete usertest where id = " + id;
        try {
            DatebaseConnector.getConnection().createStatement().executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
