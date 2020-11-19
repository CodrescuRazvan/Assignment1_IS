package repository.client;

import model.Account;
import model.Client;
import model.builder.ClientBuilder;
import repository.EntityNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import repository.account.AccountRepository;
import repository.account.AccountRepositoryMySQL;

public class ClientRepositoryMySQL implements ClientRepository {

    private final Connection connection;

    private AccountRepository accountRepository;

    public ClientRepositoryMySQL(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Client> findAll() {
        List<Client> clients = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String sql = "Select * from client";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                clients.add(getClientFromResultSet(rs));
            }
        } catch (SQLException | EntityNotFoundException e) {
            e.printStackTrace();
        }

        return clients;
    }

    @Override
    public Client findByPNC(Long PNC) throws EntityNotFoundException {
        try {
            Statement statement = connection.createStatement();
            String sql = "Select * from client where PNC=" + PNC;
            ResultSet rs = statement.executeQuery(sql);

            if (rs.next()) {
                return getClientFromResultSet(rs);
            } else {
                throw new EntityNotFoundException(PNC, Client.class.getSimpleName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new EntityNotFoundException(PNC, Client.class.getSimpleName());
        }
    }

    @Override
    public boolean saveClient(Client client) {
        try {
            PreparedStatement insertStatement = connection
                    .prepareStatement("INSERT INTO client values (?, ?, ?, ?, ?)");
            insertStatement.setString(1, client.getPNC().toString());
            insertStatement.setString(2, client.getName());
            insertStatement.setString(3, client.getCardNumber());
            insertStatement.setString(4, client.getAddress());
            insertStatement.setLong(5, client.getClientAccount().getId());
            insertStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void updateClient(Client client){
        try{
            Statement statement = connection.createStatement();
            String sql;
            if(!client.getName().equals("")) {
                sql = "UPDATE client SET name = \'" + client.getName() +"\' WHERE PNC = " + client.getPNC();
                statement.executeUpdate(sql);
            }
            if(!client.getCardNumber().equals("")) {
                sql = "UPDATE client SET card_number = \'" + client.getCardNumber() +"\' WHERE PNC = " + client.getPNC();
                statement.executeUpdate(sql);
            }
            if(!client.getAddress().equals("")) {
                sql = "UPDATE client SET address = \'" + client.getAddress() +"\' WHERE PNC = " + client.getPNC();
                statement.executeUpdate(sql);
            }
            if(client.getClientAccount().getId() != null) {
                sql = "UPDATE client SET account_id = " + client.getClientAccount().getId() +" WHERE PNC = " + client.getPNC();
                statement.executeUpdate(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeAll() {
        try {
            Statement statement = connection.createStatement();
            String sql = "DELETE from client where PNC >= 0";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Client getClientFromResultSet(ResultSet rs) throws SQLException, EntityNotFoundException {
        return new ClientBuilder()
                .setPNC(rs.getLong("PNC"))
                .setName(rs.getString("name"))
                .setCardNumber(rs.getString("card_number"))
                .setAddress(rs.getString("address"))
                .setClientAccount(accountRepository.findById(rs.getLong("account_id")))
                .build();
    }

}