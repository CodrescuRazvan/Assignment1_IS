package view.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static javax.swing.BoxLayout.Y_AXIS;

public class ClientInfoView extends JFrame {

    private JButton btnAdd;
    private JButton btnUpdate;
    private JButton btnView;
    private JButton btnBack;
    public ClientInfoView() throws HeadlessException {
        setSize(300, 300);
        setLocationRelativeTo(null);
        initializeFields();
        setLayout(new BoxLayout(getContentPane(), Y_AXIS));
        add(btnAdd);
        add(btnUpdate);
        add(btnView);
        add(btnBack);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initializeFields() {
        btnAdd = new JButton("Add New Client");
        btnUpdate = new JButton("Update Existing Client");
        btnView = new JButton("View Client Informations");
        btnBack = new JButton("Back");
    }

    public void setAddButtonListener(ActionListener addButtonListener) {
        btnAdd.addActionListener(addButtonListener);
    }

    public void setUpdateButtonListener(ActionListener updateButtonListener) {
        btnUpdate.addActionListener(updateButtonListener);
    }

    public void setViewButtonListener(ActionListener viewButtonListener) {
        btnView.addActionListener(viewButtonListener);
    }

    public void setBackButtonListener(ActionListener backButtonListener) {
        btnBack.addActionListener(backButtonListener);
    }
}
