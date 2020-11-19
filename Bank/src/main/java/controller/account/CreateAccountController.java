package controller.account;

import factory.ComponentFactory;
import model.Account;
import model.builder.AccountBuilder;
import repository.account.AccountRepository;
import view.account.ClientAccountView;
import view.account.CreateAccountView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class CreateAccountController {

    private final CreateAccountView createAccountView;
    private final AccountRepository accountRepository;

    public CreateAccountController(CreateAccountView createAccountView, ComponentFactory componentFactory) throws HeadlessException {
        this.createAccountView = createAccountView;
        this.accountRepository = componentFactory.getAccountRepository();
        createAccountView.setBackButtonListener(new BackButtonListener());
        createAccountView.setSubmitButtonListener(new SubmitButtonListener());
    }

    private class BackButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            createAccountView.dispose();
            new ClientAccountController(new ClientAccountView());

        }
    }

    private class SubmitButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e){
            String type = createAccountView.getAccType();
            String amountOfMoney = createAccountView.getAmountOfMoney();

            Account account = new AccountBuilder()
                    .setType(type)
                    .setAmountOfMoney(Long.parseLong(amountOfMoney))
                    .setDateOfCreation(new Date())
                    .build();
            accountRepository.saveAccount(account);
        }
    }
}
