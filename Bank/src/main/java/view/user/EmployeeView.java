package view.user;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static javax.swing.BoxLayout.Y_AXIS;

public class EmployeeView extends JFrame {

    private JButton btnClientInfo;
    private JButton btnClientAccount;

    public EmployeeView() throws HeadlessException{
        setSize(300, 300);
        setLocationRelativeTo(null);
        initializeFields();
        setLayout(new BoxLayout(getContentPane(), Y_AXIS));
        add(btnClientInfo);
        add(btnClientAccount);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initializeFields() {
        btnClientInfo = new JButton("Manage Client Informations");
        btnClientAccount = new JButton("Manage Client Account");
    }

    public void setClientInfoButtonListener(ActionListener clientInfoButtonListener) {
        btnClientInfo.addActionListener(clientInfoButtonListener);
    }

    public void setClientAccountButtonListener(ActionListener clientAccountButtonListener) {
        btnClientAccount.addActionListener(clientAccountButtonListener);
    }
}
