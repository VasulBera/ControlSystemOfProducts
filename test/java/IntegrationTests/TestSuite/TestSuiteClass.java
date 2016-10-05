package IntegrationTests.TestSuite;

import IntegrationTests.TestsSuite.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author Solomiia Riznychok
 * @version 1.0
 * @since 2016-04-08
 * The TestSuiteClass class represents all testing classes.
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestGetMethod.class,
        TestPostMethod.class,
        TestPutMethod.class,
        TestDeleteMethod.class,
        TestPatchMethod.class,
        TestURL.class
})
public class TestSuiteClass {
}
