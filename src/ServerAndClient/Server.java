package ServerAndClient;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Server {
    private static HashMap<Client, Socket> clients = new HashMap<Client, Socket>();
    private ServerSocket srvSocket;
    private JTextArea displayStatusServer;
    private JLabel displayPortServer;
    private JLabel displayIpServer;
    public boolean hasNewUser;
    //For display
    private DefaultMutableTreeNode root;
    private DefaultTreeModel model;
    public Server(JTextArea displayStatus, JLabel displayPort, JLabel displayIp, DefaultMutableTreeNode treeArg, DefaultTreeModel treeModel) {
        displayStatusServer = displayStatus;
        displayPortServer = displayPort;
        displayIpServer = displayIp;
        root = treeArg;
        model = treeModel;
    }

    public void runningServer() {
        try {
            srvSocket = new ServerSocket(1234);
        } catch (IOException e) {
            e.printStackTrace();
        }
        displayStatusServer.append("Server started at port: " + srvSocket.getLocalPort() + "\n");
        displayIpServer.setText(srvSocket.getInetAddress().toString());
        displayPortServer.setText(String.valueOf(srvSocket.getLocalPort()));
        initializeDirForServer();
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                Socket s = srvSocket.accept();
                ClientHandler clientSock = new ClientHandler(s);
                clientSock.getClientSend();
                System.out.println("Accept user " + clients.keySet());

                System.out.println("Socket: " + clients.values());
                addToTree(clientSock.getClient());
                hasNewUser = true;
                new Thread(clientSock).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Client connected");

        }
    }
    private void writeToFile(String pathToFile) {
        try {
            FileWriter myWriter = new FileWriter(pathToFile);
            myWriter.write("Thank you for using this service. we hope that you will feel comfortable........");
            myWriter.close();
            System.out.println("Successfully wrote to the file");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void addToTree(Client nameUSer) {
        DefaultMutableTreeNode userDir = new DefaultMutableTreeNode(nameUSer);
        File theDir = new File("/home/huygrogbro/MailServer/" + nameUSer);
        if(!theDir.exists()) {
            theDir.mkdir();
            String welcomFilePath = "/home/huygrogbro/MailServer/" + nameUSer + "/new_email.txt";
            FileCustom welcomFile = new FileCustom(welcomFilePath);
            try {
                if(welcomFile.createNewFile()) {
                    writeToFile(welcomFilePath);
                    displayStatusServer.append("File created for: " + nameUSer + "\n");
                } else {
                    displayStatusServer.append("Create failed for: " + nameUSer + ", because file already exist\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            addDirAndFile(userDir, new DefaultMutableTreeNode(welcomFile));
        } else {
            System.out.println("Dir client has exist");
        }

    }
    private void addDirAndFile(DefaultMutableTreeNode nodeParent, DefaultMutableTreeNode nodeChild) {
        nodeParent.add(nodeChild);
        root.add(nodeParent);
        model.reload(root);
    }
    private void initializeDirForServer() {
        File theDir = new File("/home/huygrogbro/MailServer");
        if(!theDir.exists()) {
            theDir.mkdir();
            displayStatusServer.append("Create dir for server success.\n");
        } else {
            displayStatusServer.append("Dir server has exist. \n");
        }
    }

    private void sendHashMapOverSocket(Socket client) {
        try {
            ObjectOutputStream objOutputStream = new ObjectOutputStream(client.getOutputStream());
            System.out.println(client);
            objOutputStream.writeObject(clients);
            objOutputStream.flush();
            objOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void sendToClient() {
        int  count = 0;
        for(Map.Entry<Client, Socket> set : clients.entrySet()) {
            System.out.println(count + "." + set.getKey() + " = " + set.getValue());
            sendHashMapOverSocket(set.getValue());
        }
    }
    private class ClientHandler  implements Runnable{
        private final Socket clientSocket;
        private Client client;
        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        public void getClientSend() {
            try {
                InputStream ipStream = clientSocket.getInputStream();
                ObjectInputStream objIpStream = new ObjectInputStream(ipStream);
                try {
                    this.client = (Client) objIpStream.readObject();
                    clients.put(this.client, this.clientSocket);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                objIpStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public Client getClient() {
            return client;
        }

        @Override
        public void run() {
            boolean ok = true;
            while (ok) {
                if(hasNewUser) {
                    //sendToClient();
                    notifyStatusChanged();
                    hasNewUser = false;
                    ok = false;
                }
            }
        }
    }

    public void notifyStatusChanged() {
        System.out.println("notifyStatusChange -- ");
    }
}
