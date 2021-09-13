/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerAndClient;

import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;

/**
 *
 * @author huygrogbro
 */
public class Client implements Serializable {
    private String nameUser;

    public Client(String nameUserArg){
        nameUser = nameUserArg;
    }

    public Client() {}
    @Override
    public String toString() {
        return nameUser;
    }
}
