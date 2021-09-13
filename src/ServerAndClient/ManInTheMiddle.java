package ServerAndClient;

import java.io.*;
import java.net.Socket;

public class ManInTheMiddle{
    private final Socket clientSocket;
    private final Client client;

    public ManInTheMiddle(Socket clientSocket, Client client) {
        this.clientSocket = clientSocket;
        this.client = client;
    }

    public void sendToServer() {
        try {
            OutputStream outputStream = clientSocket.getOutputStream();
            ObjectOutputStream objOutputStream = new ObjectOutputStream(outputStream);
            System.out.println(client);
            objOutputStream.writeObject(this.client);
            objOutputStream.flush();
            objOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
