package UISuiteTest.LogInData;

/**
 * Created by sriznych on 30.08.2016.
 */

public class AccountRepository {
    private static volatile AccountRepository instance = null;

    private AccountRepository() {
    }

    public static AccountRepository get() {
        if (instance == null) {
            synchronized (Account.class) {
                if (instance == null) {
                    instance = new AccountRepository();
                }
            }
        }
        return instance;
    }

    public Account getDataAccount() {
        return Account.get().
                setUserName("sriznuchok@gmail.com").
                setPassword("1a4b9c16dd").
                build();
    }
}
