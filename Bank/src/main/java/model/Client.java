package model;

public class Client {

    private Long PNC;
    private String name;
    private String cardNumber;
    private String address;
    private Account account;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Long getPNC() {
        return PNC;
    }

    public void setPNC(Long PNC) {
        this.PNC = PNC;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Account getClientAccount() {
        return account;
    }

    public void setClientAccount(Account account) {
        this.account = account;
    }
}