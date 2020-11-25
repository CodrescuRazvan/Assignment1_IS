package controller.account;

import controller.client.ClientInfoController;
import factory.ComponentFactory;
import model.Account;
import model.validation.Notification;
import repository.account.AccountRepository;
import service.account.AccountVerificationService;
import view.account.ClientAccountView;
import view.account.GenerateBillView;
import view.client.ClientInfoView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GenerateBillController {

    private final GenerateBillView generateBillView;
    private final AccountRepository accountRepository;
    private final AccountVerificationService accountVerificationService;

    public GenerateBillController(GenerateBillView generateBillView, ComponentFactory componentFactory) throws HeadlessException {
        this.generateBillView = generateBillView;
        this.accountRepository = componentFactory.getAccountRepository();
        this.accountVerificationService = componentFactory.getAccountVerificationService();
        generateBillView.setBackButtonListener(new GenerateBillController.BackButtonListener());
        generateBillView.setSubmitButtonListener(new GenerateBillController.SubmitButtonListener());
    }

    private class BackButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            generateBillView.dispose();
            new ClientAccountController(new ClientAccountView());

        }
    }

    private class SubmitButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Long accountId = Long.parseLong(generateBillView.getAccountId());
            Long waterAmount = Long.parseLong(generateBillView.getWater());
            Long gasAmount = Long.parseLong(generateBillView.getGas());
            Long electricityAmount = Long.parseLong(generateBillView.getElectricity());

            Account account;

            Notification<Boolean> transferNotification = null;
            try{
                account = accountRepository.findById(accountId);
                transferNotification = accountVerificationService.generateBill(account, waterAmount, gasAmount, electricityAmount);
            } catch (Exception e1){
                e1.printStackTrace();
            }

            if(transferNotification != null){
                if(transferNotification.hasErrors()){
                    JOptionPane.showMessageDialog(generateBillView.getContentPane(), transferNotification.getFormattedErrors());
                } else {
                    JOptionPane.showMessageDialog(generateBillView.getContentPane(), "Bill generated!");
                }
            }

        }
    }
}
