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
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.*;
import java.util.*;

public class ChatClient {

    public static void main(String[] argv) {
        try {
            Scanner s = new Scanner(System.in);
            System.out.println("Nombre:");
            String name = s.nextLine().trim();

            FS_Interface client = new FS(name, null);
            Registry registry = LocateRegistry.getRegistry("192.168.0.102", 8888);
            FS_Interface server = (FS_Interface) registry.lookup("ejemplo");
            
            String msg = "[" + client.getName() + "] conectado!";
            server.send(msg);
            System.out.println("Servicio listo");
            server.addClient(client);

            while (true) {
                msg = s.nextLine().trim();
                msg = "[" + client.getName() + "] " + msg;
                server.send(msg);
            }

        } catch (Exception e) {
            System.out.println("[System] Server failed: " + e);
        }
    }
}
