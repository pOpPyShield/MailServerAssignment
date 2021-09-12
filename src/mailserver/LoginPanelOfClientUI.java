/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mailserver;

import ButtonActionListener.ClienttListener;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author huygrogbro
 */
public class LoginPanelOfClientUI extends JPanel{
	public static final String titleOfLoginPanel = "Login";
	private Font fontUsr;
	private JTextField userName;
	private JPasswordField password;
	private JButton btnLogin;
	private ClienttListener clientListener;
	private boolean isLogin;
	private ClientUI clientUI;

	public boolean getIsLogin() {
		return isLogin;
	}

	public void setIsLogin(boolean isLogin) {
		this.isLogin = isLogin;
		System.out.println(isLogin);
		if(isLogin) {
			clientUI.updateUI(HomePanelOfClientUI.titleOfLoginPanel, new HomePanelOfClientUI(this), 900,600);
		}
	}

	public JPasswordField getPassword() {
		return password;
	}
	
	public JTextField getUserName() {
		return userName;
	}

	public Font getFontUsr() {
		return fontUsr;
	}

	public void setFontUsr(Font fontUsr) {
		this.fontUsr = fontUsr;
	}
	

	public LoginPanelOfClientUI(ClientUI gg) {
		clientUI = gg;
		setLayout(new BorderLayout(10,0));
		
		//JPanel top
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(2,0));
		//+ UserName section
		JPanel containUserName = new JPanel();
		containUserName.setBorder(BorderFactory.createEmptyBorder(0,0,0,10));
		containUserName.setLayout(new BorderLayout());
			JPanel containLabel = new JPanel();
			containLabel.setLayout(new GridLayout(3, 0));
			containLabel.add(new JPanel());
			containLabel.add(new JLabel("Username: ",SwingConstants.CENTER));
			containLabel.add(new JPanel());
			containUserName.add(containLabel, BorderLayout.LINE_START);

			JPanel containUsrNameTf = new JPanel();
			containUsrNameTf.setLayout(new GridLayout(3,0));
			containUsrNameTf.add(new JLabel());
			//Font 
			fontUsr = new Font("SansSerif", Font.BOLD, 20);
			containUsrNameTf.add(userName = new JTextField(20));
			userName.setFont(fontUsr);
			containUsrNameTf.add(new JLabel());
			containUserName.add(containUsrNameTf, BorderLayout.CENTER);
		topPanel.add(containUserName);

		//+ Password section
		JPanel containPassword = new JPanel();
		containPassword.setBorder(BorderFactory.createEmptyBorder(0,0,0,10));
		containPassword.setLayout(new BorderLayout());
			JPanel containLabelPwd = new JPanel();
			containLabelPwd.setLayout(new GridLayout(3,0));
			containLabelPwd.add(new JLabel("Password: ", SwingConstants.CENTER));
			containLabelPwd.add(new JLabel());
			containLabelPwd.add(new JLabel());
			containPassword.add(containLabelPwd, BorderLayout.LINE_START);

			JPanel containPasswordTf = new JPanel();
			containPasswordTf.setLayout(new GridLayout(3,0));
			containPasswordTf.add(password = new JPasswordField(20));
			//+ Button section
			JPanel containButton = new JPanel();
			containButton.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
			containButton.setLayout(new GridLayout(0,3));
				containButton.add(new JLabel());
				containButton.add(btnLogin = new JButton("Login"));
				btnLogin.setActionCommand("Login");
				btnLogin.addActionListener(clientListener = new ClienttListener());
				clientListener.setLgPanelClient(this);
				containButton.add(new JLabel());
			containPasswordTf.add(containButton);
			containPasswordTf.add(new JLabel());
			containPassword.add(containPasswordTf, BorderLayout.CENTER);
		topPanel.add(containPassword);
		this.add(topPanel, BorderLayout.CENTER);
	}
}
