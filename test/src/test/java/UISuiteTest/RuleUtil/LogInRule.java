package UISuiteTest.RuleUtil;

import UISuiteTest.ApplicationUtil.Application;
import UISuiteTest.ApplicationUtil.ApplicationSourcesRepository;
import UISuiteTest.LogInData.AccountRepository;
import UISuiteTest.pages.CreateEntityRecordPage;
import UISuiteTest.pages.HomePage;
import org.junit.rules.ExternalResource;

import static UISuiteTest.RuleUtil.OpenBrowserRule.application;

/**
 * Created by sriznych on 01.09.2016.
 */

public class LogInRule extends ExternalResource{

    public static HomePage homePage;

    @Override
    public void before() {
        homePage = application.loadChrome().successLogin(AccountRepository.get().getDataAccount()).gotoHomePage();
    }

    @Override
    public void after() {
        System.out.println(" After LogInRule rule class!!!");
        application.logout();
    }
}
