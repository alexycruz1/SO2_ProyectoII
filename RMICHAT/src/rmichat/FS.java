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
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.*;
import java.rmi.server.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
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

    public void createFile(File newFile) {
        try {
            if (newFile.createNewFile()) {
                JOptionPane.showMessageDialog(null, "File created!", "CREATE FILE", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "File already exists.", "CREATE FILE", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException ex) {
            Logger.getLogger(FS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getFileContent(String DirPath) {
        String FileContent = "";

        try {
            for (String line : Files.readAllLines(Paths.get(DirPath), StandardCharsets.UTF_8)) {
                FileContent += line + "\n";
            }
        } catch (IOException ex) {
            Logger.getLogger(FS.class.getName()).log(Level.SEVERE, null, ex);
        }

        return FileContent;
    }
    
    public boolean isFile(String filePath) { 
        final Path path = Paths.get(filePath);
        
        return !Files.isExecutable(path);
    }
}
