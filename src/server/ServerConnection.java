package server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import config.Config;

public class ServerConnection extends Thread {

    private String name;
    private Socket connection;
    private boolean play;
    private boolean connected = false;
    private int id;

    public ServerConnection otherPlayer;

    private static int connectedPlayers = 0;
    private InetAddress addr;
    private DatagramSocket enviarCast;

    public ServerConnection(int id, Socket connection, boolean play) {
        this.id = id;
        this.connection = connection;
        this.play = play;
    }

    public void run() {
        try {
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(connection.getOutputStream());
            String jogada = "";
            if (!connected) {
                jogada = inFromClient.readLine();
                outToClient.writeBytes(jogada + "\n");
                makeMulticastConnection();
                connected = true;
            }
            if (name == null) {
                name = inFromClient.readLine();
                outToClient.writeBytes(toString() + "\n");
            }
            while (true) {
                if (play) {
                    jogada = inFromClient.readLine();
                    setPlay();
                    otherPlayer.setPlay();
                    outToClient.writeBytes("Sua " + jogada + " próximo jogador: " + otherPlayer.GetId()
                            + getPlayerFlag() + "\n");
                    sandMulticast();
                }
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

    public void setPlay() {
        play = !play;
    }

    public synchronized int getConnectedPlayers() {
        return ++connectedPlayers;
    }

    private void makeMulticastConnection() throws IOException {
        addr = InetAddress.getByName(Config.multicastIp);
        enviarCast = new DatagramSocket();
    }

    private void sandMulticast() throws IOException {
        byte[] b = new byte[256];
        b = "Teste".getBytes();
        DatagramPacket pkg;
        pkg = new DatagramPacket(b, b.length, addr, Config.multicastPort);
        enviarCast.send(pkg);
    }

    public String getPlayerFlag() {
        return ((play) ? ";1" : ";0");
    }

    @Override
    public String toString() {
        return "Id: " + id + " Nome: " + name + getPlayerFlag();
    }
}