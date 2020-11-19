package view.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static javax.swing.BoxLayout.Y_AXIS;

public class AddInfoView extends JFrame{

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

    public AddInfoView() throws HeadlessException {
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
        tfPNC = new JTextField();
        lbPNC = new JLabel("PNC");

        tfName = new JTextField();
        lbName = new JLabel("Name");

        tfCardNumber = new JTextField();
        lbCardNumber = new JLabel("Card Number");

        tfAddress = new JTextField();
        lbAddress = new JLabel("Address");

        tfAccountId = new JTextField();
        lbAccountId = new JLabel("Account Id");

        btnSubmit = new JButton("Submit");
        btnBack = new JButton("Back");
    }

    public String getPNC() {
        return tfPNC.getText();
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

    public void setSubmitButtonListener(ActionListener submitButtonListener) {
        btnSubmit.addActionListener(submitButtonListener);
    }

    public void setBackButtonListener(ActionListener backButtonListener) {
        btnBack.addActionListener(backButtonListener);
    }
}
