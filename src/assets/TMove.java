package assets;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import config.Config;

public class TMove extends Thread{
	private SecundaryCharacter character;
	private int[][] map = Config.matrizMap1;
	private static MulticastSocket receberCast;
    private static InetAddress grp;
    private static byte rec[] = new byte[256];
    private static DatagramPacket pkg = new DatagramPacket(rec, rec.length);
	private static String currentInfos;
	
	public TMove(SecundaryCharacter character) {
		this.character = character;
	}
	
	@Override
	public void run() {
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
		/*while(true) {
			UpDown();
		}*/
	}
	
	private synchronized void UpDown() {
		//this.character.WaitAFeelTime(500);
		
		for(int y = 45; y <= 47; y++){
			this.character.ToMove(0, y);
			
			this.character.WaitAFeelTime(1000);
		}
		for(int y = 48; y > 43; y--) {
			this.character.ToMove(0, y);
			
			this.character.WaitAFeelTime(1000);
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

	private String getCurrentInfos(){
        return currentInfos;
    }
}
