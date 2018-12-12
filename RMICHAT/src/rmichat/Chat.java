/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmichat;

/**
 *
 * @author xioma
 */
import java.rmi.*;
import java.rmi.server.*;
import java.util.ArrayList;

public class Chat extends UnicastRemoteObject implements ChatInterface {

    public String name;
    public ChatInterface client = null;
    public ArrayList<ChatInterface> clients = new ArrayList<ChatInterface>();
    public Chat(String n) throws RemoteException {
        this.name = n;
    }

    public String getName() throws RemoteException {
        return this.name;
    }

    public void addClient(ChatInterface c) {
//        client = c;
        clients.add(c);
    }

    public ArrayList<ChatInterface> getClients() {
        return clients;
    }

    public void send(String s) throws RemoteException {
        System.out.println(s);
    }
}
