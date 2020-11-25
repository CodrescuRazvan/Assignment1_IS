package controller.user;

import factory.ComponentFactory;
import model.User;
import model.builder.UserBuilder;
import model.validation.Notification;
import repository.admin.AdminRepository;
import service.user.AuthenticationService;
import view.user.AdminView;
import view.user.AdminViewView;
import view.user.LoginView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AdminController {

    private final AdminView adminView;
    private final AuthenticationService authenticationService;
    private final AdminRepository adminRepository;
    private final ComponentFactory componentFactory;

    public AdminController(AdminView adminView, ComponentFactory componentFactory) {
        this.adminView = adminView;
        this.componentFactory = componentFactory;
        this.adminRepository = componentFactory.getAdminRepository();
        this.authenticationService = componentFactory.getAuthenticationService();
        adminView.setCreateButtonListener(new AdminController.RegisterButtonListener());
        adminView.setBackButtonListener(new AdminController.BackButtonListener());
        adminView.setUpdateButtonListener(new AdminController.UpdateButtonListener());
        adminView.setDeleteButtonListener(new AdminController.DeleteButtonListener());
        adminView.setViewButtonListener(new AdminController.ViewButtonListener());
        adminView.setGenerateButtonListener(new AdminController.ReportsButtonListener());
    }

    private class BackButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            adminView.dispose();
            new LoginController(new LoginView(), ComponentFactory.instance(false));

        }
    }

    private class RegisterButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String username = adminView.getUsername();
            String password = adminView.getPassword();

            Notification<Boolean> registerNotification = authenticationService.register(username, password);
            if (registerNotification.hasErrors()) {
                JOptionPane.showMessageDialog(adminView.getContentPane(), registerNotification.getFormattedErrors());
            } else {
                if (!registerNotification.getResult()) {
                    JOptionPane.showMessageDialog(adminView.getContentPane(), "Unsuccessful creation.");
                } else {
                    JOptionPane.showMessageDialog(adminView.getContentPane(), "Succsessful creation.");
                }
            }
        }
    }

    private class UpdateButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Long id = Long.parseLong(adminView.getId());
            String username = adminView.getUsername();
            String password = adminView.getPassword();

            User user;
            if(!username.equals("") && password.equals("")){
                user = new UserBuilder()
                        .setId(id)
                        .setUsername(username)
                        .build();
                adminRepository.updateEmployee(user);
            } else if(!password.equals("") && username.equals("")){
                user = new UserBuilder()
                        .setId(id)
                        .setPassword(password)
                        .build();
                adminRepository.updateEmployee(user);
            } else {
                user = new UserBuilder()
                        .setId(id)
                        .setUsername(username)
                        .setPassword(password)
                        .build();
                adminRepository.updateEmployee(user);
            }
        }
    }

    private class DeleteButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Long id = Long.parseLong(adminView.getId());
            adminRepository.deleteEmployee(id);
        }
    }

    private class ViewButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            adminView.dispose();
            new AdminViewController(new AdminViewView(ComponentFactory.instance(false)));
        }
    }

    private class ReportsButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                adminRepository.generateReport();
                JOptionPane.showMessageDialog(adminView.getContentPane(), "Report generated!");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}
