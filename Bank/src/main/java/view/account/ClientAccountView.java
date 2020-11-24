package view.account;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static javax.swing.BoxLayout.Y_AXIS;

public class ClientAccountView extends JFrame {

    private JButton btnCreate;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JButton btnTransfer;
    private JButton btnView;
    private JButton btnBack;

    public ClientAccountView() throws HeadlessException {
        setSize(300, 300);
        setLocationRelativeTo(null);
        initializeFields();
        setLayout(new BoxLayout(getContentPane(), Y_AXIS));
        //if(employee.getRoles().getRights().contains("create_account")
        add(btnCreate);
        add(btnUpdate);
        add(btnDelete);
        add(btnView);
        add(btnTransfer);
        add(btnBack);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initializeFields() {
        btnCreate = new JButton("Add New Client Account");
        btnUpdate = new JButton("Update Existing Client Account");
        btnDelete = new JButton("Delete Existing Client Account");
        btnView = new JButton("View Client Account Informations");
        btnTransfer = new JButton("Transfer Money Between Accounts");
        btnBack = new JButton("Back");
    }

    public void setCreateButtonListener(ActionListener createButtonListener) {
        btnCreate.addActionListener(createButtonListener);
    }

    public void setUpdateButtonListener(ActionListener updateButtonListener) {
        btnUpdate.addActionListener(updateButtonListener);
    }

    public void setDeleteButtonListener(ActionListener deleteButtonListener) {
        btnDelete.addActionListener(deleteButtonListener);
    }

    public void setViewButtonListener(ActionListener viewButtonListener) {
        btnView.addActionListener(viewButtonListener);
    }

    public void setBackButtonListener(ActionListener backButtonListener) {
        btnBack.addActionListener(backButtonListener);
    }

    public void setTransferButtonListener(ActionListener transferButtonListener){
        btnTransfer.addActionListener(transferButtonListener);
    }
}
