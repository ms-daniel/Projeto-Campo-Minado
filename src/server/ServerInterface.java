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

    public static String startServer(String ip, int port){
        server = new Server();
        ipServer = server.runServer(port, ip);
        if(ipServer != null){
            server.start();
            return ipServer;
        }
        server = null; 
        return "Porta indisponivel";
    }

    public static Boolean connectPlayer(String ip, int port){
        try {
			makeConnection(ip, port);
            if(Config.testServer){
                board = new TPlays();
                board.start();
            }
	        return true;
		} catch (IOException e) {
			return false;
		}
    }

    /**
     * 
     * @param play Na primeira chamada deve ser enviado o nome do jogador
     * @return Na primeira chamada retorna as bombas
     * @throws IOException
     */
    public static String sandPlay(int x, int y) throws IOException {
        String play = x + ";" + y;
        String res = "Erro: Conexão não iniciada entre jogador e servidor. Tente utilizar o método connectPlayer antes";
        if(outToServer != null && inFromServer != null){
            outToServer.writeBytes(play + "\n");
            res = inFromServer.readLine();
        }
        return res;
    }

    /**
     * 
     * @param name Nome do jogador
     * @param skin Skin do jogador
     * @return As bombas no seguinte formato - x;y:x¹;y¹:x²;y²...
     * @throws IOException
     */
    public static String sandNameAndSkin(String name, String skin) throws IOException {
        String res = "Erro: Conexão não iniciada entre jogador e servidor. Tente utilizar o método connectPlayer antes";
        if(outToServer != null && inFromServer != null){
            outToServer.writeBytes(name + ";" + skin + "\n");
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

    public static String getCurrentInfos() throws IOException{
        if(board != null){
            return board.getCurrentInfos();
        }
        return "É necessário iniciar a thread board";
    }

    private static void makeConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        outToServer = new DataOutputStream(clientSocket.getOutputStream());
        inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }
}
