package pl.jsystems.qa.qaapi.jdbiservice;

import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface UserDao {
    @SqlQuery("select * from userTest where id = ?")
    @RegisterConstructorMapper(JdbiUser.class)
    JdbiUser getTestUserById(Long id);

    @SqlQuery("select * from usertest")
    @RegisterConstructorMapper(JdbiUser[].class)
    List<JdbiUser> getAllTestUsers();

    @SqlUpdate("insert into usertest (id, name, surname) values (:id, :name, :surname)")
    void insert(long id, String name, String surname);

    @SqlUpdate("update usertest set name = :name where id = ?")
    void updateUser(String name, Long id);

    @SqlUpdate("delete usertest where id = ?")
    void deleteUser(Long id);

}
