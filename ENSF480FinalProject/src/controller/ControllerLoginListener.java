package controller;

import model.user.User;

public interface ControllerLoginListener {
    User login(String email, String password);
}
