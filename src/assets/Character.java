package assets;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import back.ImagesChange;

public class Character extends Thread{
	private ImagesChange get = new ImagesChange();
	private JLabel label;
	
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
	 * @param name: nome do personagem
	 * @param FolderSkin: local + nome do arquivo + extensao da skin
	 * @param x: posicao x inicial
	 * @param y: posicao y inicial
	 */
	public Character(String name, String FolderSkin, JLabel label) {
		this.label = label;
		this.name = name;
		this.skinLocal = FolderSkin;
		SetSkinNamePosition(FolderSkin);
		
		this.label.setBounds(0, 0, 182, 252);
		this.label.setHorizontalAlignment(SwingConstants.CENTER);
		this.label.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.skin = get.getIcon(FolderSkin);
		this.label.setIcon(skin);
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
		this.label.setLocation(x, y);
	}
	
	public void Move(char direction, int position) {
		this.nameSkin = this.nameSkin.replaceAll("\\d+", "") + position;
		
		skin = get.getIcon("character/luffy/" + nameSkin + "-" + 1 + ".png");
		this.label.setIcon(skin);
		
		WaitAFeelTime(250);
		
		skin = get.getIcon("character/luffy/" + nameSkin + "-" + 2 + ".png");
		this.label.setIcon(skin);
		
		WaitAFeelTime(250);
		
		skin = get.getIcon("character/luffy/" + nameSkin + ".png");
		this.label.setIcon(skin);
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
		this.label.setIcon(skin);
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
