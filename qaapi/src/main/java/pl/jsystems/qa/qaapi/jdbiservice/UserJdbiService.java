package pl.jsystems.qa.qaapi.jdbiservice;

import org.jdbi.v3.core.Handle;

public class UserJdbiService {
    private static DatabaseJdbiService databaseJdbiService = new DatabaseJdbiService();
    private static UserDao userDao;
    private static Handle handle = databaseJdbiService.getHandle();

    public static JdbiUser getTestUser(Long id){
        userDao = handle.attach(UserDao.class);
        return userDao.getTestUserById(id);
    }
}
