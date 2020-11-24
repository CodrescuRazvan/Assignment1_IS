package controller.user;

import factory.ComponentFactory;
import model.User;
import model.validation.Notification;
import repository.user.AuthenticationException;
import service.user.AuthenticationService;
import view.user.AdminView;
import view.user.EmployeeView;
import view.user.LoginView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController {
    private final LoginView loginView;
    private final AuthenticationService authenticationService;

    public LoginController(LoginView loginView, ComponentFactory componentFactory) {
        this.loginView = loginView;
        this.authenticationService = componentFactory.getAuthenticationService();
        System.out.println(componentFactory.toString());
        loginView.setLoginButtonListener(new LoginButtonListener());
    }

    private class LoginButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String username = loginView.getUsername();
            String password = loginView.getPassword();

            if (username.equals("admin") && password.equals("admin")) {
                loginView.dispose();
                new AdminController(new AdminView(), ComponentFactory.instance(false));
            } else {
                Notification<User> loginNotification = null;
                try {
                    loginNotification = authenticationService.login(username, password);
                } catch (AuthenticationException e1) {
                    e1.printStackTrace();
                }

                if (loginNotification != null) {
                    if (loginNotification.hasErrors()) {
                        JOptionPane.showMessageDialog(loginView.getContentPane(), loginNotification.getFormattedErrors());
                    } else {
                        JOptionPane.showMessageDialog(loginView.getContentPane(), "Login successful!");
                        loginView.dispose();
                        new EmployeeController(new EmployeeView());
                    }
                }
            }
        }
    }
}