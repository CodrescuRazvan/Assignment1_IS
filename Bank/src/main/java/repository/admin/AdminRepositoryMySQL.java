package repository.admin;

import model.User;
import model.builder.UserBuilder;
import repository.EntityNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminRepositoryMySQL implements AdminRepository{

    private final Connection connection;

    public AdminRepositoryMySQL(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String sql = "Select * from user";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                users.add(getUserFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    @Override
    public User findById(Long id) throws EntityNotFoundException {
        try {
            Statement statement = connection.createStatement();
            String sql = "Select * from user where id=" + id;
            ResultSet rs = statement.executeQuery(sql);

            if (rs.next()) {
                return getUserFromResultSet(rs);
            } else {
                throw new EntityNotFoundException(id, User.class.getSimpleName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new EntityNotFoundException(id, User.class.getSimpleName());
        }
    }

    @Override
    public boolean saveEmployee(User user) {
        try {
            PreparedStatement insertStatement = connection
                    .prepareStatement("INSERT INTO user values (null, ?, ?)");
            insertStatement.setString(1, user.getUsername());
            insertStatement.setString(2, user.getPassword());
            insertStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void deleteEmployee(Long id) {
        try{
            Statement statement = connection.createStatement();
            String sql = "DELETE from user where id = " + id;
            statement.executeUpdate(sql);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateEmployee(User user) {
        try{
            Statement statement = connection.createStatement();
            String sql;
            if(!user.getUsername().equals("")) {
                sql = "UPDATE user SET username = \'" + user.getUsername() +"\' WHERE id = " + user.getId();
                statement.executeUpdate(sql);
            }
            if(!user.getPassword().equals("")){
                sql = "UPDATE user SET password = \'" + user.getPassword() +"\' WHERE id = " + user.getId();
                statement.executeUpdate(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private User getUserFromResultSet(ResultSet rs) throws SQLException {
        return new UserBuilder()
                .setId(rs.getLong("id"))
                .setUsername(rs.getString("username"))
                .setPassword(rs.getString("password"))
                .build();
    }
}
