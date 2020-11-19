package view.account;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CreateAccountView extends JFrame{

    private JButton btnBack;
    private JButton btnSubmit;

    private JTextField tfType;
    private JLabel lbType;

    private JTextField tfAmountOfMoney;
    private JLabel lbAmountOfMoney;

    public CreateAccountView() throws HeadlessException {
        setSize(300, 300);
        setLocationRelativeTo(null);
        initializeFields();
        setLayout(new GridLayout(4, 2));

        add(lbType);
        add(tfType);

        add(lbAmountOfMoney);
        add(tfAmountOfMoney);

        add(btnSubmit);
        add(btnBack);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initializeFields() {
        tfType = new JTextField();
        lbType = new JLabel("Type");

        tfAmountOfMoney = new JTextField();
        lbAmountOfMoney = new JLabel("Amount of Money");

        btnSubmit = new JButton("Submit");
        btnBack = new JButton("Back");
    }

    public String getAccType() {
        return tfType.getText();
    }

    public String getAmountOfMoney() {
        return tfAmountOfMoney.getText();
    }

    public void setSubmitButtonListener(ActionListener submitButtonListener) {
        btnSubmit.addActionListener(submitButtonListener);
    }

    public void setBackButtonListener(ActionListener backButtonListener) {
        btnBack.addActionListener(backButtonListener);
    }
}
