/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mailserver;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author huygrogbro
 */
public class HomePanelOfClientUI extends JPanel{
	public static final String titleOfLoginPanel = "Home";
	
	public HomePanelOfClientUI(){
		add(new JLabel("Alababa trap"));
		add(new JButton("Logout"));
	}
	
}
