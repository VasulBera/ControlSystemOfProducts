package UISuiteTest.RuleUtil;

import UISuiteTest.ApplicationUtil.Application;
import UISuiteTest.ApplicationUtil.ApplicationSourcesRepository;
import org.junit.rules.ExternalResource;

/**
 * Created by sriznych on 01.09.2016.
 */

public class OpenBrowserRule extends ExternalResource {

    public static Application application;

    @Override
    public void before() {
        application = new Application(ApplicationSourcesRepository.get().getURLByChrome());
    }

    @Override
    public void after() {
        System.out.println(" After OpenBrowserRule rule class!!!");
        application.close();
    }
}
