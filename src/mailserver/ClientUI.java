/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mailserver;

import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author huygrogbro
 */
public class ClientUI extends JFrame{
	String titleOfClientUI;
	public String getTitleOfClientUI() {
		return titleOfClientUI;
	}

	public void updateUI(String titleOfClientUI, Container container, int width, int height) {
		this.setTitle(titleOfClientUI);
		this.setSize(width, height);
		JPanel contentPane = (JPanel) this.getContentPane();
		contentPane.removeAll();
		contentPane.add(container);
		contentPane.revalidate();
		contentPane.repaint();
	}
	public ClientUI(){
		setTitle(LoginPanelOfClientUI.titleOfLoginPanel);
		setContentPane(new LoginPanelOfClientUI(this));
		setSize(700, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}


}
