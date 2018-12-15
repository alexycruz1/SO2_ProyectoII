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
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class FSServer {

    public static void main(String[] argv) {
        FSServer myChatServer = new FSServer();

        try {
            myChatServer.scanFS();
        } catch (InterruptedException ex) {
            Logger.getLogger(FSServer.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            Scanner s = new Scanner(System.in);
            String name = "SERVER";

            DefaultTreeModel model = (DefaultTreeModel) myChatServer.RMI_FS.getModel();
            FS server = new FS(name, model);

            //System.setProperty("java.rmi.server.hostname", "192.168.0.100");
            Registry registry = LocateRegistry.createRegistry(8888);
            registry.bind("ejemplo", server);
            
            JOptionPane.showMessageDialog(null, "SERVER ONLINE!", "SERVIDOR", JOptionPane.INFORMATION_MESSAGE);

            //String msg = s.nextLine().trim();
            while (true) {
                ArrayList<FS_Interface> clients = server.getClients();
                if (!clients.isEmpty()) {
//               ChatInterface client = server.getClient();
                    //msg = "[" + server.getName() + "] " + msg;
                    for (FS_Interface client : clients) {
                        client.setFS(model);
                    }
                }

                try {
                    myChatServer.scanFS();
                } catch (InterruptedException ex) {
                    Logger.getLogger(FSServer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            /*while (true) {
                String msg = s.nextLine().trim();
                ArrayList<FS_Interface> clients = server.getClients();
                if (!clients.isEmpty()) {
//                    ChatInterface client = server.getClient();
                    msg = "[" + server.getName() + "] " + msg;
                    for (FS_Interface client : clients) {
                        client.send(msg);
                    }

                }
            }*/
        } catch (Exception e) {
            System.out.println("[System] Server failed: " + e);
        }
    }

    String actualDir = "", rootDir = "./Our Local Disk C/";
    JTree RMI_FS = new JTree();

    public void scanFS() throws InterruptedException {
        File currentDir = new File(rootDir);
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("Our Local Disk C");
        DefaultTreeModel treeModel = new DefaultTreeModel(rootNode);
        RMI_FS.setModel(treeModel);

        DefaultTreeModel model = (DefaultTreeModel) RMI_FS.getModel();
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
        displayDirectoryContents(currentDir, root);
    }

    public void displayDirectoryContents(File InitialDir, DefaultMutableTreeNode OriginalRoot) throws InterruptedException {
        DefaultMutableTreeNode newdir = new DefaultMutableTreeNode();

        File[] files = InitialDir.listFiles();

        for (File file : files) {
            if (file == null) {
                System.out.println("NUll directory found ");
                continue;
            }
            if (file.isDirectory()) {
                if (file.listFiles() == null) {
                    continue;
                }

                DefaultTreeModel model = (DefaultTreeModel) RMI_FS.getModel();
                DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();

                newdir = new DefaultMutableTreeNode(file.getName());
                OriginalRoot.add(newdir);
                model.reload();

                displayDirectoryContents(file, newdir);
            } else {
                DefaultTreeModel model = (DefaultTreeModel) RMI_FS.getModel();
                DefaultMutableTreeNode selectednode = OriginalRoot;
                DefaultMutableTreeNode newfile = new DefaultMutableTreeNode(file.getName());

                model.insertNodeInto(newfile, selectednode, selectednode.getChildCount());
                model.reload();
            }
        }
    }
}
