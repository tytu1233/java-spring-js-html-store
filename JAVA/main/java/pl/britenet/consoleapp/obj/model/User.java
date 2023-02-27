package pl.britenet.consoleapp.obj.model;

import pl.britenet.consoleapp.Constants;

public final class User {

    private final int id;
    private String name;
    private String surname;
    private String login;
    private String password;
    private String address;
    private String phone;

    public User(int id) {
        this.id = id;
    }

    public User() {
        this.id = Constants.INVALID_ID;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
