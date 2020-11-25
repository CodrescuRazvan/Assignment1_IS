package repository.client;

import factory.ComponentFactory;
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
        this.accountRepository = accountRepository;
    }

    @Override
    public List<Client> findAll() {
        List<Client> clients = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String sql = "Select * from client";
            ResultSet rs = statement.executeQuery(sql);
            ComponentFactory componentFactory = ComponentFactory.instance(false);
            List<String> statements = componentFactory.getStatements();
            statements.add("Client info's viewed");
            componentFactory.setStatements(statements);
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
                    .prepareStatement("INSERT INTO client values (?, ?, ?, ?, 0)");
            insertStatement.setString(1, client.getPNC().toString());
            insertStatement.setString(2, client.getName());
            insertStatement.setString(3, client.getCardNumber());
            insertStatement.setString(4, client.getAddress());
            insertStatement.executeUpdate();
            ComponentFactory componentFactory = ComponentFactory.instance(false);
            List<String> statement = componentFactory.getStatements();
            statement.add("New client saved with data : "
                    + client.getPNC()
                    + ", "
                    + client.getName()
                    + ", "
                    + client.getCardNumber()
                    + ", "
                    + client.getAddress());
            componentFactory.setStatements(statement);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateClient(Long PNC, String name, String cardNumber, String address, Account client){
        try{
            Statement statement = connection.createStatement();
            String sql;
            ComponentFactory componentFactory = ComponentFactory.instance(false);
            List<String> statements = componentFactory.getStatements();
            if(!name.equals("")) {
                sql = "UPDATE client SET name = \'" + name +"\' WHERE PNC = " + PNC;
                statement.executeUpdate(sql);
                statements.add("Updated client with PNC " + PNC + " name's " + name);
                componentFactory.setStatements(statements);
            }
            if(!cardNumber.equals("")) {
                sql = "UPDATE client SET card_number = " + Long.parseLong(cardNumber) +" WHERE PNC = " + PNC;
                statement.executeUpdate(sql);
                statements.add("Updated client with PNC " + PNC + " card number " + cardNumber);
                componentFactory.setStatements(statements);
            }
            if(!address.equals("")) {
                sql = "UPDATE client SET address = \'" + address +"\' WHERE PNC = " + PNC;
                statement.executeUpdate(sql);
                statements.add("Updated client with PNC " + PNC + " address' " + address);
                componentFactory.setStatements(statements);
            }
            if(client.getId() != null) {
                sql = "UPDATE client SET account_id = " + client.getId() +" WHERE PNC = " + PNC;
                statement.executeUpdate(sql);
                statements.add("Updated client with PNC " + PNC + " account ID " + client.getId());
                componentFactory.setStatements(statements);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
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