package ServerAndClient;

import java.net.Socket;
import java.util.HashMap;

public class ThreadListenDataClient implements Runnable{
    public boolean hasData;
    public HashMap<Client, Socket> userList;

    public ThreadListenDataClient() {
        this.hasData = false;
        this.userList = new HashMap<>();
    }

    @Override
    public void run() {

    }
}
