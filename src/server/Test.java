package server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;

import config.Config;

public class Test {
    private static Socket clientSocket;
    private static DataOutputStream outToServer;
    private static BufferedReader inFromServer;
    private static MulticastSocket receberCast;
    private static InetAddress grp;

    public static void main(String[] args) throws IOException {

        System.out.println("Conectando... Aguarde");
        makeConnection();
        System.out.println(sandPlayAndRecive("Conectando..."));

        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        String play = "";
        System.out.print("Digite seu nome: ");
        play = inFromUser.readLine();
        System.out.println(sandPlayAndRecive(play));
        while (play != "sair") {
            System.out.print("Digite a sua jogada: ");
            play = inFromUser.readLine();
            sandPlay(play);
            receberMatriz();
        }
        closeConnection();
    }

    public static void makeConnection() throws IOException {
        clientSocket = new Socket(Config.ip, Config.port);
        outToServer = new DataOutputStream(clientSocket.getOutputStream());
        inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        receberCast = new MulticastSocket(Config.multicastPort);
        grp = InetAddress.getByName(Config.multicastIp);
        receberCast.joinGroup(grp);
    }

    public static String sandPlayAndRecive(String play) throws IOException {
        outToServer.writeBytes(play + "\n");
        String res = inFromServer.readLine();
        return res;
    }

    public static void sandPlay(String play) throws IOException {
        outToServer.writeBytes(play + "\n");
    }

    private static void closeConnection() throws IOException {
        clientSocket.close();
    }

    private static boolean receberMatriz() throws IOException {
        byte rec[] = new byte[256];
        DatagramPacket pkg = new DatagramPacket(rec, rec.length);
        String pkgValue;
        receberCast.receive(pkg);
        if (pkg.getData().length > 0) {
            pkgValue = new String(pkg.getData(), 0, pkg.getLength());
            System.out.println(pkgValue);
            return false;
        }
        return true;
    }
}