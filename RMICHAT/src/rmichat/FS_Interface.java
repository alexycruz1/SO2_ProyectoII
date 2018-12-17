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
import java.nio.file.Path;
import java.rmi.*;
import java.util.ArrayList;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;

public interface FS_Interface extends Remote {

    public String getName() throws RemoteException;

    public void send(String information) throws RemoteException;

    public void addClient(FS_Interface c) throws RemoteException;

    public ArrayList<FS_Interface> getClients() throws RemoteException;
    
    public DefaultTreeModel getFSModel() throws RemoteException;
    
    public void setFS(DefaultTreeModel FSModel) throws RemoteException;
    
    public void createDirectory(String Address, String DirectoryName) throws RemoteException;
    
    public void createFile(File newFile) throws RemoteException;
    
    public String getFileContent(String DirPath) throws RemoteException;
    
    public boolean isFile(String filePath) throws RemoteException;
    
    public void saveContent(String filePath, String fileContent) throws RemoteException;
    
    public void changeInitFile(String clientName, String initFilePath) throws RemoteException;
}
