package view.account;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class DeleteAccountView extends JFrame {

    private JButton btnBack;
    private JButton btnSubmit;

    private JTextField tfId;
    private JLabel lbId;

    public DeleteAccountView() throws HeadlessException {
        setSize(300, 300);
        setLocationRelativeTo(null);
        initializeFields();
        setLayout(new GridLayout(3, 2));

        add(lbId);
        add(tfId);

        add(btnSubmit);
        add(btnBack);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initializeFields() {
        tfId = new JTextField();
        lbId = new JLabel("ID");

        btnSubmit = new JButton("Submit");
        btnBack = new JButton("Back");
    }

    public String getId() {
        return tfId.getText();
    }

    public void setSubmitButtonListener(ActionListener submitButtonListener) {
        btnSubmit.addActionListener(submitButtonListener);
    }

    public void setBackButtonListener(ActionListener backButtonListener) {
        btnBack.addActionListener(backButtonListener);
    }
}
