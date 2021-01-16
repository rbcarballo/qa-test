package core;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.junit.Assert;
import org.junit.ComparisonFailure;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TestCaseReport {

    private static final String ASSERT_EQUALS = "assertEquals";
    private static final String ASSERT_NOT_EQUALS = "assertNotEquals";
    private static final String ASSERT_CONTAINS = "assertContains";
    private static final String ASSERT_NULL = "assertNull";
    private static final String ASSERT_NOT_NULL = "assertNotNull";

    public ExtentReports extent;
    public ExtentTest testReport;
    List<String> comments = new ArrayList<>();


    public TestCaseReport() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH);
        String fileName = sdf.format(new Date());
        String reportLocation = (System.getProperty("user.dir")) + "/reports/" + fileName + ".html";
        extent = new ExtentReports(reportLocation, false, DisplayOrder.OLDEST_FIRST, Locale.ENGLISH);
    }

    public TestCaseReport(ExtentReports extentReports, String testName) {
        this.testReport = extentReports.startTest(testName);
    }

    public ExtentTest getTestReport() {
        return testReport;
    }

    public void setTestReport(ExtentTest testReport) {
        this.testReport = testReport;
    }

    public void logMessage(LogStatus logStatus, String stepName,
                           String message) {
        testReport.log(logStatus, getStepAndMessage(stepName, message));
    }

    public void logMessage(LogStatus logStatus, String message) {
        logMessage(logStatus, "", message);
    }


    public void logMessage(LogStatus logStatus,
                           Throwable throwable, String stepName) {
        testReport.log(logStatus, "Exception in step - " + stepName);
        testReport.log(logStatus, throwable);
    }

    public String getStepAndMessage(String stepName, String message) {
        if (!stepName.isEmpty()) {
            return stepName + " - " + message;
        } else {
            return message;
        }
    }

    public void logHtmlLink(LogStatus logStatus, String stepName,
                            String htmlLink) {
        final String open_initial_a_tag_html = "<a href=\"";
        final String close_initial_a_tag_html = "\">";
        testReport.log(logStatus, stepName);
        testReport.log(logStatus,
                open_initial_a_tag_html + htmlLink + close_initial_a_tag_html + htmlLink);
    }


    public void logHtmlTextarea(LogStatus logStatus, String stepName, String text) {
        final String open_initial_tag_html = "<textarea>";
        final String close_initial_tag_html = "</textarea>";
        testReport.log(logStatus,
                open_initial_tag_html + getStepAndMessage(stepName, text)
                        + close_initial_tag_html);
    }

    private void assertLog(LogStatus logStatus, String message, String expected, String actual) {
        final String assert_result_message =
                message + " result is " + logStatus.name() + ": expected is: '" + expected
                        + "' , actual is: '" + actual + "'";
        addComment(assert_result_message);
        logMessage(logStatus, message, assert_result_message);
    }

    public void assertEquals(String message, Object expected, Object actual) {
        try {
            Assert.assertEquals(message, expected, actual);
            assertLog(LogStatus.PASS, message + ASSERT_EQUALS, String.valueOf(expected),
                    String.valueOf(actual));
        } catch (Exception e) {
            assertLog(LogStatus.FAIL, message + ASSERT_EQUALS, String.valueOf(expected),
                    String.valueOf(actual));
            throw e;
        }
    }

    public void assertEquals(String message, double expected, double actual, double delta) {
        try {
            Assert.assertEquals(message, expected, actual, delta);
            assertLog(LogStatus.PASS, message + ASSERT_EQUALS, String.valueOf(expected),
                    String.valueOf(actual));
        } catch (Exception e) {
            assertLog(LogStatus.FAIL, message + ASSERT_EQUALS, String.valueOf(expected),
                    String.valueOf(actual));
            throw e;
        }
    }

    public void assertNotEquals(String message, Object expected, Object actual) {
        try {
            Assert.assertNotEquals(message, expected, actual);
            assertLog(LogStatus.PASS, message + ASSERT_NOT_EQUALS, String.valueOf(expected),
                    String.valueOf(actual));
        } catch (AssertionError e) {
            assertLog(LogStatus.FAIL, message + ASSERT_NOT_EQUALS, String.valueOf(expected),
                    String.valueOf(actual));
            throw e;
        }
    }

    public void assertContains(String message, String expected, String actual) {
        if (bothNotNull(expected, actual) && !justOneIsEmpty(expected, actual) && (
                expected.contains(actual) || actual.contains(expected))) {
            assertLog(LogStatus.PASS, message + ASSERT_CONTAINS, expected, actual);
        } else {
            assertLog(LogStatus.FAIL, message + ASSERT_CONTAINS, expected, actual);
            throw new ComparisonFailure(ASSERT_CONTAINS, expected, actual);
        }
    }

    private boolean bothNotNull(String expected, String actual) {
        return (actual != null) && (expected != null);
    }

    private boolean justOneIsEmpty(String expected, String actual) {
        return (!expected.isEmpty() && actual.isEmpty()) || (expected.isEmpty() && !actual
                .isEmpty());
    }

    public void assertNotNull(String message, Object object) {
        try {
            Assert.assertNotNull(object);
            assertLog(LogStatus.PASS, message + ASSERT_NOT_NULL, null, String.valueOf(object));
        } catch (Exception e) {
            assertLog(LogStatus.FAIL, message + ASSERT_NOT_NULL, null, String.valueOf(object));
            throw e;
        }
    }

    public void assertNull(String message, Object object) {
        try {
            Assert.assertNull(object);
            assertLog(LogStatus.PASS, message + ASSERT_NULL, null, String.valueOf(object));
        } catch (Exception e) {
            assertLog(LogStatus.FAIL, message + ASSERT_NULL, null, String.valueOf(object));
            throw e;
        }
    }

    public void addComment(String comment) {
        comments.add(comment.substring(0, Math.min(comment.length(), 1000000)));
    }

}