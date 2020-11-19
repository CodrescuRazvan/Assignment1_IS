package view.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class UpdateInfoView extends JFrame{

    private JButton btnBack;
    private JButton btnSubmit;

    private JTextField tfPNC;
    private JLabel lbPNC;

    private JTextField tfName;
    private JLabel lbName;

    private JTextField tfCardNumber;
    private JLabel lbCardNumber;

    private JTextField tfAddress;
    private JLabel lbAddress;

    private JTextField tfAccountId;
    private JLabel lbAccountId;

    public UpdateInfoView() throws HeadlessException {
        setSize(300, 300);
        setLocationRelativeTo(null);
        initializeFields();
        setLayout(new GridLayout(7, 2));

        add(lbPNC);
        add(tfPNC);

        add(lbName);
        add(tfName);

        add(lbCardNumber);
        add(tfCardNumber);

        add(lbAddress);
        add(tfAddress);

        add(lbAccountId);
        add(tfAccountId);

        add(btnSubmit);
        add(btnBack);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initializeFields() {
        tfPNC = new JTextField(null);
        lbPNC = new JLabel("PNC");

        tfName = new JTextField(null);
        lbName = new JLabel("Name");

        tfCardNumber = new JTextField(null);
        lbCardNumber = new JLabel("Card Number");

        tfAddress = new JTextField(null);
        lbAddress = new JLabel("Address");

        tfAccountId = new JTextField(null);
        lbAccountId = new JLabel("Account Id");

        btnSubmit = new JButton("Submit");
        btnBack = new JButton("Back");
    }

    public String getName() {
        return tfName.getText();
    }

    public String getCardNumber() {
        return tfCardNumber.getText();
    }

    public String getAddress() {
        return tfAddress.getText();
    }

    public String getAccountId() {
        return tfAccountId.getText();
    }

    public String getPNC() {
        return tfPNC.getText();
    }

    public void setSubmitButtonListener(ActionListener submitButtonListener) {
        btnSubmit.addActionListener(submitButtonListener);
    }

    public void setBackButtonListener(ActionListener backButtonListener) {
        btnBack.addActionListener(backButtonListener);
    }
}
