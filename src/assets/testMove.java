package assets;

import config.Config;

public class testMove extends Thread{
	private SecundaryCharacter character;
	private int[][] map = Config.matrizMap1;
	
	public testMove(SecundaryCharacter character) {
		this.character = character;
	}
	
	@Override
	public void run() {
		while(true) {
			UpDown();
		}
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
}
