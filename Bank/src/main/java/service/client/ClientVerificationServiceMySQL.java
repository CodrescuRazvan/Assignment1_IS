package service.client;

import model.Account;
import model.Client;
import model.builder.ClientBuilder;
import model.validation.InfoClientValidator;
import model.validation.Notification;
import model.validation.UpdateClientValidator;
import repository.client.ClientRepository;

public class ClientVerificationServiceMySQL implements ClientVerificationService{

    private final ClientRepository clientRepository;

    public ClientVerificationServiceMySQL(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Notification<Boolean> saveClient(Long PNC, String name, String cardNumber, String address) {
        Client client = new ClientBuilder()
                .setPNC(PNC)
                .setName(name)
                .setCardNumber(cardNumber)
                .setAddress(address)
                .build();
        InfoClientValidator infoClientValidator = new InfoClientValidator(client);
        boolean clientValid = infoClientValidator.validate();
        Notification<Boolean> clientAddNotification = new Notification<>();

        if(!clientValid){
            infoClientValidator.getErrors().forEach(clientAddNotification::addError);
            clientAddNotification.setResult(Boolean.FALSE);
        } else {
            clientAddNotification.setResult(clientRepository.saveClient(client));
        }

        return clientAddNotification;
    }

    @Override
    public Notification<Boolean> updateClient(Long PNC, String name, String cardNumber, String address, Account client) {
        Client client1 = new Client();
        client1.setPNC(PNC);
        client1.setName(name);
        client1.setCardNumber(cardNumber);
        client1.setAddress(address);
        client1.setClientAccount(client);
        UpdateClientValidator updateClientValidator = new UpdateClientValidator(client1);
        boolean updateValid = updateClientValidator.validate();
        Notification<Boolean> updateNotification = new Notification<>();

        if(!updateValid){
            updateClientValidator.getErrors().forEach(updateNotification::addError);
            updateNotification.setResult(Boolean.FALSE);
        } else {
            updateNotification.setResult(clientRepository.updateClient(PNC, name, cardNumber, address, client));
        }

        return updateNotification;
    }
}
