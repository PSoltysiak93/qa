package pl.jsystems.qa.qaapi.jdbiservice;

import org.jdbi.v3.core.mapper.reflect.ColumnName;

public class JdbiUser {

    private Long id;
    private String name;
    private String surname;

    public JdbiUser(@ColumnName("id") Long id, @ColumnName("name") String name, @ColumnName("surname") String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "JdbiUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }

}
