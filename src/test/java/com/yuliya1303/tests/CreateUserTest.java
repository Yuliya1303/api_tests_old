package com.yuliya1303.tests;

import com.yuliya1303.models.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.yuliya1303.helpers.AllureRestAssuredFilter.withCustomTemplates;
import static io.restassured.RestAssured.given;
import static com.yuliya1303.helpers.Specs.responseSpec;
import static com.yuliya1303.helpers.Specs.requestSpec;
import static org.junit.jupiter.api.Assertions.*;

public class CreateUserTest {
    @Test
    @DisplayName("Check that user is created with valid data")
    void testUserIsCreatedWithValidData() {

        String name = "Yuliya";
        String job = "QA Engineer";

        User bodyCreateUser = new User();
        bodyCreateUser.setName(name);
        bodyCreateUser.setJob(job);

        User userData = given()
                .filter(withCustomTemplates())
                .spec(requestSpec)
                .body(bodyCreateUser)
                .when()
                .post("/users")
                .then()
                .spec(responseSpec)
                .log().body()
                .extract().as(User.class);

        assertNotEquals(null, userData.getId());
        assertEquals(name, userData.getName());
        assertEquals(job, userData.getJob());
        assertNotEquals(null, userData.getCreatedAt());
    }
}
