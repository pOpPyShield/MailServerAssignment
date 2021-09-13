/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mailserver;

import ServerAndClient.Client;
import ServerAndClient.ManInTheMiddle;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.Socket;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author huygrogbro
 */
public class HomePanelOfClientUI extends JPanel{
	public static final String titleOfLoginPanel = "Home";
	private JButton btnLogout;
	private JButton btnSend;
	private LoginPanelOfClientUI lgPanelOfClientUI;
	private JLabel nameOfFriend;
	private JLabel typeOf;
	private JTextArea jt;
	private JTextArea tf;
	private JList list;
	private JList list2;
	private Client clientCreate;
	private void initializeSocket() {
		Socket socketClient = null;
		try {
			socketClient = new Socket("localhost", 1234);
		} catch (IOException e) {
			e.printStackTrace();
		}
		clientCreate = new Client(lgPanelOfClientUI.getUserName().getText());
		ManInTheMiddle testSend = new ManInTheMiddle(socketClient, clientCreate);
		testSend.sendToServer();
		System.out.println("Create success in home user panel, client:  " + clientCreate);
	}
	public HomePanelOfClientUI(LoginPanelOfClientUI lgPanel){
		lgPanelOfClientUI = lgPanel;
		initializeSocket();
		setLayout(new BorderLayout());
		JPanel topPanel = new JPanel();
		topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		topPanel.setLayout(new GridLayout(0, 3));
		topPanel.add(new JLabel(lgPanelOfClientUI.getUserName().getText(), SwingConstants.CENTER));
		topPanel.add(new JLabel());
		topPanel.add(btnLogout = new JButton("Logout"));
		add(topPanel, BorderLayout.PAGE_START);

		JPanel panelCenter = new JPanel();
		panelCenter.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		panelCenter.setLayout(new BorderLayout());
			JPanel panelLeft = new JPanel();
			panelLeft.setLayout(new BorderLayout());
				JPanel containSendto = new JPanel();
				containSendto.setLayout(new BorderLayout());
				containSendto.add(typeOf = new JLabel("Send to: ", SwingConstants.LEFT), BorderLayout.LINE_START);
				containSendto.add(nameOfFriend = new JLabel("Don't have", SwingConstants.LEFT), BorderLayout.CENTER);
				panelLeft.add(containSendto, BorderLayout.PAGE_START);

				JPanel containJtextarea = new JPanel();
				containJtextarea.setLayout(new BorderLayout());
				jt = new JTextArea();
				jt.setEditable(false);
				JScrollPane scReadEmail = new JScrollPane(jt);
				containJtextarea.add(scReadEmail, BorderLayout.CENTER);
				panelLeft.add(containJtextarea, BorderLayout.CENTER);

				JPanel containBtnAndTf = new JPanel();
				containBtnAndTf.setBorder(BorderFactory.createEmptyBorder(10,0,0,0));
				containBtnAndTf.setLayout(new BorderLayout());
				tf = new JTextArea(4,20);
				JScrollPane scInput = new JScrollPane(tf);
				containBtnAndTf.add(scInput, BorderLayout.CENTER);
				btnSend = new JButton("Send email");
				containBtnAndTf.add(btnSend, BorderLayout.LINE_END);
				panelLeft.add(containBtnAndTf, BorderLayout.PAGE_END);

			panelCenter.add(panelLeft, BorderLayout.CENTER);

			JPanel panelRight = new JPanel();
			panelRight.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 0));
			panelRight.setLayout(new GridLayout(2, 0));

				String categories[] = {"Household", "Office", "Extended Family", "Company (US)", "Company (World)", "Team", "Will", "Birthday Card List", "High School", "Country", "Continent", "Planet"};
				list = new JList(categories);

				String categories2[] = {"Household", "Office", "Extended Family", "Company (US)", "Company (World)", "Team", "Will", "Birthday Card List", "High School", "Country", "Continent", "Planet"};
				list2 = new JList(categories2);
				JPanel panelFriend = new JPanel();
				panelFriend.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
				panelFriend.setLayout(new BorderLayout(10,0));
				panelFriend.add(new JLabel("Friend: ", SwingConstants.LEFT), BorderLayout.PAGE_START);
				list.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						if(e.getClickCount() == 2) {
							String selectedItem = (String) list.getSelectedValue();
							typeOf.setText("Send to: ");
							list2.clearSelection();
							nameOfFriend.setText(selectedItem);
						}
					}
				});
				JScrollPane scFriend = new JScrollPane(list);
				panelFriend.add(scFriend, BorderLayout.CENTER);
				panelRight.add(panelFriend);

				JPanel panelEmail = new JPanel();
				panelEmail.setLayout(new BorderLayout(10, 0));
				panelEmail.add(new JLabel("Email: ", SwingConstants.LEFT), BorderLayout.PAGE_START);
				list2.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						if(e.getClickCount() == 2) {
							String selectedItem = (String) list2.getSelectedValue();
							typeOf.setText("Email: ");
							list.clearSelection();
							nameOfFriend.setText(selectedItem);
						}
					}
				});
				JScrollPane scEmail = new JScrollPane(list2);
				panelEmail.add(scEmail, BorderLayout.CENTER);
				panelRight.add(panelEmail);

			panelCenter.add(panelRight, BorderLayout.LINE_END);
		
		add(panelCenter, BorderLayout.CENTER);
	}
}
