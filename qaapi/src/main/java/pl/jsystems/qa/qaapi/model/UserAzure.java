package pl.jsystems.qa.qaapi.model;

import com.fasterxml.jackson.annotation.JacksonAnnotation;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserAzure {


    @JsonProperty(value = "ID")
    public int id;

    @JsonProperty(value = "UserName")
    public String userName;

    @JsonProperty(value = "Password")
    public String password;

}
