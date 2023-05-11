package back;

import java.util.ArrayList;

import javax.swing.JLabel;
import assets.SecundaryCharacter;

public class MapMove implements Runnable {
	private JLabel map;
    private JLabel mapT; 
    private int x;
    private int y;
    private int increment;
    
    private SecundaryCharacter[] characters = new SecundaryCharacter[4];
    private int QTPlayers = 0;
    
    /**
     * Class responsible for moving the map of other players
     * @param map: Image of the main map
     * @param mapT: Usually the second layer of the map containing the tops of trees, mountains, etc.
     */
//	public MapMove(JLabel map, JLabel mapT) {
//        this.map = map;
//        this.mapT = mapT;
//    }
	
    /**
     * Class responsible for moving the map of other players
     * @param map: Image of the main map
     */
//	public MapMove(JLabel map) {
//        this.map = map;
//        this.mapT = null;
//    }
	
	public void setMaps(JLabel map, JLabel mapT) {
        this.map = map;
        this.mapT = mapT;
	}
	
	public void setSecondMap(JLabel mapT) {
		this.mapT = mapT;
	}
	
	/**
	 * Tells where the map should be moved and whether it's a negative (up) or positive (down) move
	 * @param x: x position
	 * @param y: y position
	 */
	public void MoveMaps(int x, int y) {
		this.x = x;
		this.y = y;
		
		if(x < 0 || y < 0) {
			increment = -1;
		}else {
			increment = +1;
		}
	}
	
	/**
	 * Add a new character to the list of secondary characters (other players)
	 * @param Character: player
	 */
	public void AddCharacterToMap(SecundaryCharacter Character) {
		this.characters[QTPlayers] = Character;
		QTPlayers++;
	}
	
	/**
	 * Makes all the animation of the main character and the secondary ones according to the main one
	 */
	@Override
	public synchronized void run() {
		int xAux = map.getX() + x, yAux = map.getY() + y; //stores the final position of the map at
														  //the end of the animation of the main character
		
		//This for is responsible for the movement of the map while it shows the animation of the main character walking
		for(int i = 1; Math.abs(i) < 46; i+=increment) {
			if(y == 0) {
				MoveAllCharacters(increment, 0);
				map.setLocation(map.getX() + increment, map.getY());
				if(mapT != null)
					mapT.setLocation(mapT.getX() + increment, mapT.getY());
			}else {
				MoveAllCharacters(0, increment);
				map.setLocation(map.getX(), map.getY() + increment);
				if(mapT != null)
					mapT.setLocation(mapT.getX(), mapT.getY() + increment);	
			}

			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//=======================================================================
		
		//positions all players within the map in their final places
		if(y == 0)
			setLocaleToAllCharacters(45*increment, 0);
		else
			setLocaleToAllCharacters(0, 45*increment);
				
		//position the maps in their final places
		map.setLocation(xAux, yAux);
		if(mapT != null)
			mapT.setLocation(xAux, yAux);
	}
	
	/**
	 * Moves all players according to the animation and movement of the main player
	 * @param x: x position
	 * @param y: y position
	 */
	private synchronized void MoveAllCharacters(int x, int y) {
		SecundaryCharacter CurrentCharacter;
		
		for(int i = 0; i < QTPlayers; i++) {
			CurrentCharacter = characters[i];
			CurrentCharacter.IncrementLocale(x, y);
		}
	}
	
	/**
	 * Moves all players to a specific position
	 * @param x: x position
	 * @param y: y position
	 */
	private synchronized void setLocaleToAllCharacters(int x, int y) {
		SecundaryCharacter CurrentCharacter;
		
		for(int i = 0; i < QTPlayers; i++) {
			CurrentCharacter = characters[i];
			CurrentCharacter.Locale(x, y);
		}
	}
	
}
