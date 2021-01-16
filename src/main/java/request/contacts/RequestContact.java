package request.contacts;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

public class RequestContact {

    public static final String API_ID = "de76a30151efb543f1861aefc593cce3";
    public static final String API_TOKEN = "c17e9cd430811b8da7625070304323ac";
    public static final String CONTACTS_URL = "https://api.aircall.io/v1/contacts";

    public static ValidatableResponse getContacts () {
        return RestAssured.given()
                .auth()
                .preemptive()
                .basic(API_ID, API_TOKEN)
                .when()
                .get(CONTACTS_URL)
                .then();
    }

    public static ValidatableResponse searchContactByPhone (String phoneNumber) {
        return RestAssured.given()
                .auth()
                .preemptive()
                .basic(API_ID,API_TOKEN)
                .when()
                .param("phone_number",phoneNumber)
                .get(CONTACTS_URL+"/search")
                .then();
    }
    public static ValidatableResponse searchContactById (String id) {
        return RestAssured.given()
                .auth()
                .preemptive()
                .basic(API_ID,API_TOKEN)
                .when()
                .pathParam("id",id)
                .get(CONTACTS_URL+"/{id}")
                .then();
    }

    public static ValidatableResponse createContact (String body) {
        return RestAssured.given()
                .auth()
                .preemptive()
                .basic(API_ID,API_TOKEN)
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post(CONTACTS_URL)
                .then();
    }

    public static ValidatableResponse updateContact (String id, String body) {
        return RestAssured.given()
                .auth()
                .preemptive()
                .basic(API_ID,API_TOKEN)
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .pathParam("id",id)
                .post(CONTACTS_URL+"/{id}")
                .then();
    }

    public static ValidatableResponse deleteContact (String id) {
        return RestAssured.given()
                .auth()
                .preemptive()
                .basic(API_ID,API_TOKEN)
                .when()
                .pathParam("id",id)
                .delete(CONTACTS_URL+"/{id}")
                .then();
    }

    public static ValidatableResponse addPhoneNumberToContact (String id, String body) {
        return RestAssured.given()
                .auth()
                .preemptive()
                .basic(API_ID,API_TOKEN)
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .pathParam("id",id)
                .post(CONTACTS_URL+"/{id}/phone_details")
                .then();
    }

    public static ValidatableResponse updatePhoneNumberToContact (String id, String body, String phone_number_id) {
        return RestAssured.given()
                .auth()
                .preemptive()
                .basic(API_ID,API_TOKEN)
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .pathParam("id",id)
                .pathParam("phone_number_id",phone_number_id)
                .put(CONTACTS_URL+"/{id}/phone_details/{phone_number_id}")
                .then();
    }

    public static ValidatableResponse deletePhoneNumberToContact (String id, String phone_number_id) {
        return RestAssured.given()
                .auth()
                .preemptive()
                .basic(API_ID,API_TOKEN)
                .when()
                .pathParam("id",id)
                .pathParam("phone_number_id",phone_number_id)
                .delete(CONTACTS_URL+"/{id}/phone_details/{phone_number_id}")
                .then();
    }


}
