/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmichat;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author alexyc
 */
public class FSClient extends javax.swing.JFrame {

    /**
     * Creates new form FS
     */
    public FSClient() {
        initComponents();

        try {
            name = JOptionPane.showInputDialog(this, "Enter client name", "INICIANDO CLIENTE", JOptionPane.INFORMATION_MESSAGE);

            client = new FS(name, null, null);
            registry = LocateRegistry.getRegistry("192.168.0.102", 8888);
            //registry = LocateRegistry.getRegistry(8888);
            server = (FS_Interface) registry.lookup("ejemplo");

            String msg = "[" + client.getName() + "] conectado!";
            server.send(msg);
            server.addClient(client);

            RMI_FS.setModel(server.getFSModel());

            /*ArrayList<FS_Interface> clients = server.getClients();
            for (int i = 0; i < clients.size(); i++) {
                clients.get(i).setFS(server.getFSModel());
                clients.get(i).getFSModel().reload();
            }*/
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Server failed!", "INICIANDO CLIENTE", JOptionPane.ERROR_MESSAGE);
            JOptionPane.showMessageDialog(this, e, "INICIANDO CLIENTE", JOptionPane.ERROR_MESSAGE);
        }

        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        FS_Options = new javax.swing.JPopupMenu();
        FS_OpenFile = new javax.swing.JMenuItem();
        FS_CreateFile = new javax.swing.JMenuItem();
        FS_CreateDirectory = new javax.swing.JMenuItem();
        jd_File = new javax.swing.JDialog();
        jScrollPane2 = new javax.swing.JScrollPane();
        jta_FileContent = new javax.swing.JTextArea();
        jmb_FileContentOperations = new javax.swing.JMenuBar();
        Menu_FileContent = new javax.swing.JMenu();
        FileContent_Save = new javax.swing.JMenuItem();
        FileContent_Close = new javax.swing.JMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        RMI_FS = new javax.swing.JTree();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        FS_OpenFile.setText("Open File");
        FS_OpenFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FS_OpenFileActionPerformed(evt);
            }
        });
        FS_Options.add(FS_OpenFile);

        FS_CreateFile.setText("Create File");
        FS_CreateFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FS_CreateFileActionPerformed(evt);
            }
        });
        FS_Options.add(FS_CreateFile);

        FS_CreateDirectory.setText("Create Directory");
        FS_CreateDirectory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FS_CreateDirectoryActionPerformed(evt);
            }
        });
        FS_Options.add(FS_CreateDirectory);

        jta_FileContent.setColumns(20);
        jta_FileContent.setRows(5);
        jScrollPane2.setViewportView(jta_FileContent);

        Menu_FileContent.setText("File");

        FileContent_Save.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        FileContent_Save.setText("Save");
        FileContent_Save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FileContent_SaveActionPerformed(evt);
            }
        });
        Menu_FileContent.add(FileContent_Save);

        FileContent_Close.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        FileContent_Close.setText("Close");
        FileContent_Close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FileContent_CloseActionPerformed(evt);
            }
        });
        Menu_FileContent.add(FileContent_Close);

        jmb_FileContentOperations.add(Menu_FileContent);

        jd_File.setJMenuBar(jmb_FileContentOperations);

        javax.swing.GroupLayout jd_FileLayout = new javax.swing.GroupLayout(jd_File.getContentPane());
        jd_File.getContentPane().setLayout(jd_FileLayout);
        jd_FileLayout.setHorizontalGroup(
            jd_FileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 779, Short.MAX_VALUE)
        );
        jd_FileLayout.setVerticalGroup(
            jd_FileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("Our Local Disk C");
        RMI_FS.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        RMI_FS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RMI_FSMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(RMI_FS);

        jButton1.setText("Refresh");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        jButton2.setText("Desmontar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(563, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void RMI_FSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RMI_FSMouseClicked
        // TODO add your handling code here:
        if (evt.isMetaDown()) {
            int Row = RMI_FS.getClosestRowForLocation(evt.getX(), evt.getY());
            RMI_FS.setSelectionRow(Row);

            Object Selected = RMI_FS.getSelectionPath().getLastPathComponent();
            nodo_seleccionado = (DefaultMutableTreeNode) Selected;

            FS_Options.show(evt.getComponent(), evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_RMI_FSMouseClicked

    private void FS_CreateFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FS_CreateFileActionPerformed
        // TODO add your handling code here:
        String FileName = JOptionPane.showInputDialog(this, "Enter file name and extension (e.g. myFile.txt)", "CREATE FILE", JOptionPane.INFORMATION_MESSAGE);

        if (nodo_seleccionado.isRoot()) {
            File file = new File(rootDir + FileName);

            if (!"".equals(FileName)) {
                try {
                    if(!close){
                        server.createFile(file);
                        RMI_FS.setModel(server.getFSModel());
                        /*ArrayList<FS_Interface> clients = server.getClients();
                        for (int i = 0; i < clients.size(); i++) {
                            clients.get(i).setFS(server.getFSModel());
                            clients.get(i).getFSModel().reload();
                        }*/
                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(FSClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(this, "File name required. ", "CREATE FILE", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            String DirPath = "./";
            Object[] paths = RMI_FS.getSelectionPath().getPath();

            for (int i = 0; i < paths.length; i++) {
                DirPath += paths[i];
                if (i + 1 < paths.length) {
                    DirPath += File.separator;
                }
            }

            DirPath += "/";

            File file = new File(DirPath + FileName);

            if (!"".equals(FileName)) {
                try {
                    if(!close){
                        server.createFile(file);
                        RMI_FS.setModel(server.getFSModel());
                        /*ArrayList<FS_Interface> clients = server.getClients();
                        for (int i = 0; i < clients.size(); i++) {
                            clients.get(i).setFS(server.getFSModel());
                            clients.get(i).getFSModel().reload();
                        }*/
                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(FSClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(this, "File name required. ", "CREATE FILE", JOptionPane.WARNING_MESSAGE);
            }
        }

        try {
            this.scanFS();
        } catch (InterruptedException ex) {
            Logger.getLogger(FSClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_FS_CreateFileActionPerformed

    private void FS_CreateDirectoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FS_CreateDirectoryActionPerformed
        // TODO add your handling code here:
        String DirectoryName = JOptionPane.showInputDialog(this, "Enter directory name", "CREATE DIRECTORY", JOptionPane.INFORMATION_MESSAGE);

        if (nodo_seleccionado.isRoot()) {
            File file = new File(rootDir + DirectoryName);

            if (!file.exists() && !"".equals(DirectoryName)) {
                try {
                    if(!close){
                        //new File(rootDir + DirectoryName).mkdirs();
                        server.createDirectory(rootDir, DirectoryName);
                        RMI_FS.setModel(server.getFSModel());
                        /*ArrayList<FS_Interface> clients = server.getClients();
                        for (int i = 0; i < clients.size(); i++) {
                            clients.get(i).setFS(server.getFSModel());
                            clients.get(i).getFSModel().reload();
                        }*/
                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(FSClient.class.getName()).log(Level.SEVERE, null, ex);
                }

                JOptionPane.showMessageDialog(this, "Directory Created! ", "CREATE DIRECTORY", JOptionPane.INFORMATION_MESSAGE);
            } else {
                if (DirectoryName.equals("")) {
                    JOptionPane.showMessageDialog(this, "Directory name required. ", "CREATE DIRECTORY", JOptionPane.WARNING_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Directory already exists. ", "CREATE DIRECTORY", JOptionPane.ERROR_MESSAGE);
                }
            }

        } else {
            String DirPath = "./";
            Object[] paths = RMI_FS.getSelectionPath().getPath();

            for (int i = 0; i < paths.length; i++) {
                DirPath += paths[i];
                if (i + 1 < paths.length) {
                    DirPath += File.separator;
                }
            }

            DirPath += "/";

            File file = new File(DirPath + DirectoryName);

            if (!file.exists() && !"".equals(DirectoryName)) {
                try {
                    if(!close){
                        server.createDirectory(DirPath, DirectoryName);
                        RMI_FS.setModel(server.getFSModel());
                        /*ArrayList<FS_Interface> clients = server.getClients();
                        for (int i = 0; i < clients.size(); i++) {
                            clients.get(i).setFS(server.getFSModel());
                            clients.get(i).getFSModel().reload();
                        }*/
                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(FSClient.class.getName()).log(Level.SEVERE, null, ex);
                }

                JOptionPane.showMessageDialog(this, "Directory Created! ", "CREATE DIRECTORY", JOptionPane.INFORMATION_MESSAGE);
            } else {
                if (DirectoryName.equals("")) {
                    JOptionPane.showMessageDialog(this, "Directory name required. ", "CREATE DIRECTORY", JOptionPane.WARNING_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Directory already exists. ", "CREATE DIRECTORY", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_FS_CreateDirectoryActionPerformed

    private void FS_OpenFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FS_OpenFileActionPerformed
        // TODO add your handling code here:
        String DirPath = "./";
        Object[] paths = RMI_FS.getSelectionPath().getPath();

        for (int i = 0; i < paths.length; i++) {
            DirPath += paths[i];
            if (i + 1 < paths.length) {
                DirPath += File.separator;
            }
        }

        final Path path = Paths.get(DirPath);
        actualDir = DirPath;
        DirPathInServer = DirPath;

        try {
            if(true){
                if (true) {
                    String FileContent = "";

                    File cacheDirectory = new File(cacheDir);
                    if (!cacheDirectory.exists()) {
                        new File(cacheDir).mkdirs();

                        JOptionPane.showMessageDialog(null, "Cache directory created!", "DIRECTORY", JOptionPane.INFORMATION_MESSAGE);

                        File newFile = new File(cacheDir + paths[paths.length - 1].toString());
                        try {
                            BufferedWriter writer = null;
                            if (newFile.createNewFile()) {;
                                writer = new BufferedWriter(new FileWriter(newFile, false));
                                writer.write(server.getFileContent(DirPath));
                                writer.close();

                                JOptionPane.showMessageDialog(null, "File created in cache!", "CREATE FILE", JOptionPane.INFORMATION_MESSAGE);
                                openedFile = cacheDir + paths[paths.length - 1].toString();
                            } else {
                                JOptionPane.showMessageDialog(null, "File already exists in cache", "CREATE FILE", JOptionPane.ERROR_MESSAGE);
                            }

                            File iniFile = new File(cacheDir + paths[paths.length - 1].toString() + ".bin");
                            if (iniFile.createNewFile()) {
                                writer = new BufferedWriter(new FileWriter(iniFile, false));
                                writer.write("0");
                                writer.close();

                                JOptionPane.showMessageDialog(null, "Bit file created in cache!", "CREATE FILE", JOptionPane.INFORMATION_MESSAGE);
                                initFile = cacheDir + paths[paths.length - 1].toString() + ".bin";
                            } else {
                                JOptionPane.showMessageDialog(null, "Bit file already exists in cache", "CREATE FILE", JOptionPane.ERROR_MESSAGE);

                                FileContent = "";
                                for (String line : Files.readAllLines(Paths.get(initFile), StandardCharsets.UTF_8)) {
                                    FileContent += line + "\n";
                                }
                                
                                System.out.println(FileContent);

                                if (FileContent.contains("1")) {
                                    writer = new BufferedWriter(new FileWriter(newFile, false));
                                    writer.write(server.getFileContent(DirPath));
                                    writer.close();

                                    writer = new BufferedWriter(new FileWriter(iniFile, false));
                                    writer.write("0");
                                    writer.close();
                                }
                            }
                        } catch (IOException ex) {
                            Logger.getLogger(FSClient.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        File newFile = new File(cacheDir + paths[paths.length - 1].toString());

                        try {
                            BufferedWriter writer = null;
                            if (newFile.createNewFile()) {
                                writer = new BufferedWriter(new FileWriter(newFile, false));
                                writer.write(server.getFileContent(DirPath));
                                writer.close();

                                JOptionPane.showMessageDialog(null, "File created in cache!", "CREATE FILE", JOptionPane.INFORMATION_MESSAGE);
                                openedFile = cacheDir + paths[paths.length - 1].toString();
                            } else {
                                JOptionPane.showMessageDialog(null, "File already exists in cache", "CREATE FILE", JOptionPane.ERROR_MESSAGE);
                            }

                            File iniFile = new File(cacheDir + paths[paths.length - 1].toString() + ".bin");
                            if (iniFile.createNewFile()) {
                                writer = new BufferedWriter(new FileWriter(iniFile, false));
                                writer.write(0);
                                writer.close();

                                JOptionPane.showMessageDialog(null, "Bit file created in cache!", "CREATE FILE", JOptionPane.INFORMATION_MESSAGE);
                                initFile = cacheDir + paths[paths.length - 1].toString() + ".bin";
                            } else {
                                JOptionPane.showMessageDialog(null, "Bit file already exists in cache", "CREATE FILE", JOptionPane.ERROR_MESSAGE);

                                FileContent = "";
                                for (String line : Files.readAllLines(Paths.get(initFile), StandardCharsets.UTF_8)) {
                                    FileContent += line + "\n";
                                }
                                
                                System.out.println(FileContent);

                                if (FileContent.contains("1")) {
                                    writer = new BufferedWriter(new FileWriter(newFile, false));
                                    writer.write(server.getFileContent(DirPath));
                                    writer.close();

                                    writer = new BufferedWriter(new FileWriter(iniFile, false));
                                    writer.write("0");
                                    writer.close();
                                }
                            }
                        } catch (IOException ex) {
                            Logger.getLogger(FSClient.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                    jta_FileContent.setText(server.getFileContent(DirPath));
                    openedFileContent = server.getFileContent(DirPath);

                    jd_File.setModal(true);
                    jd_File.pack();
                    jd_File.setLocationRelativeTo(this);
                    jd_File.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "It's a Directory! ", "OPEN FILE", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (RemoteException ex) {
            Logger.getLogger(FSClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_FS_OpenFileActionPerformed

    private void FileContent_SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FileContent_SaveActionPerformed
        try {
            if(!close){
                server.changeInitFile(client.getName(), initFile);
                System.out.println(actualDir);
                server.saveContent(actualDir, jta_FileContent.getText());
            }
        } catch (RemoteException ex) {
            Logger.getLogger(FSClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_FileContent_SaveActionPerformed

    private void FileContent_CloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FileContent_CloseActionPerformed
        // TODO add your handling code here:
        jd_File.dispose();
        jta_FileContent.setText("");
    }//GEN-LAST:event_FileContent_CloseActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        try {
            // TODO add your handling code here:
            if(!close){
                RMI_FS.setModel(server.getFSModel());
            }
        } catch (RemoteException ex) {
            Logger.getLogger(FSClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       try {
            // TODO add your handling code here:
            if(!close){
                server.disconnectClient(name);
                client.disconnectClient(name);
                close = true;
            }
        } catch (RemoteException ex) {
            Logger.getLogger(FSClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

/**
 * @param args the command line arguments
 */
public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                

}
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FSClient.class
.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        

} catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FSClient.class
.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        

} catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FSClient.class
.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        

} catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FSClient.class
.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FSClient().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem FS_CreateDirectory;
    private javax.swing.JMenuItem FS_CreateFile;
    private javax.swing.JMenuItem FS_OpenFile;
    private javax.swing.JPopupMenu FS_Options;
    private javax.swing.JMenuItem FileContent_Close;
    private javax.swing.JMenuItem FileContent_Save;
    private javax.swing.JMenu Menu_FileContent;
    private javax.swing.JTree RMI_FS;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JDialog jd_File;
    private javax.swing.JMenuBar jmb_FileContentOperations;
    private javax.swing.JTextArea jta_FileContent;
    // End of variables declaration//GEN-END:variables
    DefaultMutableTreeNode nodo_seleccionado;
    String actualDir = "", rootDir = "./Our Local Disk C/", cacheDir = "./cache/", initFile = "", openedFile = "", openedFileContent = "", DirPathInServer = "";

    FS_Interface client;
    Registry registry;
    FS_Interface server;
    boolean close = false;
    String name = "";

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
    
    public int getNumberOfNodes(TreeModel model, Object node) {
        int count = 1;
        int nChildren = model.getChildCount(node);
        for (int i = 0; i < nChildren; i++) {
            count += getNumberOfNodes(model, model.getChild(node, i));
        }
        return count;
    }
}
