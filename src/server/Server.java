package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

import config.Config;

public class Server {
    private static ArrayList<ServerConnection> connections = new ArrayList<ServerConnection>();
    private static boolean setedFirst = false;

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(Config.port);
        try {
            while (true) {
                Socket SocketConnection = server.accept();
                if (SocketConnection.isConnected() && (connections.size() + 1) <= Config.connectionsNumber) {
                    ServerConnection connection = new ServerConnection(SocketConnection, WillPlayFirst(),
                            "Moyses");
                    connection.start();
                    connections.add(connection);
                } else {
                    for (ServerConnection c : connections) {
                        if (!c.isAlive()) {
                            System.out.println("Removendo");
                            connections.remove(c);
                        }
                    }
                    if (connections.size() != 0) {
                        SocketConnection.close();
                    } else {
                        setedFirst = false;
                    }
                }
            }
        } catch (IOException e) {
            server.close();
            e.printStackTrace();
        }
    }

    private static boolean WillPlayFirst() {
        if (setedFirst) {
            return !setedFirst;
        }
        if ((connections.size() + 1) == Config.connectionsNumber && !setedFirst) {
            return !setedFirst;
        }
        Random randomico = new Random();
        int n = randomico.nextInt(10);
        setedFirst = (n % 2) == 0;
        return setedFirst;
    }

}
