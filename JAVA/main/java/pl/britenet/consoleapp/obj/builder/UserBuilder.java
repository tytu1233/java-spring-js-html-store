package pl.britenet.consoleapp.obj.builder;

import pl.britenet.consoleapp.Constants;
import pl.britenet.consoleapp.obj.model.User;

public class UserBuilder {

    private final User user;

    public UserBuilder(int id) {
        this.user = new User(id);
    }

    public UserBuilder() {
        this.user = new User(Constants.INVALID_ID);
    }

    public UserBuilder setName(String name) {
        this.user.setName(name);
        return this;
    }

    public UserBuilder setSurname(String surname) {
        this.user.setSurname(surname);
        return this;
    }

    public UserBuilder setLogin(String login) {
        this.user.setLogin(login);
        return this;
    }

    public UserBuilder setPassword(String password) {
        this.user.setPassword(password);
        return this;
    }

    public UserBuilder setAddress(String address) {
        this.user.setAddress(address);
        return this;
    }

    public UserBuilder setPhone(String phone) {
        this.user.setPhone(phone);
        return this;
    }

    public User getUser() {
        return this.user;
    }
}
