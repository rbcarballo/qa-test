package tests;

import behavior.TestBaseBehavior;
import com.relevantcodes.extentreports.LogStatus;
import core.TestBase;
import data.ACUser;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class TestApiAC extends TestBase {
    public static final String JSONFILES = "jsonfiles/";
    TestBaseBehavior behavior;
    ACUser user;


    public TestApiAC() {
        user = new ACUser("rbcarballo@gmail.com", "4c5682af");
    }

    @Test
    public void testAirCallGetContacts() {
        createInstance("Contacts API Get Part");
        behavior = new TestBaseBehavior(testCaseReport);
        testCaseReport.logMessage(LogStatus.INFO, "Testing list of contacts");
        behavior.api().contacts().getContactList();
        testCaseReport.logMessage(LogStatus.INFO, "Testing search by id");
        ValidatableResponse response = behavior.api().contacts().getContactByPhone("+33652556756");
        testCaseReport.assertEquals("Number of contacts found ", 1,
                response.extract().jsonPath().get("meta.count"));
        String id = getString(response, "contacts.id");
        ValidatableResponse responseById = behavior.api().contacts().getContactById(id);
        testCaseReport.assertEquals("Contact searching by phone y the same as searching by id",
                getString(response, "contacts.direct_link"),
                getString(responseById, "contact.direct_link"));
    }

    @Test
    public void testAirCallCreateContact() {
        createInstance("Contacts API Create contact");
        behavior = new TestBaseBehavior(testCaseReport);
        URL json = TestApiAC.class.getClassLoader().getResource(JSONFILES + "createUserCompleteInformation.json");
        String body = readJsonFile(json);
        ValidatableResponse response = behavior.api().contacts().createContact(body);
        int idUserCreated = response.extract().jsonPath().get("contact.id");
        testCaseReport.assertEquals("User created correctly, status code ", 201, response.extract().statusCode());
        testCaseReport.logMessage(LogStatus.INFO, "id user: " + idUserCreated);
        response = behavior.api().contacts().deleteContact(idUserCreated);
        testCaseReport.assertEquals("User deleted", 204, response.extract().statusCode());
    }

    @Test
    public void testAirCallCreateContactBlankvalues() {
        createInstance("Contacts API Create contact Blank values");
        behavior = new TestBaseBehavior(testCaseReport);
        List<URL> listJson = new ArrayList<>();
        listJson.add(TestApiAC.class.getClassLoader().getResource(JSONFILES + "createUserBlankLastName.json"));
        listJson.add(TestApiAC.class.getClassLoader().getResource(JSONFILES + "createUserBlankCompanyName.json"));
        listJson.add(TestApiAC.class.getClassLoader().getResource(JSONFILES + "createUserBlankFirstName.json"));
        for (URL json : listJson) {
            String body = readJsonFile(json);
            ValidatableResponse response = behavior.api().contacts().createContact(body);
            testCaseReport.assertNotEquals("User should not be created ", 201, response.extract().statusCode());
        }
    }

    @Test
    public void testAirCallCreateContactNoMandatory() {
        createInstance("Contacts API Create contact no mandatory fields");
        behavior = new TestBaseBehavior(testCaseReport);
        URL json = TestApiAC.class.getClassLoader().getResource(JSONFILES + "createUserNoMandatory.json");
        String body = readJsonFile(json);
        ValidatableResponse response = behavior.api().contacts().createContact(body);
        testCaseReport.assertNotEquals("User should not be created not mandatory fields ", 201, response.extract().statusCode());
    }

    @Test
    public void testAirCallUpdateContact() {
        createInstance("Contacts API Update contact");
        behavior = new TestBaseBehavior(testCaseReport);
        URL json = TestApiAC.class.getClassLoader().getResource(JSONFILES + "createUserNoMandatory.json");
        URL jsonUpdate = TestApiAC.class.getClassLoader().getResource(JSONFILES + "updateUser.json");
        String body = readJsonFile(json);
        ValidatableResponse response = behavior.api().contacts().createContact(body);

        int idUserCreated = response.extract().jsonPath().get("contact.id");
        testCaseReport.assertEquals("User created correctly, status code ", 201, response.extract().statusCode());
        testCaseReport.logMessage(LogStatus.INFO, "id user: " + idUserCreated);

        body = readJsonFile(jsonUpdate);
        response = behavior.api().contacts().updateContact(idUserCreated, body);
        testCaseReport.assertEquals("User updated ", 200, response.extract().statusCode());

        response = behavior.api().contacts().deleteContact(idUserCreated);
        testCaseReport.assertEquals("User deleted", 204, response.extract().statusCode());
        testCaseReport.assertNotEquals("User should not be created not mandatory fields ", 201, response.extract().statusCode());
    }
}

