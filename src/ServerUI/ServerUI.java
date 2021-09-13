package ServerUI;

import ServerAndClient.Server;
import mailserver.LoginPanelOfClientUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ServerUI extends JFrame {
    private JTextArea jt;
    private JLabel displayIp;
    private JLabel displayPort;
    private JTree treeUser;
    private DefaultMutableTreeNode root;
    private Server serverSocket;
    private DefaultTreeModel model;
    private void initializeJTree() {
        //Working with root node
        root = new DefaultMutableTreeNode("User");
        treeUser = new JTree(root);
        model = (DefaultTreeModel) treeUser.getModel();
    }

    private void initializeSocketForServer() {
            serverSocket = new Server(jt, displayPort, displayIp, root, model);
            serverSocket.runningServer();
    }

    public ServerUI() {
        setTitle("Server");
        JPanel contentPanelOfServer = new JPanel();
        contentPanelOfServer.setLayout(new BorderLayout());
            JPanel leftPanel = new JPanel();
            leftPanel.setBorder(new EmptyBorder(10,10,10,10));
            leftPanel.setLayout(new BorderLayout());
                JPanel informationPanel = new JPanel();
                informationPanel.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
                informationPanel.setLayout(new BorderLayout());

                    JPanel containIp = new JPanel();
                    containIp.setLayout(new BorderLayout());
                    containIp.add(new JLabel("IP: ", SwingConstants.LEFT), BorderLayout.LINE_START);
                    containIp.add(displayIp = new JLabel("", SwingConstants.LEFT), BorderLayout.CENTER);
                    informationPanel.add(containIp, BorderLayout.PAGE_START);

                    JPanel containPort = new JPanel();
                    containPort.setLayout(new BorderLayout());
                    containPort.add(new JLabel("Port: ", SwingConstants.LEFT), BorderLayout.LINE_START);
                    containPort.add(displayPort = new JLabel("", SwingConstants.LEFT), BorderLayout.CENTER);
                    informationPanel.add(containPort, BorderLayout.CENTER);

                leftPanel.add(informationPanel, BorderLayout.PAGE_START);

                JPanel activityPanel = new JPanel();
                activityPanel.setLayout(new BorderLayout());
                jt = new JTextArea(10,20);
                jt.setEditable(false);
                JScrollPane scActivity = new JScrollPane(jt);
                activityPanel.add(scActivity);
                leftPanel.add(activityPanel, BorderLayout.CENTER);
            contentPanelOfServer.add(leftPanel, BorderLayout.CENTER);

            JPanel rightPanel = new JPanel();
            rightPanel.setBorder(BorderFactory.createEmptyBorder(10,0,10,10));
            rightPanel.setLayout(new BorderLayout());
            initializeJTree();
            JScrollPane scTree = new JScrollPane(treeUser);
            rightPanel.add(scTree);
            contentPanelOfServer.add(rightPanel, BorderLayout.LINE_END);
        setContentPane(contentPanelOfServer);
        setSize(600, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        initializeSocketForServer();
    }
}
