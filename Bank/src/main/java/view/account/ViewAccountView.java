package view.account;

import factory.ComponentFactory;
import model.Account;
import repository.account.AccountRepository;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionListener;

import java.util.Date;
import java.util.List;

public class ViewAccountView extends JFrame{

    private JButton btnBack;
    private DefaultTableModel tableModel;
    private JTable table;
    private AccountRepository accountRepository;

    public ViewAccountView(ComponentFactory componentFactory) throws HeadlessException {
        accountRepository = componentFactory.getAccountRepository();
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
        tableModel.addColumn("Type");
        tableModel.addColumn("Amount Of Money");
        tableModel.addColumn("Date Of Creation");
        tableModel.addRow(new Object[]{"ID", "Type", "Amount Of Money", "Date Of Creation"});
        List<Account> accounts = accountRepository.findAll();
        for(Account account : accounts){
            Long d1 = account.getId();
            String d2 = account.getType();
            Long d3 = account.getAmountOfMoney();
            Date d4 = account.getDateOfCreation();
            tableModel.addRow(new Object[] {d1, d2, d3, d4});
        }

    }

    public void setBackButtonListener(ActionListener backButtonListener) {
        btnBack.addActionListener(backButtonListener);
    }
}
