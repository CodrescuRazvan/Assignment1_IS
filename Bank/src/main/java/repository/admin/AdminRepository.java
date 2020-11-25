package repository.admin;

import model.Account;
import model.User;
import repository.EntityNotFoundException;

import java.io.IOException;
import java.util.List;

public interface AdminRepository {

    List<User> findAll();

    User findById(Long id) throws EntityNotFoundException;

    boolean saveEmployee(User user);

    void deleteEmployee(Long id);

    void updateEmployee(User user);

    public void generateReport() throws IOException;
}
