package controller.account;

import view.account.ClientAccountView;
import view.account.ViewAccountView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewAccountController {

    private final ViewAccountView viewAccountView;

    public ViewAccountController(ViewAccountView viewAccountView) throws HeadlessException {
        this.viewAccountView = viewAccountView;
        viewAccountView.setBackButtonListener(new ViewAccountController.BackButtonListener());
    }

    private class BackButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            viewAccountView.dispose();
            new ClientAccountController(new ClientAccountView());

        }
    }
}
