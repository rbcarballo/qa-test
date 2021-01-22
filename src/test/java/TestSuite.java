import org.apache.commons.io.FileUtils;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import tests.*;

import java.io.File;

@RunWith(Suite.class)
@Suite.SuiteClasses(value = {
        TestApiAC.class,
        NavigationAndInternalCall.class,
        StandardCall.class,
        ArchiveAllTask.class,
        AddingNewTask.class,
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