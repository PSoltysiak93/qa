package pl.jsystems.qa.qaapi.service;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import pl.jsystems.qa.qaapi.model.MyUser;
import pl.jsystems.qa.qaapi.model.User;
import pl.jsystems.qa.qaapi.model.error.ErrorResponse;
import pl.jsystems.qa.qaapi.specification.Specifications;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class UserService {

    public static final String MY_USER_URL = "/5bc70dba32000052000b05ff";
    public static final String USER_URL = "/5a6a58222e0000d0377a7789";
    public static final String ERROR_URL = "/5a690b452e000054007a73cd";

    //    public static String
    public static MyUser getMyUser() {
        return RestAssured.given()
                .spec(Specifications.requestSpecification())
                .when()
                .get(MY_USER_URL)
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .body()
                .jsonPath()
                .getObject("", MyUser.class);

    }

    public static List<User> getUserList() {
        return RestAssured.given()
                .spec(Specifications.requestSpecification())
                .when()
                .get(USER_URL)
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .body()
                .jsonPath()
                .getList("", User.class);


    }


    public static MyUser getMyUserResponse() {
        return RestAssured.given()
                .spec(Specifications.requestSpecification())
                .when()
                .get(MY_USER_URL)
                .andReturn()
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .body()
                .as(MyUser.class);

    }

    public static List<User> getMyUserResponseList() {
        return Arrays.asList(
                RestAssured.given()
                        .spec(Specifications.requestSpecification())
                        .when()
                        .get(USER_URL)
                        .andReturn()
                        .then()
                        .assertThat()
                        .statusCode(200)
                        .extract()
                        .body()
                        .as(User[].class)

        );

    }
    public static ErrorResponse getUserErrorResponse() {
        return RestAssured.given()
                .spec(Specifications.requestSpecification())
                .when()
                .get(ERROR_URL)
                .andReturn()
                .then()
                .assertThat()
                .statusCode(400)
                .extract()
                .body()
                .as(ErrorResponse.class);

    }
}