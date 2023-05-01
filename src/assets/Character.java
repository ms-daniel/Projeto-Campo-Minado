package assets;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import back.ImagesChange;

public class Character extends Thread{
	private ImagesChange get = new ImagesChange();
	private JLabel Skinlabel;
	private JLabel PlayerNameLabel;
	
	private String PlayerName;
	private ImageIcon[][] SkinImages;
	private String SkinName;
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
	 * @param SkinName: nome da skin
	 * @param x: posicao x inicial
	 * @param y: posicao y inicial
	 */
	public Character(String name, String SkinName, JLabel SkinLabel, JLabel PlayerName) {
		//labels
		this.Skinlabel = SkinLabel;
		this.PlayerNameLabel = PlayerName;
		
		//nomes
		this.PlayerName = name;
		this.SkinName = SkinName;
		
		this.PlayerNameLabel.setText(this.PlayerName);
		
		//reconfigurando label da skin
		this.Skinlabel.setBounds(0, 0, 48, 66);
		this.Skinlabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.Skinlabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		//label do nome do jogador
		this.PlayerNameLabel.setBounds(0, 0, 55, 15);
		
		//carregar imagens da skin e transformar para ImageIcon
		SkinImages = ImageToIcon(get.GetSkinImages(SkinName));
		
		this.skin = SkinImages[0][0];
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
		this.PlayerNameLabel.setBounds(Skinlabel.getX() - 10, Skinlabel.getY() - 12, 65, 20);
		
		this.PlayerNameLabel.setFont(new Font("Arial", Font.BOLD, 10));
		this.PlayerNameLabel.setForeground(Color.WHITE);
		this.PlayerNameLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		
		this.PlayerNameLabel.setHorizontalTextPosition(SwingConstants.CENTER);
	}
	
	public void Move(char direction, int position) {
		this.SkinName = this.SkinName.replaceAll("\\d+", "") + position;
		
		skin = get.getIcon("character/luffy/" + SkinName + "-" + 1 + ".png");
		this.Skinlabel.setIcon(skin);
		
		WaitAFeelTime(250);
		
		skin = get.getIcon("character/luffy/" + SkinName + "-" + 2 + ".png");
		this.Skinlabel.setIcon(skin);
		
		WaitAFeelTime(250);
		
		skin = get.getIcon("character/luffy/" + SkinName + ".png");
		this.Skinlabel.setIcon(skin);
		this.move = false;
	}
	
	public void MoveTo(char direction, int position) {
		this.move = true;
		this.direction = direction;
		this.position = position;
	}
	
	public void Pause(boolean pause) {
		this.pause = pause;
	}
	
	public int GetSkinWidth() {
		return skin.getIconWidth();
	}
	
	public int GetSkinHeight() {
		return skin.getIconHeight();
	}
	
	/**
	 * Passa uma matriz de BufferedImage para ImageIcon
	 * @param images: matriz BufferedImage a ser convertida
	 * @return matriz de ImageIcon contendo todos os BufferedImage
	 */
	private ImageIcon[][] ImageToIcon(BufferedImage[][] images) {
		ImageIcon[][] icons = new ImageIcon[4][3]; 
		
		for(int y = 0; y < 4; y++) 
			for(int x = 0; x < 3; x++)
				icons[y][x] = new ImageIcon(images[y][x]);
		
		return icons;
	}
	
	/**
	 * espera um tempo antes da proxima ação
	 * @param time: milissegundos
	 * @throws InterruptedException 
	 */
	private void WaitAFeelTime(long time){
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
