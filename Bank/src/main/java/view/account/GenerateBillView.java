package view.account;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GenerateBillView extends JFrame{

    private JButton btnBack;
    private JButton btnSubmit;

    private JTextField tfAccountId;
    private JLabel lbAccountId;

    private JTextField tfWater;
    private JLabel lbWater;

    private JTextField tfGas;
    private JLabel lbGas;

    private JTextField tfElectricity;
    private JLabel lbElectricity;

    public GenerateBillView() throws HeadlessException {
        setSize(300, 300);
        setLocationRelativeTo(null);
        initializeFields();
        setLayout(new GridLayout(5, 2));

        add(lbAccountId);
        add(tfAccountId);

        add(lbWater);
        add(tfWater);

        add(lbGas);
        add(tfGas);

        add(lbElectricity);
        add(tfElectricity);

        add(btnSubmit);
        add(btnBack);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initializeFields() {
        tfAccountId = new JTextField();
        lbAccountId = new JLabel("Account ID");

        tfWater = new JTextField();
        lbWater = new JLabel("Water");

        tfGas = new JTextField();
        lbGas = new JLabel("Gas");

        tfElectricity = new JTextField();
        lbElectricity = new JLabel("Electricity");

        btnSubmit = new JButton("Submit");
        btnBack = new JButton("Back");
    }

    public String getAccountId() {
        return tfAccountId.getText();
    }

    public String getWater() {
        return tfWater.getText();
    }

    public String getGas() {
        return tfGas.getText();
    }

    public String getElectricity() {
        return tfElectricity.getText();
    }

    public void setSubmitButtonListener(ActionListener submitButtonListener) {
        btnSubmit.addActionListener(submitButtonListener);
    }

    public void setBackButtonListener(ActionListener backButtonListener) {
        btnBack.addActionListener(backButtonListener);
    }
}
