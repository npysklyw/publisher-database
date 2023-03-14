package iPublisher;

import javafx.beans.property.StringProperty;

public class Account {

    private final StringProperty user;
    private final StringProperty password;
    private final StringProperty email;
    private final StringProperty name;

    public Account(StringProperty user, StringProperty password, StringProperty email, StringProperty name) {
        this.user = user;
        this.password = password;
        this.email = email;
        this.name = name;
    }

    public String getUser() {
        return user.get();
    }

    public StringProperty userProperty() {
        return user;
    }

    public void setUser(String user) {
        this.user.set(user);
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }
}
