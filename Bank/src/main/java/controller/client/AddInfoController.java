package controller.client;

import factory.ComponentFactory;
import model.Client;
import model.builder.ClientBuilder;
import model.validation.Notification;
import repository.EntityNotFoundException;
import repository.account.AccountRepository;
import repository.client.ClientRepository;
import service.client.ClientVerificationService;
import view.client.AddInfoView;
import view.client.ClientInfoView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddInfoController {

    private final AddInfoView addInfoView;
    private final ClientVerificationService clientVerificationService;

    public AddInfoController(AddInfoView addInfoView, ComponentFactory componentFactory) throws HeadlessException {
        this.addInfoView = addInfoView;
        this.clientVerificationService = componentFactory.getClientVerificationService();
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

            Notification<Boolean> clientNotification = null;
            try{
                clientNotification = clientVerificationService.saveClient(PNC, name, cardNumber, address);
            } catch (Exception e1){
                e1.printStackTrace();
            }

            if(clientNotification != null){
                if(clientNotification.hasErrors()){
                    JOptionPane.showMessageDialog(addInfoView.getContentPane(), clientNotification.getFormattedErrors());
                } else {
                    JOptionPane.showMessageDialog(addInfoView.getContentPane(), "Client added!");
                }
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
