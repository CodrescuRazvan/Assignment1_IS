package model.builder;

import model.Client;
import model.Account;

public class ClientBuilder {

    private Client client;

    public ClientBuilder() {
        client = new Client();
    }

    public ClientBuilder setName(String name) {
        client.setName(name);
        return this;
    }

    public ClientBuilder setAddress(String address) {
        client.setAddress(address);
        return this;
    }

    public ClientBuilder setCardNumber(String cardNumber) {
        client.setCardNumber(cardNumber);
        return this;
    }

    public ClientBuilder setPNC(Long PNC) {
        client.setPNC(PNC);
        return this;
    }

    public ClientBuilder setClientAccount(Account account) {
        client.setClientAccount(account);
        return this;
    }

    public Client build() {
        return client;
    }
}
