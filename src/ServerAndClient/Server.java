package ServerAndClient;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class Server {
    private static HashMap<Client, Socket> clients = new HashMap<Client, Socket>();
    private ServerSocket srvSocket;
    private JTextArea displayStatusServer;
    private JLabel displayPortServer;
    private JLabel displayIpServer;
    public Server(JTextArea displayStatus, JLabel displayPort, JLabel displayIp) {
        displayStatusServer = displayStatus;
        displayPortServer = displayPort;
        displayIpServer = displayIp;
        runningServer();
    }

    private void runningServer() {
        try {
            srvSocket = new ServerSocket(1234);
        } catch (IOException e) {
            e.printStackTrace();
        }
        displayStatusServer.append("Server started at port: " + srvSocket.getLocalPort() + "\n");
        displayIpServer.setText(srvSocket.getInetAddress().toString());
        displayPortServer.setText(String.valueOf(srvSocket.getLocalPort()));
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
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Client connected");

        }
    }

    private class ClientHandler  {
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

    }
}
