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
    
	public MapMove(JLabel map, JLabel mapT) {
        this.map = map;
        this.mapT = mapT;
    }
	
	public MapMove(JLabel map) {
        this.map = map;
        this.mapT = null;
    }
	
	public void MoveMaps(int x, int y) {
		this.x = x;
		this.y = y;
		
		if(x < 0 || y < 0) {
			increment = -1;
		}else {
			increment = +1;
		}
	}
	
	public void AddCharacterToMap(Character Character) {
		this.characters.add(Character);
	}
	
	@Override
	public void run() {
		//System.out.println("X: " + map.getX() + " Y: " + map.getY());
		
		int xAux = map.getX() + x, yAux = map.getY() + y;
		
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
		
		map.setLocation(xAux, yAux);
		
		//posiciona todos os players dentro do mapa nos seus lugares
		if(y == 0)
			setLocaleToAllCharacters(45*increment, 0);
		else
			setLocaleToAllCharacters(0, 45*increment);
		
		if(mapT != null)
			mapT.setLocation(xAux, yAux);	
		//System.out.println("depois X: " + map.getX() + "depois Y: " + map.getY());

	}
	
	private void MoveAllCharacters(int x, int y) {
		Character CurrentCharacter;
		
		for(int i = 0; i < characters.size(); i++) {
			CurrentCharacter = characters.get(i);
			CurrentCharacter.InDecLocale(x, y);
		}
	}
	
	private void setLocaleToAllCharacters(int x, int y) {
		Character CurrentCharacter;
		
		for(int i = 0; i < characters.size(); i++) {
			CurrentCharacter = characters.get(i);
			CurrentCharacter.Locale(x, y);
		}
	}
	
}
