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
	
	private Socket connection;
	
	private BufferedReader inFromServer;
	private InputStream toServer;
	
	public SecundaryCharacter(String name, String SkinName, JLabel SkinLabel, JLabel PlayerName, Socket socket) {
		super(name, SkinName, SkinLabel, PlayerName);
		this.connection = socket;
		
		try {
			this.inFromServer = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		// TODO Criar movimentação feita pelo servidor
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
			if(y > this.CoordenateX)
				toY = 45;
			else
				toY = -45;
		}
		
		this.CoordenateX = x;
		this.CoordenateY = y;
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
