package assets;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import back.ImagesChange;

public class Character extends Thread{
	private ImagesChange get = new ImagesChange();
	private JLabel Skinlabel;
	private JLabel PlayerName;
	
	private String name;
	private String skinLocal;
	private String nameSkin;
	private ImageIcon skin;
	
	private boolean pause = false;
	private boolean move = false;
	private char direction;
	private int position;
	
	private int posX;
	private int posY;
	
	/**
	 * constructor para instaciar um personagem/character
	 * @param name: nome do jogador
	 * @param FolderSkin: local + nome do arquivo + extensao da skin
	 * @param x: posicao x inicial
	 * @param y: posicao y inicial
	 */
	public Character(String name, String FolderSkin, JLabel SkinLabel, JLabel PlayerName) {
		this.Skinlabel = SkinLabel;
		this.PlayerName = PlayerName;
		
		this.name = name;
		this.PlayerName.setText(this.name);
		
		this.skinLocal = FolderSkin;
		SetSkinNamePosition(FolderSkin);
		
		this.Skinlabel.setBounds(0, 0, 48, 66);
		this.Skinlabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.Skinlabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		this.PlayerName.setBounds(0, 0, 55, 15);
		
		this.skin = get.getIcon(FolderSkin);
		this.Skinlabel.setIcon(skin);
	}
	
	@Override
	public void run() {
		while(!pause) { // Verifica se o pause foi ativado
			synchronized (this) {} //apenas para poder continuar executando a thread
			if(move){
				Move(this.direction, this.position);
			}
			
		}
		
	}
	
	public void Locale(int x, int y) {
		this.posX = x;
		this.posY = y;
		this.Skinlabel.setLocation(x, y);
		this.PlayerName.setBounds(Skinlabel.getX() - 10, Skinlabel.getY() - 12, 65, 20);
		
		this.PlayerName.setFont(new Font("Arial", Font.BOLD, 10));
		this.PlayerName.setForeground(Color.WHITE);
		this.PlayerName.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		
		this.PlayerName.setHorizontalTextPosition(SwingConstants.CENTER);
	}
	
	public void Move(char direction, int position) {
		this.nameSkin = this.nameSkin.replaceAll("\\d+", "") + position;
		
		skin = get.getIcon("character/luffy/" + nameSkin + "-" + 1 + ".png");
		this.Skinlabel.setIcon(skin);
		
		WaitAFeelTime(250);
		
		skin = get.getIcon("character/luffy/" + nameSkin + "-" + 2 + ".png");
		this.Skinlabel.setIcon(skin);
		
		WaitAFeelTime(250);
		
		skin = get.getIcon("character/luffy/" + nameSkin + ".png");
		this.Skinlabel.setIcon(skin);
		this.move = false;
	}
	
	public synchronized void MoveTo(char direction, int position) {
		this.move = true;
		this.direction = direction;
		this.position = position;
		//this.notify();
	}
	
	public void Resize(int width, int height) {
		skin = get.Resize(skin, width, height);
		this.Skinlabel.setIcon(skin);
	}
	
	public void Pause(boolean pause) {
		this.pause = pause;
	}
	
	private void SetSkinNamePosition(String Folder) {
		String[] partes = Folder.split("/");
		
		partes = partes[partes.length-1].split("\\.");
		
		this.nameSkin = partes[0];
	}
	
	public int GetSkinWidth() {
		return skin.getIconWidth();
	}
	
	public int GetSkinHeight() {
		return skin.getIconHeight();
	}
	
	/**
	 * espera um tempo antes da proxima ação
	 * @param time: milissegundos
	 * @throws InterruptedException 
	 */
	public void WaitAFeelTime(long time){
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
