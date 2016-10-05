package UISuiteTest.LogInData;

interface IUserName {
    IPassword setUserName(String login);
}

interface IPassword {
    IAccountBuild setPassword(String password);
}

interface IAccountBuild {
    Account build();
}

public class Account implements IUserName, IPassword, IAccount, IAccountBuild {

    private String userName;
    private String password;

    private Account() {
    }

    public static IUserName get() {
        return new Account();
    }

    public IPassword setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public IAccountBuild setPassword(String password) {
        this.password = password;
        return this;
    }

    public Account build() {
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

}
