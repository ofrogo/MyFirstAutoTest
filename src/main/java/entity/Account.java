package entity;

import service.Service;

import java.util.Properties;

public class Account {
    private String email;
    private String password;

    public Account() {
        Properties properties = Service.getProperties();
        email = properties.getProperty("yandex.email");
        password = properties.getProperty("yandex.password");
    }

    public Account(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
