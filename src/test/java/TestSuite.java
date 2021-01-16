import org.apache.commons.io.FileUtils;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import tests.StandardCall;
import tests.NavigationAndInternalCall;
import tests.TestApiAC;

import java.io.File;

@RunWith(Suite.class)
@Suite.SuiteClasses(value = {
        TestApiAC.class,
        NavigationAndInternalCall.class,
        StandardCall.class,
}
)
public class TestSuite {
    @BeforeClass
    public static void cleanFolder() {
        try {
            FileUtils.cleanDirectory(new File((System.getProperty("user.dir")) + "/reports/"));
        } catch (Exception e) {
            System.out.println("Exception trying to clean report folder");
        }
    }
}