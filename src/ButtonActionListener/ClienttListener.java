/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ButtonActionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import mailserver.LoginPanelOfClientUI;

/**
 *
 * @author huygrogbro
 */
public class ClienttListener implements ActionListener{
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
	
	

