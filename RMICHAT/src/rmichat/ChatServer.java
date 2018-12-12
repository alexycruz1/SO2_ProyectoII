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

public class ChatServer {

    public static void main(String[] argv) {
        try {
            Scanner s = new Scanner(System.in);
            System.out.println("Nombre: ");
            String name = s.nextLine().trim();

            Chat server = new Chat(name);
            
            //Public address: 181.115.9.201
            //hostname ip-Adress: 127.0.1.1
            
            System.setProperty("java.rmi.server.hostname", "192.168.x.x");
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.bind("ejemplo", server);

            while (true) {
                String msg = s.nextLine().trim();
                if (server.getClient() != null) {
                    ChatInterface client = server.getClient();
                    msg = "[" + server.getName() + "] " + msg;
                    client.send(msg);
                }
            }

        } catch (Exception e) {
            System.out.println("[System] Server failed: " + e);
        }
    }
}
