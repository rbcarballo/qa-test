package core;

import com.relevantcodes.extentreports.ExtentReports;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

public class TestBase {
    protected TestCaseReport testCaseReport;
    protected ExtentReports extentReports;

    //Before section
    @Before
    public void createTestCaseReport() {
        TestCaseReport testCaseReport = new TestCaseReport();
        this.extentReports = testCaseReport.extent;
    }

    //After section
    @After
    public void closeReport() {
        extentReports.endTest(testCaseReport.testReport);
        extentReports.flush();
    }

    //Common methods

    public void createInstance(String testName) {
        this.testCaseReport = new TestCaseReport(extentReports, testName);
    }

    public String getString(ValidatableResponse response, String path) {
        return response.extract().jsonPath().getString(path).replace("[", "").replace("]", "");
    }

    public String readJsonFile(URL resource) {
        if (resource == null)
            throw new IllegalArgumentException("file is not found!");

        StringBuilder fileContent = new StringBuilder();

        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(resource.getFile()));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                fileContent.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return fileContent.toString();
    }
}

