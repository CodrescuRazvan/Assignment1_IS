package controller.account;

import controller.user.EmployeeController;
import factory.ComponentFactory;
import view.account.*;
import view.user.EmployeeView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientAccountController {

    private final ClientAccountView clientAccountView;

    public ClientAccountController(ClientAccountView clientAccountView) throws HeadlessException {
        this.clientAccountView = clientAccountView;
        clientAccountView.setBackButtonListener(new ClientAccountController.BackButtonListener());
        clientAccountView.setCreateButtonListener(new ClientAccountController.CreateButtonListener());
        clientAccountView.setUpdateButtonListener(new ClientAccountController.UpdateButtonListener());
        clientAccountView.setDeleteButtonListener(new ClientAccountController.DeleteButtonListener());
        clientAccountView.setViewButtonListener(new ClientAccountController.ViewButtonListener());

    }

    private class BackButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            clientAccountView.dispose();
            new EmployeeController(new EmployeeView());

        }
    }

    private class CreateButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            clientAccountView.dispose();
            new CreateAccountController(new CreateAccountView(), ComponentFactory.instance(false));

        }
    }

    private class UpdateButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            clientAccountView.dispose();
            new UpdateAccountController(new UpdateAccountView(), ComponentFactory.instance(false));
        }
    }

    private class DeleteButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            clientAccountView.dispose();
            new DeleteAccountController(new DeleteAccountView(), ComponentFactory.instance(false));
        }
    }

    private class ViewButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            clientAccountView.dispose();
            new ViewAccountController(new ViewAccountView(ComponentFactory.instance(false)));
        }
    }

    private class TransferButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            clientAccountView.dispose();
            new MoneyTransferController()
        }
    }
}
