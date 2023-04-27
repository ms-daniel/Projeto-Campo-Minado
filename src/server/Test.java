package server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import config.Config;

public class Test {
    public static void main(String[] args) throws IOException {
        System.out.println("Conectando... Aguarde");
        System.out.println(ServerInterface.startServer(Config.port));

        ServerInterface.connectPlayer(Config.ip, Config.port);

        System.out.print("Digite seu nome: ");
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        String bombs = ServerInterface.sandPlayAndRecive(inFromUser.readLine());
        String play = "";

        while (play != "sair") {
            play = inFromUser.readLine();
            System.out.println(ServerInterface.sandPlayAndRecive(play));
        }
        ServerInterface.closeConnection();
    }
}