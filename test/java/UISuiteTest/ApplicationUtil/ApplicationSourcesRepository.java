package UISuiteTest.ApplicationUtil;



/**
 * Created by sriznych on 25.08.2016.
 */
public class ApplicationSourcesRepository {
    private static volatile ApplicationSourcesRepository instance = null;

    private ApplicationSourcesRepository() {
    }

    public static ApplicationSourcesRepository get() {
        if(instance == null) {
            Class var0 = ApplicationSourcesRepository.class;
            synchronized(ApplicationSourcesRepository.class) {
                if(instance == null) {
                    instance = new ApplicationSourcesRepository();
                }
            }
        }

        return instance;
    }

     public ApplicationSources getURLByFirefox() {
     return ApplicationSources.get().setLoginUrl("https://login.salesforce.com/").setLogoutUrl("https://login.salesforce.com/").setImplicitTimeOut(10).setBrowserName("firefox");
    }

    public ApplicationSources getURLByChrome() {
        return ApplicationSources.get().setLoginUrl("https://login.salesforce.com/").setLogoutUrl("https://login.salesforce.com/").setImplicitTimeOut(10).setBrowserName("chrome");
    }

}
