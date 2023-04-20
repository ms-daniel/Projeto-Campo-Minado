package back;

import javax.swing.JLabel;

public class MapMove implements Runnable {
	private JLabel map;
    private JLabel mapT; 
    private int x;
    private int y;
    private int increment;
    
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
	
	@Override
	public void run() {
		//System.out.println("X: " + map.getX() + " Y: " + map.getY());
		
		int xAux = map.getX() + x, yAux = map.getY() + y;
		
		for(int i = 1; Math.abs(i) < 46; i+=increment) {
			if(y == 0) {
				map.setLocation(map.getX() + increment, map.getY());
				if(mapT != null)
					mapT.setLocation(mapT.getX() + increment, mapT.getY());
			}else {
				map.setLocation(map.getX(), map.getY() + increment);
				if(mapT != null)
					mapT.setLocation(mapT.getX(), mapT.getY() + increment);	
			}

			try {
				Thread.sleep(12);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		map.setLocation(xAux, yAux);
		if(mapT != null)
			mapT.setLocation(xAux, yAux);	
		//System.out.println("depois X: " + map.getX() + "depois Y: " + map.getY());

	}
	
}
