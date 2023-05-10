package server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import config.Config;

public class PlayAtServer {
    public static void main(String[] args) throws IOException {
        System.out.println("Conectando... Aguarde");
        System.out.println(ServerInterface.startServer(Config.ip, Config.port));

        ServerInterface.connectPlayer(Config.ip, Config.port);

        System.out.print("Digite seu nome: ");
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        String bombs = ServerInterface.sandNameAndSkin(inFromUser.readLine(), "skin_teste");
        String play = "";
        while (play != "sair") {
            play = inFromUser.readLine();
            int x = Integer.parseInt(play.split(";")[0]);
            int y = Integer.parseInt(play.split(";")[1]);
            System.out.println(ServerInterface.sandPlay(x,y));
        }
        ServerInterface.closeConnection();
    }
}
