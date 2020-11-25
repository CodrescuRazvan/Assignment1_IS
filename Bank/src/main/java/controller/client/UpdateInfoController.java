package controller.client;

import factory.ComponentFactory;
import model.Account;
import model.validation.Notification;
import repository.EntityNotFoundException;
import repository.account.AccountRepository;
import service.client.ClientVerificationService;
import view.client.ClientInfoView;
import view.client.UpdateInfoView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateInfoController {

    private final UpdateInfoView updateInfoView;
    private final AccountRepository accountRepository;
    private final ClientVerificationService clientVerificationService;

    public UpdateInfoController(UpdateInfoView updateInfoView, ComponentFactory componentFactory) throws HeadlessException {
        this.updateInfoView = updateInfoView;
        this.clientVerificationService = componentFactory.getClientVerificationService();
        this.accountRepository = componentFactory.getAccountRepository();
        updateInfoView.setSubmitButtonListener(new UpdateInfoController.SubmitButtonListener());
        updateInfoView.setBackButtonListener(new UpdateInfoController.BackButtonListener());
    }

    private class SubmitButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Long PNC = Long.parseLong(updateInfoView.getPNC());
            String name = updateInfoView.getName();
            String cardNumber = updateInfoView.getCardNumber();
            String address = updateInfoView.getAddress();

            Account account = new Account();
            if(!updateInfoView.getAccountId().equals("")) {
                Long accountId = Long.parseLong(updateInfoView.getAccountId());
                try {
                    account = accountRepository.findById(accountId);
                } catch (EntityNotFoundException entityNotFoundException) {
                    entityNotFoundException.printStackTrace();
                }
            }

            Notification<Boolean> updateNotification = null;
            try {
                updateNotification = clientVerificationService.updateClient(PNC, name, cardNumber, address, account);
            } catch (Exception e1) {
                e1.printStackTrace();
            }

            if(updateNotification != null){
                if(updateNotification.hasErrors()){
                    JOptionPane.showMessageDialog(updateInfoView.getContentPane(), updateNotification.getFormattedErrors());
                } else {
                    JOptionPane.showMessageDialog(updateInfoView.getContentPane(), "Informations updated!");
                }
            }
        }
    }

    private class BackButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            updateInfoView.dispose();
            new ClientInfoController(new ClientInfoView());
        }
    }
}
