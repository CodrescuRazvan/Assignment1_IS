package controller.user;

import factory.ComponentFactory;
import view.user.AdminView;
import view.user.AdminViewView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminViewController {

    private final AdminViewView adminViewView;

    public AdminViewController(AdminViewView adminViewView) throws HeadlessException {
        this.adminViewView = adminViewView;
        adminViewView.setBackButtonListener(new AdminViewController.BackButtonListener());
    }

    public class BackButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            adminViewView.dispose();
            new AdminController(new AdminView(), ComponentFactory.instance(false));
        }
    }
}
