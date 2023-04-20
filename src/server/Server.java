package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import config.Config;

public class Server {
    private static ArrayList<ServerConnection> connections = new ArrayList<ServerConnection>();
    private static ArrayList<Player> players = new ArrayList<Player>();
    private static int board[][] = new int[Config.boardLength][Config.boardLength];

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(Config.port);
        try {
            while (true) {
                Socket SocketConnection = server.accept();
                Player player;
                if (SocketConnection.isConnected() && (connections.size() + 1) <= Config.connectionsNumber) {
                    player = new Player();
                    players.add(player);
                    ServerConnection connection = new ServerConnection(SocketConnection, board, player, players);
                    connections.add(connection);
                    System.out.println(connections.size() + "º" + " jogador conectado");
                    if (connections.size() == Config.connectionsNumber) {
                        PreencherBoard();
                        int invertCount = 1;
                        for (int i = 0; i < connections.size(); i++) {
                            if (connections.size() == invertCount) {
                                invertCount = 0;
                            }
                            connections.get(i).otherPlayer = connections.get(invertCount++);
                            connections.get(i).start();
                        }
                    }

                } else {
                    for (ServerConnection c : connections) {
                        if (!c.isAlive()) {
                            connections.remove(c);
                            System.out.println(connections.size());
                        }
                    }
                    if (connections.size() != 0) {
                        SocketConnection.close();
                    }
                }
            }
        } catch (IOException e) {
            server.close();
            e.printStackTrace();
        }
    }

    private static void PreencherBoard() {
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
