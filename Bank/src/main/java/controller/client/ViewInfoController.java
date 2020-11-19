package controller.client;

import controller.account.ClientAccountController;
import controller.account.ViewAccountController;
import view.account.ClientAccountView;
import view.account.ViewAccountView;
import view.client.ClientInfoView;
import view.client.ViewInfoView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewInfoController {

    private final ViewInfoView viewInfoView;

    public ViewInfoController(ViewInfoView viewAccountView) throws HeadlessException {
        this.viewInfoView = viewAccountView;
        viewInfoView.setBackButtonListener(new ViewInfoController.BackButtonListener());
    }

    private class BackButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            viewInfoView.dispose();
            new ClientInfoController(new ClientInfoView());

        }
    }
}
