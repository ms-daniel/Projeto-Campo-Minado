package server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import config.Config;

public class Test {
    private static Socket clientSocket;
    private static DataOutputStream outToServer;
    private static BufferedReader inFromServer;

    public static void main(String[] args) throws IOException {

        System.out.println("Conectando... Aguarde " + Config.connectionsNumber + " jogadores conectar.");
        makeConnection();
        sandPlay("Conectado");

        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        String play = "";
        System.out.print("Digite seu nome: ");
        while (play != "sair") {
            play = inFromUser.readLine();
            sandPlay(play);
            System.out.print("Digite a sua jogada: ");
        }
        closeConnection();
    }

    public static void makeConnection() throws IOException {
        clientSocket = new Socket(Config.ip, Config.port);
        outToServer = new DataOutputStream(clientSocket.getOutputStream());
        inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public static void sandPlay(String play) throws IOException {
        outToServer.writeBytes(play + "\n");
        String res = inFromServer.readLine();
        System.out.println(res);

    }

    private static void closeConnection() throws IOException {
        clientSocket.close();
    }
}