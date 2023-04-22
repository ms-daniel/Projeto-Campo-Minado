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
    private static String pkgValue;

    public void run(){
        try {
            makeConnection();
            while(true){
                System.out.println(receberPlays());
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void makeConnection() throws IOException {
        receberCast = new MulticastSocket(Config.multicastPort);
        grp = InetAddress.getByName(Config.multicastIp);
        receberCast.joinGroup(grp);
    }

    private static String receberPlays() throws IOException {
        receberCast.receive(pkg);
        pkgValue = new String(pkg.getData(), 0, pkg.getLength());
        return pkgValue;
    }

}
