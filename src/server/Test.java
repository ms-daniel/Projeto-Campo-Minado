package server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import config.Config;

public class Test {
    public static void main(String[] args) throws IOException {
        Socket clientSocket = new Socket(Config.ip, Config.port);

        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        // outToServer.writeBytes("Command test" + "\n");
        while (true) {
            String res = inFromServer.readLine();
            System.out.println(res);
        }
        //clientSocket.close();
    }
}