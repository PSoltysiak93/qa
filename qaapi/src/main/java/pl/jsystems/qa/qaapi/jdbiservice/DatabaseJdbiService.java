package pl.jsystems.qa.qaapi.jdbiservice;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import pl.jsystems.qa.qaapi.configuration.Configuration;

public class DatabaseJdbiService {

    private static Jdbi jdbi;
    private static Handle handle;

    static {
        jdbi = Jdbi.create(Configuration.DB_URL, Configuration.DB_USER, Configuration.DB_PASSWORD);
        jdbi.installPlugin(new SqlObjectPlugin());
        handle = jdbi.open();
    }

    public Handle getHandle(){
        return DatabaseJdbiService.handle;
    }

}
