package controller.client;

import factory.ComponentFactory;
import model.Account;
import model.Client;
import model.builder.ClientBuilder;
import repository.EntityNotFoundException;
import repository.account.AccountRepository;
import repository.client.ClientRepository;
import view.client.ClientInfoView;
import view.client.UpdateInfoView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateInfoController {

    private final UpdateInfoView updateInfoView;
    private final AccountRepository accountRepository;
    private final ClientRepository clientRepository;

    public UpdateInfoController(UpdateInfoView updateInfoView, ComponentFactory componentFactory) throws HeadlessException {
        this.updateInfoView = updateInfoView;
        this.accountRepository = componentFactory.getAccountRepository();
        this.clientRepository = componentFactory.getClientRepository();
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
            if(!updateInfoView.getAccountId().equals("")) {
                Long accountId = Long.parseLong(updateInfoView.getAccountId());
                try {
                    Client client = new ClientBuilder()
                            .setPNC(PNC)
                            .setName(name)
                            .setCardNumber(cardNumber)
                            .setAddress(address)
                            .setClientAccount(accountRepository.findById(accountId))
                            .build();
                    clientRepository.updateClient(client);
                } catch (EntityNotFoundException entityNotFoundException) {
                    entityNotFoundException.printStackTrace();
                }
            } else {
                    Client client = new ClientBuilder()
                            .setPNC(PNC)
                            .setName(name)
                            .setCardNumber(cardNumber)
                            .setAddress(address)
                            .setClientAccount(new Account())
                            .build();
                    clientRepository.updateClient(client);
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
