package ServerAndClient;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server extends ServerSocket{
    private ArrayList<Socket> clients = new ArrayList<>();

    public Server() throws IOException {
        super(1234);
        System.out.println("Server started at port: " + getLocalPort());
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Client zz = (Client) accept();
            System.out.println(zz.toString());
            System.out.println("Client connected");

        }
    }

    @Override
    public Socket accept() throws IOException {
        Socket s = new Client();
        implAccept(s);
        return s;
    }

}
