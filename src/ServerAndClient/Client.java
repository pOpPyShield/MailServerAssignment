/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerAndClient;

import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author huygrogbro
 */
public class Client extends Socket{
    private String nameUser;

    public Client(String nameUserArg) throws IOException {
        super("localhost",1234);
        nameUser = nameUserArg;
    }

    public Client() {
    }
    @Override
    public String toString() {
        return nameUser;
    }
}
