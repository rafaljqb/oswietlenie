/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.oswietlenie;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
/**
 *
 * @author rjaku
 */
public class PanelSocketSerwer {
    private SocketIOServer server;
    
    public static PanelSocketSerwer getInstance() {
        return PanelSocketSerwerHolder.INSTANCE;
    }
    
    private static class PanelSocketSerwerHolder {
 
        private static final PanelSocketSerwer INSTANCE = new PanelSocketSerwer();
    }
    
    public void start() {
        final SerialConnection serialConnection = new SerialConnection();
        serialConnection.initialize();
        
        Configuration config = new Configuration();
        config.setHostname("192.168.1.29");
        config.setPort(8080);
        server = new SocketIOServer(config);
        server.addConnectListener(new ConnectListener() {
            @Override
            public void onConnect(SocketIOClient client) {
                System.out.println("onConnected");
            }
        });
        server.addDisconnectListener(new DisconnectListener() {
            @Override
            public void onDisconnect(SocketIOClient client) {
                System.out.println("onDisconnected");
            }
        });
        server.addEventListener("light", Light.class, new DataListener<Light>() {
 
            @Override
            public void onData(SocketIOClient client, Light data, AckRequest ackSender) throws Exception {
                serialConnection.write(data.isState() ? "1" : "0");
                System.out.println("onLight: " + data.toString());
                server.getBroadcastOperations().sendEvent("light", data);
            }
        });
        System.out.println("Starting server...");
        server.start();
        System.out.println("Server started");
    }
    
    public void stop() {
        if (server != null) {
            server.stop();   
        }
    }
}
