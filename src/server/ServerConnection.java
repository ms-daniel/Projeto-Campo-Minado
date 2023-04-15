package server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import config.Config;

public class ServerConnection extends Thread {

    private String name;
    private Socket connection;
    private boolean play;
    private int id;

    public ServerConnection otherPlayer;

    private static int connectedPlayers = 0;

    public ServerConnection(int id, Socket connection, boolean play, String name) {
        this.id = id;
        this.connection = connection;
        this.play = play;
        this.name = name;
    }

    public void run() {
        try {
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(connection.getOutputStream());

            System.out.println("Chegou aqui com 2 " + connectedPlayers + " " + id);
            // String nmArquivo = inFromClient.readLine();
            while (true) {
                if (play) {
                    outToClient.writeBytes("Meu id: " + id + " Id do outro jogador: " + otherPlayer.GetId() + "\n");
                    setPlay();
                    otherPlayer.setPlay();
                } /*
                   * else {
                   * outToClient.writeBytes("Meu id: " + id + " Id do outro jogador: " +
                   * otherPlayer.GetId() + "\n");
                   * }
                   */
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
}