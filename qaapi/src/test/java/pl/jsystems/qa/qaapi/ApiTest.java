package pl.jsystems.qa.qaapi;


import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.jsystems.qa.qaapi.model.MyUser;
import pl.jsystems.qa.qaapi.model.User;
import pl.jsystems.qa.qaapi.model.error.ErrorResponse;
import pl.jsystems.qa.qaapi.service.UserService;
import pl.jsystems.qa.qaapi.specification.Specifications;

import java.util.List;

import static org.hamcrest.core.Is.is;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

@DisplayName("Api tests")
public class ApiTest {

    @Test
    @DisplayName("First rest assured test")
    public void firsTest() {

        RestAssured.given()
                .spec(Specifications.requestSpecification())
                .when()
                .get("5bc70dba32000052000b05ff")
                .then()
                .assertThat()
                .statusCode(200)
                .body("name", equalTo("Piotrek"))
                .body("surname", equalTo("Sołtysiak"));
    }

    @Test
    @DisplayName("Second rest assured test")
    public void nestedTest() {
        RestAssured.given()
                .spec(Specifications.requestSpecification())
                .when()
                .get("5a6a58222e0000d0377a7789")
                .then()
                .assertThat()
                .statusCode(200)
                .body("[0].imie", equalTo("Piotr"))
                .body("[0].nazwisko", equalTo("Kowalski"))
                .body("[0].device[0].type", equalTo("computer"));
//                .body("[0].device.[0].device.model.produce", equalTo("dell"));
    }

    @Test
    @DisplayName("Third rest assured test")
    public void simpleTest() {
//        JsonPath jsonPath = RestAssured.given()
//                .spec(Specifications.requestSpecification())
//                .when()
//                .get("/5a6a58222e0000d0377a7789")
//                .then()
//                .assertThat()
//                .statusCode(200)
//                .extract()
//                .body()
//                .jsonPath();
//
//        List<User> users = jsonPath.getList("",User.class);
        List<User> users = new UserService().getUserList();

        assertThat(users.get(0).imie, equalTo("Piotr"));
        assertThat(users.get(0).nazwisko, equalTo("Kowalski"));
        assertThat(users.get(0).device.get(0).type, equalTo("computer"));
        assertThat(users.get(0).device.get(0).deviceModel.get(0).produce, equalTo("dell"));
        assertThat(users.get(0).device.get(0).deviceModel.get(0).screenSize, is(17.0));


    }

    @Test
    @DisplayName("Fourth rest assured test")
    public void jsonPathTest() {
//        JsonPath jsonPath = RestAssured.given()
//                .spec(Specifications.requestSpecification())
//                .when()
//                .get("/5bc70dba32000052000b05ff")
//                .then()
//                .assertThat()
//                .statusCode(200)
//                .extract()
//                .body()
//                .jsonPath();
//
//        MyUser users = jsonPath.getObject("", MyUser.class);
        MyUser users = new UserService().getMyUser();
        assertThat(users.name, equalTo("Piotrek"));
        assertThat(users.surname, equalTo("Sołtysiak"));

    }

    @Test
    @DisplayName("Fifth rest assured test")
    public void rawResponseTest() {

        MyUser users = UserService.getMyUserResponse();
        assertThat(users.name, equalTo("Piotrek"));
        assertThat(users.surname, equalTo("Sołtysiak"));
    }

    @Test
    @DisplayName("Sixth rest assured test")
    public void userResponseTest() {
//        JsonPath jsonPath = RestAssured.given()
//                .spec(Specifications.requestSpecification())
//                .when()
//                .get("/5a6a58222e0000d0377a7789")
//                .then()
//                .assertThat()
//                .statusCode(200)
//                .extract()
//                .body()
//                .jsonPath();
//
//        List<User> users = jsonPath.getList("",User.class);
        List<User> users = UserService.getMyUserResponseList();

        assertThat(users.get(0).imie, equalTo("Piotr"));
        assertThat(users.get(0).nazwisko, equalTo("Kowalski"));
        assertThat(users.get(0).device.get(0).type, equalTo("computer"));
        assertThat(users.get(0).device.get(0).deviceModel.get(0).produce, equalTo("dell"));
        assertThat(users.get(0).device.get(0).deviceModel.get(0).screenSize, is(17.0));


    }

    @Test
    @DisplayName("Error rest assured test")
    public void getErrorResponse(){
        ErrorResponse errorResponse = UserService.getUserErrorResponse();
        assertThat(errorResponse.Error.errorCode, is(400));
        assertThat(errorResponse.Error.validationError,equalTo("invalid_email"));
        assertThat(errorResponse.Error.message, equalTo("your email is invalid"));
    }
}