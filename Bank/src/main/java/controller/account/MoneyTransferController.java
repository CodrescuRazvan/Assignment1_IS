package controller.account;

import factory.ComponentFactory;
import model.Account;
import model.validation.Notification;
import repository.account.AccountRepository;
import service.account.AccountVerificationService;
import view.account.ClientAccountView;
import view.account.MoneyTransferView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MoneyTransferController {

    private final MoneyTransferView moneyTransferView;
    private final AccountRepository accountRepository;
    private final AccountVerificationService accountVerificationService;

    public MoneyTransferController(MoneyTransferView moneyTransferView, ComponentFactory componentFactory) throws HeadlessException {
        this.moneyTransferView = moneyTransferView;
        this.accountRepository = componentFactory.getAccountRepository();
        this.accountVerificationService = componentFactory.getAccountVerificationService();
        moneyTransferView.setBackButtonListener(new MoneyTransferController.BackButtonListener());
        moneyTransferView.setSubmitButtonListener(new MoneyTransferController.SubmitButtonListener());
    }

    private class BackButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            moneyTransferView.dispose();
            new ClientAccountController(new ClientAccountView());

        }
    }

    private class SubmitButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Long idSt = Long.parseLong(moneyTransferView.getIdSt());
            Long idNd = Long.parseLong(moneyTransferView.getIdNd());

            Long moneyAmount = Long.parseLong(moneyTransferView.getMoneyAmount());

            Account accountSt;
            Account accountNd;

            Notification<Boolean> transferNotification = null;
            try{
                accountSt = accountRepository.findById(idSt);
                accountNd = accountRepository.findById(idNd);
                transferNotification = accountVerificationService.transferMoney(accountSt, accountNd, moneyAmount);
            } catch (Exception e1){
                e1.printStackTrace();
            }

            if(transferNotification != null){
                if(transferNotification.hasErrors()){
                    JOptionPane.showMessageDialog(moneyTransferView.getContentPane(), transferNotification.getFormattedErrors());
                } else {
                    JOptionPane.showMessageDialog(moneyTransferView.getContentPane(), "Money transfered!");
                }
            }

        }
    }
}
