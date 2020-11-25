package view.client;

import factory.ComponentFactory;
import model.Client;
import repository.client.ClientRepository;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class ViewInfoView extends JFrame{

    private JButton btnBack;
    private DefaultTableModel tableModel;
    private JTable table;
    private ClientRepository clientRepository;

    public ViewInfoView(ComponentFactory componentFactory) throws HeadlessException {
        clientRepository = componentFactory.getClientRepository();
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
        tableModel.addColumn("PNC");
        tableModel.addColumn("Name");
        tableModel.addColumn("Card Number");
        tableModel.addColumn("Address");
        tableModel.addColumn("Account");
        tableModel.addRow(new Object[]{"PNC", "Name", "Card Number", "Address", "Account"});

        List<Client> clients = clientRepository.findAll();

        for(Client client : clients){
            Long d1 = client.getPNC();
            String d2 = client.getName();
            String d3 = client.getCardNumber();
            String d4 = client.getAddress();
            Long d5 = client.getClientAccount().getId();

            tableModel.addRow(new Object[] {d1, d2, d3, d4, d5});
        }

    }

    public void setBackButtonListener(ActionListener backButtonListener) {
        btnBack.addActionListener(backButtonListener);
    }
}
