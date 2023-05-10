package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import config.Config;

public class TPlays extends Thread {
    private static MulticastSocket receberCast;
    private static InetAddress grp;
    private static byte rec[] = new byte[256];
    private static DatagramPacket pkg = new DatagramPacket(rec, rec.length);
    private static String currentInfos;

    public void run(){
        try {
            makeConnection();
            while(true){
                receberPlays();
                System.out.println(getCurrentInfos());
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void makeConnection() throws IOException {
        receberCast = new MulticastSocket(Config.multicastPort);
        grp = InetAddress.getByName(Config.multicastIp);
        receberCast.joinGroup(grp);
    }

    private void receberPlays() throws IOException {
        receberCast.receive(pkg);
        currentInfos = new String(pkg.getData(), 0, pkg.getLength());
    }

    public String getCurrentInfos(){
        return currentInfos;
    }

}
