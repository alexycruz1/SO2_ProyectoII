/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmichat;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

/**
 *
 * @author alexyc
 */
public class FS extends javax.swing.JFrame {

    /**
     * Creates new form FS
     */
    public FS() {
        initComponents();

        this.setLocationRelativeTo(null);

        try {
            this.scanFS();
        } catch (InterruptedException ex) {
            Logger.getLogger(FS.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        FS_CreateFile = new javax.swing.JMenuItem();
        FS_CreateDirectory = new javax.swing.JMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        RMI_FS = new javax.swing.JTree();

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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("Local disk (C:)");
        RMI_FS.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        RMI_FS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RMI_FSMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(RMI_FS);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 606, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE)
                .addContainerGap())
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

            try {
                if (file.createNewFile() && !"".equals(FileName)) {
                    JOptionPane.showMessageDialog(this, "File created! ", "CREATE FILE", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    if (FileName.equals("")) {
                        JOptionPane.showMessageDialog(this, "File name required. ", "CREATE FILE", JOptionPane.WARNING_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "File already exists. ", "CREATE FILE", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(FS.class.getName()).log(Level.SEVERE, null, ex);
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

            try {
                if (file.createNewFile() && !"".equals(FileName)) {
                    JOptionPane.showMessageDialog(this, "File created! ", "CREATE FILE", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    if (FileName.equals("")) {
                        JOptionPane.showMessageDialog(this, "File name required. ", "CREATE FILE", JOptionPane.WARNING_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "File already exists. ", "CREATE FILE", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(FS.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_FS_CreateFileActionPerformed

    private void FS_CreateDirectoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FS_CreateDirectoryActionPerformed
        // TODO add your handling code here:
        String DirectoryName = JOptionPane.showInputDialog(this, "Enter directory name", "CREATE DIRECTORY", JOptionPane.INFORMATION_MESSAGE);

        if (nodo_seleccionado.isRoot()) {
            File file = new File(rootDir + DirectoryName);

            if (!file.exists() && !"".equals(DirectoryName)) {
                new File(rootDir + DirectoryName).mkdirs();

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
                new File(rootDir + DirectoryName).mkdirs();

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
            java.util.logging.Logger.getLogger(FS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FS().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem FS_CreateDirectory;
    private javax.swing.JMenuItem FS_CreateFile;
    private javax.swing.JPopupMenu FS_Options;
    private javax.swing.JTree RMI_FS;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
    DefaultMutableTreeNode nodo_seleccionado;
    String rootDir = "./Local disk (C:)/";

    public void scanFS() throws InterruptedException {
        File currentDir = new File(rootDir);

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