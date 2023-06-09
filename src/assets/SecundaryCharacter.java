package assets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.swing.JLabel;

public class SecundaryCharacter extends Character{
	private int toX = 0;
	private int toY = 0;
	
	public SecundaryCharacter(String name, String SkinName, JLabel SkinLabel, JLabel PlayerName) {
		super(name, SkinName, SkinLabel, PlayerName);
	}
	
	@Override
	public synchronized void run() {
		// TODO Criar movimentação feita pelo servidor

		Move();
		this.interrupt();
	}
	
	/**
	 * Esse metodo deve ser chamado pelo servidor 
	 * para mover os personagens dentro do mapa
	 * @param x: coordenada x para mover
	 * @param y: coordenada y para mover
	 */
	public void ToMove(int x, int y) {
		//verifica se irá pra esquerda/direita ou cima/baixo
		if(x != this.CoordenateX) {
			if(x > this.CoordenateX)
				toX = 45;
			else
				toX = -45;
		}
		else {
			if(y > this.CoordenateY)
				toY = 45;
			else
				toY = -45;
		}
		
		this.CoordenateX = x;
		this.CoordenateY = y;
		
		new Thread(this).start();
	}
	
	protected synchronized void Move() {
		int increment = 1;
		int auxX = toX, auxY = toY;
		int position = 0;
		boolean isX = false;

		if(toX < 0 || toY < 0)
			increment = -1;
		
//		while((Math.abs(auxX) > 0) || (Math.abs(auxY) > 0)) {
//			if(auxX != 0) {
//				IncrementLocale(increment, 0);
//				auxX += (increment*-1);
//				
//				isX = true;
//			}
//			else if(auxY != 0) {
//				IncrementLocale(0, increment);
//				auxY += (increment*-1);
//			}
//			WaitAFeelTime(10);
//		}

		this.Locale(toX, toY);
		
		if(toX != 0)
			toX = 0;
		else
			toY = 0;

	}
	
	public void setCoordenateX(int x){
		this.CoordenateX = x;
	}
	
	public void setCoordenateY(int y){
		this.CoordenateY = y;
	}
	
	
	@Override
	public void WaitAFeelTime(long time){
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * increment or decrement player location
	 * @param x: increment or decrement x
	 * @param y: increment or decrement y
	 */
	public void IncrementLocale(int x, int y) {
		this.Skinlabel.setLocation(Skinlabel.getX() + x, Skinlabel.getY() + y);
		
		this.PlayerNameLabel.setLocation(Skinlabel.getX() + adjusteNameLabel, Skinlabel.getY() - 12);
	}
}



