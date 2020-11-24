package view.user;

import factory.ComponentFactory;
import model.Client;
import model.User;
import repository.admin.AdminRepository;
import repository.client.ClientRepository;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

import static javax.swing.BoxLayout.Y_AXIS;

public class AdminViewView extends JFrame{

    private JButton btnBack;
    private DefaultTableModel tableModel;
    private JTable table;
    private AdminRepository adminRepository;

    public AdminViewView(ComponentFactory componentFactory) throws HeadlessException {
        adminRepository = componentFactory.getAdminRepository();
        setSize(600, 400);
        setLocationRelativeTo(null);
        initializeFields();
        setLayout(new GridLayout(2, 1));

        add(table);
        add(btnBack);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initializeFields() {
        btnBack = new JButton("Back");
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        tableModel.addColumn("ID");
        tableModel.addColumn("Username");
        tableModel.addColumn("Password");
        tableModel.addRow(new Object[]{"ID", "Username", "Password"});

        List<User> users = adminRepository.findAll();

        for(User user : users){
            Long d1 = user.getId();
            String d2 = user.getUsername();
            String d3 = user.getPassword();

            tableModel.addRow(new Object[] {d1, d2, d3});
        }

    }

    public void setBackButtonListener(ActionListener backButtonListener) {
        btnBack.addActionListener(backButtonListener);
    }
}
