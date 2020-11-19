package repository.account;

import model.Account;
import model.builder.AccountBuilder;
import repository.EntityNotFoundException;

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
    public void deleteAccount(Long id){
        try{
            Statement statement = connection.createStatement();
            String sql = "DELETE from account where id = " + id;
            statement.executeUpdate(sql);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateAccount(Account account) {
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
        } catch (SQLException e) {
            e.printStackTrace();
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

    private Account getAccountFromResultSet(ResultSet rs) throws SQLException {
        return new AccountBuilder()
                .setId(rs.getLong("id"))
                .setType(rs.getString("type"))
                .setAmountOfMoney(rs.getLong("amount_of_money"))
                .setDateOfCreation(new Date(rs.getDate("date_of_creation").getTime()))
                .build();
    }
}
