package server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import config.Config;

public class ServerInterface {
    /* Objs de Servidor */
    public static Server server;
    public static String ipServer;

    /* Objs de Cliente */
    private static Socket clientSocket;
    private static DataOutputStream outToServer;
    private static BufferedReader inFromServer;
    private static TPlays board;

    public static String startServer(int port){
        server = new Server();
        ipServer = server.runServer(port);
        if(ipServer != null){
            server.start();
            return ipServer;
        }
        server = null; 
        return "Porta indisponivel";
    }

    public static void connectPlayer(String ip, int port) throws IOException{
        makeConnection(ip, port);
        board = new TPlays();
        board.start();
    }

    public static String sandPlayAndRecive(String play) throws IOException {
        String res = "Erro: Conexão não iniciada entre jogador e servidor. Tente utilizar o método connectPlayer antes";
        if(outToServer != null && inFromServer != null){
            outToServer.writeBytes(play + "\n");
            res = inFromServer.readLine();
        }
        return res;
    }

    public static void closeConnection() throws IOException {
        if(clientSocket != null){
            clientSocket.close();
        }
    }

    public static void closeServer() throws IOException {
        if(server != null){
            server.closeServer();
        }
    }

    private static void makeConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        outToServer = new DataOutputStream(clientSocket.getOutputStream());
        inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }
}
