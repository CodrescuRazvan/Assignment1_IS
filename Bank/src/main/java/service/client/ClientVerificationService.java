package service.client;

import model.Account;
import model.validation.Notification;

public interface ClientVerificationService {

    Notification<Boolean> saveClient(Long PNC, String name, String cardNumber, String address);

    Notification<Boolean> updateClient(Long PNC, String name, String cardNumber, String address, Account client);
}
