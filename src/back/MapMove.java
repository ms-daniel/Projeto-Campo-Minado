package back;

import java.util.ArrayList;

import javax.swing.JLabel;
import assets.Character;

public class MapMove implements Runnable {
	private JLabel map;
    private JLabel mapT; 
    private int x;
    private int y;
    private int increment;
    private ArrayList<Character> characters = new ArrayList<>();;
    
    /**
     * Class responsible for moving the map of other players
     * @param map: Image of the main map
     * @param mapT: Usually the second layer of the map containing the tops of trees, mountains, etc.
     */
	public MapMove(JLabel map, JLabel mapT) {
        this.map = map;
        this.mapT = mapT;
    }
	
    /**
     * Class responsible for moving the map of other players
     * @param map: Image of the main map
     */
	public MapMove(JLabel map) {
        this.map = map;
        this.mapT = null;
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
	public void AddCharacterToMap(Character Character) {
		this.characters.add(Character);
	}
	
	/**
	 * Makes all the animation of the main character and the secondary ones according to the main one
	 */
	@Override
	public void run() {
		int xAux = map.getX() + x, yAux = map.getY() + y; //stores the final position of the map at
														  //the end of the animation of the main character
		
		//This for is responsible for the movement of the map while it shows the animation of the main character walking
		for(int i = 1; Math.abs(i) < 46; i+=increment) {
			if(y == 0) {
				map.setLocation(map.getX() + increment, map.getY());
				if(mapT != null)
					mapT.setLocation(mapT.getX() + increment, mapT.getY());
				
				MoveAllCharacters(increment, 0);
			}else {
				map.setLocation(map.getX(), map.getY() + increment);
				if(mapT != null)
					mapT.setLocation(mapT.getX(), mapT.getY() + increment);	
				
				MoveAllCharacters(0, increment);
			}

			try {
				Thread.sleep(12);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//=======================================================================
		
		//position the maps in their final places
		map.setLocation(xAux, yAux);
		if(mapT != null)
			mapT.setLocation(xAux, yAux);
		
		//positions all players within the map in their final places
		if(y == 0)
			setLocaleToAllCharacters(45*increment, 0);
		else
			setLocaleToAllCharacters(0, 45*increment);
	}
	
	/**
	 * Moves all players according to the animation and movement of the main player
	 * @param x: x position
	 * @param y: y position
	 */
	private void MoveAllCharacters(int x, int y) {
		Character CurrentCharacter;
		
		for(int i = 0; i < characters.size(); i++) {
			CurrentCharacter = characters.get(i);
			CurrentCharacter.InDecLocale(x, y);
		}
	}
	
	/**
	 * Moves all players to a specific position
	 * @param x: x position
	 * @param y: y position
	 */
	private void setLocaleToAllCharacters(int x, int y) {
		Character CurrentCharacter;
		
		for(int i = 0; i < characters.size(); i++) {
			CurrentCharacter = characters.get(i);
			CurrentCharacter.Locale(x, y);
		}
	}
	
}
