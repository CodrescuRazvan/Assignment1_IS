package view.user;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AdminView extends JFrame{


    private JTextField tfUsername;
    private JTextField tfPassword;

    private JTextField tfId;
    private JLabel lbId;

    private JLabel lbUsername;
    private JLabel lbPassword;

    private JButton btnCreate;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JButton btnView;
    private JButton btnGenerateReport;

    private JButton btnBack;

    public AdminView() throws HeadlessException {
        setSize(300, 300);
        setLocationRelativeTo(null);
        initializeFields();
        setLayout(new GridLayout(7, 2));

        add(lbId);
        add(tfId);

        add(lbUsername);
        add(tfUsername);

        add(lbPassword);
        add(tfPassword);

        add(btnCreate);
        add(btnUpdate);
        add(btnDelete);
        add(btnDelete);
        add(btnView);

        add(btnGenerateReport);
        add(btnBack);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initializeFields() {
        tfId = new JTextField();
        tfUsername = new JTextField();
        tfPassword = new JTextField();

        lbId = new JLabel("ID");
        lbUsername = new JLabel("Username");
        lbPassword = new JLabel("Password");

        btnCreate = new JButton("Create");
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");
        btnView = new JButton("View");
        btnGenerateReport = new JButton("Generate report");

        btnBack = new JButton("Back");
    }

    public String getId() {
        return tfId.getText();
    }

    public String getUsername() {
        return tfUsername.getText();
    }

    public String getPassword() {
        return tfPassword.getText();
    }

    public void setBackButtonListener(ActionListener backButtonListener) {
        btnBack.addActionListener(backButtonListener);
    }

    public void setCreateButtonListener(ActionListener createButtonListener){
        btnCreate.addActionListener(createButtonListener);
    }

    public void setUpdateButtonListener(ActionListener updateButtonListener){
        btnUpdate.addActionListener(updateButtonListener);
    }

    public void setDeleteButtonListener(ActionListener deleteButtonListener){
        btnDelete.addActionListener(deleteButtonListener);
    }

    public void setViewButtonListener(ActionListener viewButtonListener){
        btnView.addActionListener(viewButtonListener);
    }

    public void setGenerateButtonListener(ActionListener generateButtonListener){
        btnGenerateReport.addActionListener(generateButtonListener);
    }
}
