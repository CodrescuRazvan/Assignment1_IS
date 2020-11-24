package controller.account;

import factory.ComponentFactory;
import model.Account;
import model.builder.AccountBuilder;
import repository.EntityNotFoundException;
import repository.account.AccountRepository;
import view.account.ClientAccountView;
import view.account.MoneyTransferView;
import view.account.UpdateAccountView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class MoneyTransferController {

    private final MoneyTransferView moneyTransferView;
    private final AccountRepository accountRepository;

    public MoneyTransferController(MoneyTransferView moneyTransferView, ComponentFactory componentFactory) throws HeadlessException {
        this.moneyTransferView = moneyTransferView;
        this.accountRepository = componentFactory.getAccountRepository();
        moneyTransferView.setBackButtonListener(new MoneyTransferController.BackButtonListener());
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

            try {
                accountSt = accountRepository.findById(idSt);
                accountNd = accountRepository.findById(idNd);


            } catch (EntityNotFoundException entityNotFoundException) {
                entityNotFoundException.printStackTrace();
            }
        }
    }
}
