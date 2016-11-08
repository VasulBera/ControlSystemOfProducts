package UISuiteTest.TestsSuite;

import IntegrationTests.TestsSuite.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by sriznych on 08.11.2016.
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
        SmokeTest.class,
        TestCreateRecordOption.class
})

public class UITestSite {
}

