package com.softserveinc.trainee.sender.Impl.salesforce;


public enum SalesForceProperties {
    CLIENT_ID_PREFIX("client_id"),
    CLIENT_SECRET_PREFIX("client_secret"),
    USERNAME("username"),
    PASSWORD("password"),
    SECURITY_TOKEN("securityToken"),
    URL("https://login.salesforce.com/services/oauth2/token"),
    ACCESS_TOKEN_KEY("access_token"),
    INSTANCE_URL_KEY("instance_url"),
    GET_METHOD_LINK("/services/apexrest/task"),
    AUTHORIZATION_KEY("Authorization"),
    AUTHORIZATION_CODE_PREFIX("OAuth ");

    private String value;

    SalesForceProperties(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
