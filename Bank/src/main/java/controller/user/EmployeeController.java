package controller.user;

import controller.account.ClientAccountController;
import controller.client.ClientInfoController;
import factory.ComponentFactory;
import view.account.ClientAccountView;
import view.client.ClientInfoView;
import view.user.EmployeeView;
import view.user.LoginView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeController {

    private final EmployeeView employeeView;


    public EmployeeController(EmployeeView employeeView) {
        this.employeeView = employeeView;
        employeeView.setClientInfoButtonListener(new ClientInfoButtonListener());
        employeeView.setClientAccountButtonListener(new ClientAccountButtonListener());
        employeeView.setBackButtonListener(new BackButtonListener());
    }

    private class ClientInfoButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            employeeView.dispose();
            new ClientInfoController(new ClientInfoView());

        }
    }

    private class ClientAccountButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            employeeView.dispose();
            new ClientAccountController(new ClientAccountView());

        }
    }

    private class BackButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            employeeView.dispose();
            new LoginController(new LoginView(), ComponentFactory.instance(false));
        }
    }
}
