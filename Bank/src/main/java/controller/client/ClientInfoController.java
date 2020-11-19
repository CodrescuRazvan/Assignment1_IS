package controller.client;

import controller.user.EmployeeController;
import factory.ComponentFactory;
import view.client.AddInfoView;
import view.client.ClientInfoView;
import view.client.ViewInfoView;
import view.user.EmployeeView;
import view.client.UpdateInfoView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientInfoController {

    private final ClientInfoView clientInfoView;

    public ClientInfoController(ClientInfoView clientInfoView){
        this.clientInfoView = clientInfoView;
        clientInfoView.setBackButtonListener(new ClientInfoController.BackButtonListener());
        clientInfoView.setAddButtonListener(new ClientInfoController.AddButtonListener());
        clientInfoView.setUpdateButtonListener(new ClientInfoController.UpdateButtonListener());
        clientInfoView.setViewButtonListener(new ClientInfoController.ViewButtonListener());
    }

    private class AddButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            clientInfoView.dispose();
            new AddInfoController(new AddInfoView(), ComponentFactory.instance(false));

        }
    }

    private class BackButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            clientInfoView.dispose();
            new EmployeeController(new EmployeeView());

        }
    }

    private class UpdateButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            clientInfoView.dispose();
            new UpdateInfoController(new UpdateInfoView(), ComponentFactory.instance(false));
        }
    }

    private class ViewButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            clientInfoView.dispose();
            new ViewInfoController(new ViewInfoView(ComponentFactory.instance(false)));
        }
    }
}
