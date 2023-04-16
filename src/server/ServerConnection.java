package server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerConnection extends Thread {

    private String name;
    private Socket connection;
    private boolean play;
    private boolean connected = false;
    private int id;

    public ServerConnection otherPlayer;

    private static int connectedPlayers = 0;

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
                connected = true;
            }
            if (name == null) {
                name = inFromClient.readLine();
                outToClient.writeBytes(toString() + "\n");
            }
            while (true) {
                if (play) {
                    jogada = inFromClient.readLine();
                    outToClient.writeBytes("Sua " + jogada + " Id do próximo jogador: " + otherPlayer.GetId() + "\n");
                    setPlay();
                    otherPlayer.setPlay();
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

    @Override
    public String toString() {
        return "Id: " + id + "Nome: " + name;
    }
}