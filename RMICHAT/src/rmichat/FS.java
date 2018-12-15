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
import java.io.File;
import java.rmi.*;
import java.rmi.server.*;
import java.util.ArrayList;
import javax.swing.tree.DefaultTreeModel;

public class FS extends UnicastRemoteObject implements FS_Interface {

    public String name;
    public DefaultTreeModel FS;
    public FS_Interface client = null;
    public ArrayList<FS_Interface> clients = new ArrayList<FS_Interface>();
    public FS(String n, DefaultTreeModel PublicFS) throws RemoteException {
        this.name = n;
        this.FS = PublicFS;
    }
    
    public void setFS(DefaultTreeModel FSModel) {
        this.FS = FSModel;
    }

    public String getName() throws RemoteException {
        return this.name;
    }

    public void addClient(FS_Interface c) {
        clients.add(c);
    }

    public ArrayList<FS_Interface> getClients() {
        return clients;
    }

    public void send(String s) throws RemoteException {
        System.out.println(s);
    }
    
    public DefaultTreeModel getFSModel() {
        return this.FS;
    }
    
    public void createDirectory(String Address, String DirectoryName) {
        new File(Address + DirectoryName).mkdirs();
    }
}
