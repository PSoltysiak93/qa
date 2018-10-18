package pl.jsystems.qa.qaapi.specification;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import pl.jsystems.qa.qaapi.configuration.Configuration;

public class Specifications {

    public static RequestSpecification requestSpecification() {
        return new RequestSpecBuilder()
//                .addHeader()
                .setContentType(ContentType.JSON)
                .setBaseUri(Configuration.BASE_URL)
                .setBasePath("/v2")
                .build();


    }

    public static RequestSpecification requestSpecificationAuthori(String auth) {
        return new RequestSpecBuilder()
                .addHeader("Authorise", "Apikey," + auth)
                .setContentType(ContentType.JSON)
                .setBaseUri(Configuration.BASE_URL)
                .setBasePath("/v2")
                .build();
    }
    public static RequestSpecification fakeAzureSpecBuilder() {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri("http://fakerestapi.azurewebsites.net")
                .build();
    }

}
