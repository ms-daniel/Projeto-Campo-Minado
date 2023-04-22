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
                receberMatriz();
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

    private static boolean receberMatriz() throws IOException {
        receberCast.receive(pkg);
        if (pkg.getData().length > 0) {
            pkgValue = new String(pkg.getData(), 0, pkg.getLength());
            System.out.println(pkgValue);
            return false;
        }
        return true;
    }

}
