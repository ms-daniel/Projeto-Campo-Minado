package server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

import config.Config;

public class ServerConnection extends Thread {

    private Socket connection;
    private String jogada;
    private int[][] board;

    private Player player;
    private ArrayList<Player> players;

    public ArrayList<ServerConnection> connections;

    private InetAddress addr;
    private DatagramSocket enviarCast;

    public ServerConnection(Socket connection, int[][] board, Player player, ArrayList<Player> players, ArrayList<ServerConnection> connections) {
        this.connection = connection;
        this.board = board;
        this.player = player;
        this.players = players;
        this.connections = connections;
    }

    public void run() {
        try {
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(connection.getOutputStream());

            player.SetName(inFromClient.readLine());
            makeMulticastConnection();
            outToClient.writeBytes(getAllBombs() + "\n");

            while (true) {
                jogada = inFromClient.readLine();
                if (jogada.contains(";")) {
                    player.SetCoordinates(jogada.split(";")[0], jogada.split(";")[1]);
                    if (verifyCoordinates(player.getX(), player.getY())) {
                        outToClient.writeBytes("Bomba" + "\n");
                    } else {
                        outToClient.writeBytes("Seguro" + "\n");
                    }
                } else {
                    outToClient.writeBytes("Erro: Coordenadas invalidas" + "\n");
                }
                sandMulticast();
            }

        } catch (IOException e) {
            players.remove(player);
            connections.remove(this);
        }
    }

    private void makeMulticastConnection() throws IOException {
        addr = InetAddress.getByName(Config.multicastIp);
        enviarCast = new DatagramSocket();
    }

    private void sandMulticast() throws IOException {
        byte[] b = new byte[256];
        b = getAllData().getBytes();
        DatagramPacket pkg;
        pkg = new DatagramPacket(b, b.length, addr, Config.multicastPort);
        enviarCast.send(pkg);
    }

    private String getAllData() {
        String data = "";
        for (Player i : players) {
            data += i.toString();
        }
        return data;
    }

    private String getAllBombs() {
        String data = "";
        for (int l = 0; l < Config.boardLength; l++) {
            for (int c = 0; c < Config.boardLength; c++) {
                if (board[l][c] == 2) {
                    data += String.valueOf(l) + ";";
                    data += String.valueOf(c) + ":";
                }
            }
        }
        return data;
    }

    private boolean verifyCoordinates(int x, int y) {
        if (x > Config.boardLength || y > Config.boardLength) {
            return false;
        }
        if (board[x][y] == 2) {
            board[x][y] = 0;
            return true;
        }
        return false;
    }
}