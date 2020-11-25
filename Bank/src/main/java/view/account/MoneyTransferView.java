package view.account;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MoneyTransferView extends JFrame{

    private JButton btnBack;
    private JButton btnSubmit;

    private JTextField tfIdSt;
    private JLabel lbIdSt;

    private JTextField tfIdNd;
    private JLabel lbIdNd;

    private JTextField tfMoneyAmount;
    private JLabel lbMoneyAmount;

    public MoneyTransferView() throws HeadlessException {
        setSize(300, 300);
        setLocationRelativeTo(null);
        initializeFields();
        setLayout(new GridLayout(4, 2));

        add(lbIdSt);
        add(tfIdSt);

        add(lbIdNd);
        add(tfIdNd);

        add(lbMoneyAmount);
        add(tfMoneyAmount);

        add(btnSubmit);
        add(btnBack);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initializeFields() {
        tfIdSt = new JTextField();
        lbIdSt = new JLabel("Transfer Account");

        tfIdNd = new JTextField();
        lbIdNd = new JLabel("Receiver Account");

        tfMoneyAmount = new JTextField();
        lbMoneyAmount = new JLabel("Money Amount");

        btnSubmit = new JButton("Submit");
        btnBack = new JButton("Back");
    }

    public String getIdSt() {
        return tfIdSt.getText();
    }

    public String getIdNd() {
        return tfIdNd.getText();
    }

    public String getMoneyAmount() {
        return tfMoneyAmount.getText();
    }

    public void setSubmitButtonListener(ActionListener submitButtonListener) {
        btnSubmit.addActionListener(submitButtonListener);
    }

    public void setBackButtonListener(ActionListener backButtonListener) {
        btnBack.addActionListener(backButtonListener);
    }
}
