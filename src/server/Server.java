package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import config.Config;

public class Server extends Thread {
    private static ArrayList<ServerConnection> connections = new ArrayList<ServerConnection>();
    private static ArrayList<Player> players = new ArrayList<Player>();
    private static int board[][] = new int[Config.boardLength][Config.boardLength];
    private static boolean running = false;
    private static ServerSocket server;

    private int port;

    public Server(int port) {
        this.port = port;
    }

    public void run() {
        try {
            server = new ServerSocket(Config.port);
            while (true) {
                Socket SocketConnection = server.accept();
                Player player;
                if (SocketConnection.isConnected() && (connections.size() + 1) <= Config.connectionsNumber) {
                    player = new Player();
                    players.add(player);
                    ServerConnection connection = new ServerConnection(SocketConnection, board, player, players, connections);
                    connections.add(connection);
                    if (connections.size() == Config.connectionsNumber && !running) {
                        PreencherBoard();
                        for (ServerConnection c : connections) {
                            c.start();
                        }
                        running = true;
                    }else{
                        if(running){
                            connection.start();
                        }
                    }
                } else {
                    SocketConnection.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isRunning(int port){
        if(server == null){
            return false;
        }
        return server.getLocalPort() == port;
    }

    private void PreencherBoard() {
        int count = 0;
        for (int l = 0; l < Config.boardLength; l++) {
            for (int c = 0; c < Config.boardLength; c++) {
                if (count > 600) {
                    /* Sem bomba */
                    board[l][c] = 0;
                } else {
                    /* Com bomba */
                    board[l][c] = 2;
                }
                count++;
            }
        }
    }
}
