package assets;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import back.getIcon;

public class Character extends JLabel{
	private getIcon get = new getIcon();
	
	private String name;
	private String skin;
	
	private int posX;
	private int posY;
	
	private int frame;
	
	/**
	 * constructor para instaciar um personagem/character
	 * @param name: nome do personagem
	 * @param FolderSkin: local + nome do arquivo + extensao da skin
	 * @param x: posicao x inicial
	 * @param y: posicao y inicial
	 */
	public Character(String name, String FolderSkin) {
		this.name = name;
		this.skin = FolderSkin;
		
		setBounds(0, 0, 182, 252);
		setHorizontalAlignment(SwingConstants.CENTER);
		setAlignmentX(Component.CENTER_ALIGNMENT);
		setIcon(get.getIcon(FolderSkin));
	}
	
	public void Locale(int x, int y) {
		this.posX = x;
		this.posY = y;
		setLocation(x, y);
	}
}
