package controller.client;

import factory.ComponentFactory;
import model.Client;
import model.builder.ClientBuilder;
import repository.EntityNotFoundException;
import repository.account.AccountRepository;
import repository.client.ClientRepository;
import view.client.AddInfoView;
import view.client.ClientInfoView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddInfoController {

    private final AddInfoView addInfoView;
    private final AccountRepository accountRepository;
    private final ClientRepository clientRepository;

    public AddInfoController(AddInfoView addInfoView, ComponentFactory componentFactory) throws HeadlessException {
        this.addInfoView = addInfoView;
        this.accountRepository = componentFactory.getAccountRepository();
        this.clientRepository = componentFactory.getClientRepository();
        addInfoView.setSubmitButtonListener(new SubmitButtonListener());
        addInfoView.setBackButtonListener(new BackButtonListener());
    }

    private class SubmitButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Long PNC = Long.parseLong(addInfoView.getPNC());
            String name = addInfoView.getName();
            String cardNumber = addInfoView.getCardNumber();
            String address = addInfoView.getAddress();
            Long accountId = Long.parseLong(addInfoView.getAccountId());

            try {
                Client client = new ClientBuilder()
                        .setPNC(PNC)
                        .setName(name)
                        .setCardNumber(cardNumber)
                        .setAddress(address)
                        .setClientAccount(accountRepository.findById(accountId))
                        .build();
                clientRepository.saveClient(client);
            } catch (EntityNotFoundException entityNotFoundException) {
                entityNotFoundException.printStackTrace();
            }
        }
    }

    private class BackButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            addInfoView.dispose();
            new ClientInfoController(new ClientInfoView());

        }
    }
}
