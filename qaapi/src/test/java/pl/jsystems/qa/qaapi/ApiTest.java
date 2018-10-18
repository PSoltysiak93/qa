package pl.jsystems.qa.qaapi;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.jsystems.qa.qaapi.dbservice.UserDao;
import pl.jsystems.qa.qaapi.jdbiservice.UserJdbiService;
import pl.jsystems.qa.qaapi.model.*;
import pl.jsystems.qa.qaapi.model.error.ErrorResponse;
import pl.jsystems.qa.qaapi.service.UserService;
import pl.jsystems.qa.qaapi.specification.Specifications;
import sun.misc.JavaUtilZipFileAccess;

import javax.jws.soap.SOAPBinding;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;


import static org.awaitility.Awaitility.await;
import static org.hamcrest.core.Is.is;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    public void getErrorResponse() {
        ErrorResponse errorResponse = UserService.getUserErrorResponse();
        assertThat(errorResponse.Error.errorCode, is(400));
        assertThat(errorResponse.Error.validationError, equalTo("invalid_email"));
        assertThat(errorResponse.Error.message, equalTo("your email is invalid"));
    }

    @Test
    @DisplayName("Post assured test")
    public void postUser() {
        String[] emptyTable = UserService.postMyUser(new MyUser("Adam", "Majewski"));

        assertTrue(Arrays.asList(emptyTable).isEmpty());

    }

    @Test
    @DisplayName("Int generic test")
    public void genericIntTest() throws IOException {

        Response response = UserService.getGeneric("/5b05bf3f3200007100ebfa04");

        UserGeneric<Integer> userGeneric = new ObjectMapper().readValue(
                response
                        .then()
                        .extract()
                        .body()
                        .asInputStream(), new TypeReference<UserGeneric<Integer>>() {
                }
        );

        assertThat(userGeneric.id, is(1));
    }

    @Test
    @DisplayName("String generic test")
    public void genericStringTest() throws IOException {

        Response response = UserService.getGeneric("/5b05c83e3200009700ebfa2b");

        UserGeneric<String> userGeneric = new ObjectMapper().readValue(
                response
                        .then()
                        .extract()
                        .body()
                        .asInputStream(), new TypeReference<UserGeneric<String>>() {
                }
        );

        assertThat(userGeneric.id, equalTo("1a"));
    }

    @Test
    @DisplayName("Get User Azure by ID")
    public void azureUser() {
        UserAzure userAzure = UserService.getUserAzureByID(1);
        assertThat(userAzure.id, is(1));
        assertThat(userAzure.userName, equalTo("User 1"));
        assertThat(userAzure.password, equalTo("Password1"));
    }

    @Test
    public void testowy() {
        System.out.println(UserService.getGeneric("5b05c83e3200009700ebfa2b").prettyPeek());
    }


    @Test
    public void dbTest() {
        UserDBTest userDBTest = UserDao.getOneById(1);
        System.out.println(userDBTest);

        List<UserDBTest> users = UserDao.getAll();
        System.out.println(users);

        UserDao.saveOne(new UserDBTest(4, "Paweł", "Nowak"));
        UserDao.saveOne(new UserDBTest(5, "Łukasz", "Mi"));
        System.out.println(UserDao.getOneById(4));


        UserDao.update(new UserDBTest(1, "Piotr", "Nowak"), 1);
        System.out.println(UserDao.getOneById(1));

//        UserDao.delete(4);
//        System.out.println(UserDao.getOneById(4));


    }

    @Test
    public void awaitility() {
        await().untilAsserted(() -> {
            assertThat(UserService.getUserAzureByID(1).id, is(1));
            assertThat(UserDao.getOneById(1).getId(), is(1));
        });
    }

    @Test
    public void jdbiTest(){
        assertThat(UserJdbiService.getTestUser(1L).getId(), is(1L));

    }

}

