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
import java.util.ArrayList;

public interface ChatInterface extends Remote {

    public String getName() throws RemoteException;

    public void send(String msg) throws RemoteException;

    public void addClient(ChatInterface c) throws RemoteException;

    public ArrayList<ChatInterface> getClients() throws RemoteException;
}
