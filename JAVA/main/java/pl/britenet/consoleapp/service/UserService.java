package pl.britenet.consoleapp.service;

import pl.britenet.consoleapp.obj.builder.UserBuilder;
import pl.britenet.consoleapp.obj.model.User;

import java.sql.SQLException;
import java.util.*;

public class UserService {

    private final Map<Integer, User> userMap;
    private final DatabaseService databaseService;

    public UserService(DatabaseService databaseService) {
        this.userMap = new HashMap<>();
        this.databaseService = databaseService;
    }

    public void insertUser(User user) {
        this.databaseService.performDML(
                String.format("INSERT INTO users (name, surname, login, password, address, phone) " +
                                "VALUES ('%s', '%s','%s', '%s','%s', '%s')",
                        user.getName(),
                        user.getSurname(),
                        user.getLogin(),
                        user.getPassword(),
                        user.getAddress(),
                        user.getPhone())
        );
    }

    public Optional<Collection<User>> getAllUsers() {
        return this.databaseService.performSQL(
                "SELECT * FROM users",
                resultSet -> {
                    try {
                        Collection<User> usersCollections = new ArrayList<>();
                        while (resultSet.next()) {

                            int id = resultSet.getInt("id_user");
                            String name = resultSet.getString("name");
                            String surname = resultSet.getString("surname");
                            String login = resultSet.getString("login");
                            String password = resultSet.getString("password");
                            String address = resultSet.getString("address");
                            String phone = resultSet.getString("phone");

                            User user = new UserBuilder(id)
                                    .setName(name)
                                    .setSurname(surname)
                                    .setLogin(login)
                                    .setPassword(password)
                                    .setAddress(address)
                                    .setPhone(phone)
                                    .getUser();

                            usersCollections.add(user);

                        }
                        return usersCollections;
                    } catch (SQLException e) {
                        e.printStackTrace();
                        return new ArrayList<>();
                    }
                }
        );
    }

    public Optional<User> getUser(int id) {
        return this.databaseService.performSQL(
                String.format("SELECT * FROM users WHERE id_user='%d'", id),
                resultSet -> {
                    try {
                        if (resultSet.next()) {

                            String name = resultSet.getString("name");
                            String surname = resultSet.getString("surname");
                            String login = resultSet.getString("login");
                            String password = resultSet.getString("password");
                            String address = resultSet.getString("address");
                            String phone = resultSet.getString("phone");

                            return new UserBuilder(id)
                                    .setName(name)
                                    .setSurname(surname)
                                    .setLogin(login)
                                    .setPassword(password)
                                    .setAddress(address)
                                    .setPhone(phone)
                                    .getUser();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    return null;
                }
        );
    }

    public Optional<User> getUserByCredentials(String username, String password) {
        return this.databaseService.performSQL(
                String.format("SELECT * FROM users WHERE login='%s' AND password='%s'", username, password),
                resultSet -> {
                    try {
                        if (resultSet.next()) {

                            int id = resultSet.getInt("id_user");

                            return new UserBuilder(id)
                                    .setLogin(username)
                                    .setPassword(password)
                                    .getUser();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    return null;
                }
        );
    }



    public void updateUser(User user) {
        this.databaseService.performDML(
                String.format("UPDATE users SET name='%s', surname='%s', login='%s', password='%s', address='%s', phone='%s' WHERE id_user='%d'",
                        user.getName(),
                        user.getSurname(),
                        user.getLogin(),
                        user.getPassword(),
                        user.getAddress(),
                        user.getPhone(),
                        user.getId())
        );
    }

    public void deleteUser(User user) {
        this.databaseService.performDML(
                String.format("DELETE FROM users WHERE id_user='%s'",
                        user.getId())
        );
    }
}
