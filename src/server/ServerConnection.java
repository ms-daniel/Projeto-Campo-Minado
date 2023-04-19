package server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

import config.Config;

public class ServerConnection extends Thread {

    private String name;
    private Socket connection;
    private String jogada;
    private int id;

    public ServerConnection otherPlayer;

    private InetAddress addr;
    private DatagramSocket enviarCast;

    public ServerConnection(int id, Socket connection) {
        this.id = id;
        this.connection = connection;
    }

    public void run() {
        try {
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(connection.getOutputStream());

            
            name = inFromClient.readLine();
            makeMulticastConnection();
            outToClient.writeBytes(toString() + "\n");
            
            while (true) {
                jogada = inFromClient.readLine();
                /* Criar Thread para Manipular Matriz  Aqui e enviar multicast */
                sandMulticast();
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public String GetName() {
        return this.name;
    }

    public int GetId() {
        return this.id;
    }

    private void makeMulticastConnection() throws IOException {
        addr = InetAddress.getByName(Config.multicastIp);
        enviarCast = new DatagramSocket();
    }

    private void sandMulticast() throws IOException {
        byte[] b = new byte[256];
        b = jogada.getBytes();
        DatagramPacket pkg;
        pkg = new DatagramPacket(b, b.length, addr, Config.multicastPort);
        enviarCast.send(pkg);
    }

    @Override
    public String toString() {
        return "Id: " + id + " Nome: " + name;
    }
}