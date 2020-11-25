package repository.account;

import model.Account;
import model.builder.AccountBuilder;
import repository.EntityNotFoundException;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountRepositoryMySQL implements AccountRepository{

    private final Connection connection;

    public AccountRepositoryMySQL(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Account> findAll() {
        List<Account> accounts = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String sql = "Select * from account";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                accounts.add(getAccountFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return accounts;
    }

    @Override
    public Account findById(Long id) throws EntityNotFoundException {
        try {
            Statement statement = connection.createStatement();
            String sql = "Select * from account where id=" + id;
            ResultSet rs = statement.executeQuery(sql);

            if (rs.next()) {
                return getAccountFromResultSet(rs);
            } else {
                throw new EntityNotFoundException(id, Account.class.getSimpleName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new EntityNotFoundException(id, Account.class.getSimpleName());
        }
    }

    @Override
    public boolean saveAccount(Account account) {
        try {
            PreparedStatement insertStatement = connection
                    .prepareStatement("INSERT INTO account values (null, ?, ?, ?)");
            insertStatement.setString(1, account.getType());
            insertStatement.setLong(2, account.getAmountOfMoney());
            insertStatement.setDate(3, new java.sql.Date(account.getDateOfCreation().getTime()));
            insertStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteAccount(Long id){
        try{
            Statement statement = connection.createStatement();
            String sql = "DELETE from account where id = " + id;
            statement.executeUpdate(sql);
            return true;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateAccount(Account account) {
        try{
            Statement statement = connection.createStatement();
            String sql;
            if(!account.getType().equals("")) {
                sql = "UPDATE account SET type = \'" + account.getType() +"\' WHERE id = " + account.getId();
                statement.executeUpdate(sql);
            }
            if(account.getAmountOfMoney() != null){
                sql = "UPDATE account SET amount_of_money = " + account.getAmountOfMoney() +" WHERE id = " + account.getId();
                statement.executeUpdate(sql);
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void removeAll() {
        try {
            Statement statement = connection.createStatement();
            String sql = "DELETE from account where id >= 0";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean transferMoney(Account account1, Account account2, Long money){
        Long value1 = account1.getAmountOfMoney();
        Long value2 = account2.getAmountOfMoney();

        account1.setAmountOfMoney(value1 - money);
        account2.setAmountOfMoney(value2 + money);

        updateAccount(account1);
        updateAccount(account2);
        return true;
    }

    @Override
    public boolean generateBill(Account account, Long water, Long gas, Long electricity) throws IOException {
        Long value = account.getAmountOfMoney();

        Long id = account.getId();
        FileWriter fileWriter = new FileWriter("Bill" + id +".txt");
        fileWriter.write("Bill for client with account ID " + account.getId() + ": ");
        fileWriter.write("\nAccount balance :" + account.getAmountOfMoney());
        if(value > water) {
            account.setAmountOfMoney(value - water);
            updateAccount(account);
            fileWriter.write("\nWater bill payed successfully! Bill value " + water + "\nAccount balance :" + account.getAmountOfMoney());
        }

        value = account.getAmountOfMoney();
        if(value > gas) {
            account.setAmountOfMoney(value - gas);
            updateAccount(account);
            fileWriter.write("\nGas bill payed successfully! Bill value " + gas + "\nAccount balance :" + account.getAmountOfMoney());
        }

        value = account.getAmountOfMoney();
        if(value > electricity) {
            account.setAmountOfMoney(value - electricity);
            updateAccount(account);
            fileWriter.write("\nElectricity bill payed successfully! Bill value " + electricity + "\nAccount balance :" + account.getAmountOfMoney());
        }

        fileWriter.close();
        return true;
    }


    private Account getAccountFromResultSet(ResultSet rs) throws SQLException {
        return new AccountBuilder()
                .setId(rs.getLong("id"))
                .setType(rs.getString("type"))
                .setAmountOfMoney(rs.getLong("amount_of_money"))
                .setDateOfCreation(new Date(rs.getDate("date_of_creation").getTime()))
                .build();
    }
}
