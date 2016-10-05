package UISuiteTest.ApplicationUtil;

/**
 * Created by sriznych on 25.08.2016.
 */

public final class ApplicationSources {
    private String loginUrl;
    private String logoutUrl;
    private long implicitTimeOut;
    private String browserName;


    public ApplicationSources() {
        this.loginUrl = new String();
        this.logoutUrl = new String();
        this.implicitTimeOut = 11;
        this.browserName = new String();
    }

    public static ApplicationSources get() {
        return new ApplicationSources();
    }

    public ApplicationSources setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
        return this;
    }

    public ApplicationSources setLogoutUrl(String logoutUrl) {
        this.logoutUrl = logoutUrl;
        return this;
    }

    public ApplicationSources setImplicitTimeOut(long implicitTimeOut) {
        this.implicitTimeOut = implicitTimeOut;
        return this;
    }

    public ApplicationSources setBrowserName(String browserName) {
        this.browserName = browserName;
        return this;
    }

    public String getLoginUrl() {
        return loginUrl;
    }

    public String getLogoutUrl() {
        return logoutUrl;
    }

    public long getImplicitTimeOut() {
        return implicitTimeOut;
    }

    public String getBrowserName() {
        return browserName;
    }

}
