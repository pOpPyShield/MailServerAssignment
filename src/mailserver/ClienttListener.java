package mailserver;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClienttListener implements ActionListener {
    private LoginPanelOfClientUI lgPanelClient;

    public void setLgPanelClient(LoginPanelOfClientUI lgPanelClient) {
        this.lgPanelClient = lgPanelClient;
    }


    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() instanceof JButton) {
            switch(ae.getActionCommand()) {
                case "Login":
                    validateLogin();
                    break;
            }
        }
    }

    private void validateLogin(){
        if((lgPanelClient.getUserName().getText() == null || lgPanelClient.getUserName().getText().isEmpty())) {
            JOptionPane.showMessageDialog(lgPanelClient.getRootPane(), "Username failed", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if(lgPanelClient.getPassword().getText() == null || lgPanelClient.getPassword().getText().isEmpty() ){
            JOptionPane.showMessageDialog(lgPanelClient.getRootPane(), "Password failed", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            lgPanelClient.setIsLogin(true);
        }
    }
}
