package model.controller;

import java.util.ArrayList;

import model.movie.Ticket;
import model.user.RegisteredUser;
import model.user.ShoppingCart;
import model.user.User;

public class LoginServer {
    private ArrayList<User> users;
    private ArrayList<RegisteredUser> registeredUsers;
    private static LoginServer instance = new LoginServer();

    private LoginServer() {
        this.users = new ArrayList<>();
        this.registeredUsers = new ArrayList<>();
    }

    public RegisteredUser authenticate(String email, String password) {
        for (RegisteredUser ru : registeredUsers) {
            if (ru.authenticate(email, password)) {
                return ru;
            }
        }
        return null;
    }

    public User findUser(String email) {
        for (User u : users) {
            if (u.getEmail().compareTo(email) == 0) {
                return u;
            }
        }
        
        User user = new User(email, 0, false);
        this.users.add(user);
        return user;
    }
    
    public RegisteredUser findRU(String email) {
        for (RegisteredUser ru : registeredUsers) {
            if (ru.getEmail().compareTo(email) == 0) {
                return ru;
            }
        }
        return null;
    }

    public static LoginServer getInstance() {
        return LoginServer.instance;
    }

    public User addUser(User newUser) {
        this.users.add(newUser);
        return newUser;
    }

    public RegisteredUser addRegisteredUser(RegisteredUser ru) {
        this.registeredUsers.add(ru);
        return ru;
    }
}
