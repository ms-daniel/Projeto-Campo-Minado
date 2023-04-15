package server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerConnection extends Thread {

    private String nome;
    private Socket connection;
    private boolean play;

    public ServerConnection(Socket connection, boolean play, String nome) {
        this.connection = connection;
        this.play = play;
        this.nome = nome;
    }

    public void run() {
        try {
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(connection.getOutputStream());
            String nmArquivo = inFromClient.readLine();

            outToClient.writeBytes(nmArquivo.toUpperCase() + " " + nome + " " + play + "\n");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}