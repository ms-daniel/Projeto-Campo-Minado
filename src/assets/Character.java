package assets;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import back.ImagesChange;

public class Character extends Thread{
	private ImagesChange get = new ImagesChange();
	private Font fonte = new Font("Arial", Font.BOLD, 10);
	
	protected JLabel Skinlabel;
	protected JLabel PlayerNameLabel;
	
	private String PlayerName;
	private ImageIcon[][] SkinImages;
	protected String SkinName;
	private ImageIcon skin;
	
	private boolean pause = false;
	private boolean move = false;
	private char direction;
	private int position;
	protected int adjusteNameLabel = 0;
	
	private int posXatCharacter = 0;
	private int posYatCharacter = 0;
	
	private int CoordenateX = 0;
	private int CoordenateY = 0;
	
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
		name = name.toUpperCase();
		this.PlayerName = name;
		this.SkinName = SkinName;
		
		this.adjusteNameLabel = 23 - (this.PlayerName.length()*4);
		
		this.PlayerNameLabel.setText(this.PlayerName);
		
		//reconfigurando label da skin
		this.Skinlabel.setBounds(0, 0, 48, 66);
		this.Skinlabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.Skinlabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		//label do nome do jogador
		this.PlayerNameLabel.setBounds(0, 0, name.length()*8, 9);
		this.PlayerNameLabel.setFont(this.fonte);
		this.PlayerNameLabel.setForeground(Color.WHITE);
		this.PlayerNameLabel.setBackground(Color.BLUE);
		this.PlayerNameLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		this.PlayerNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.PlayerNameLabel.setVerticalAlignment(SwingConstants.CENTER);
		this.PlayerNameLabel.setOpaque(true);
		
		//carregar imagens da skin e transformar para ImageIcon
		SkinImages = ImageToIcon(get.GetSkinImages(SkinName));
		
		this.skin = SkinImages[0][0];
		this.Skinlabel.setIcon(skin);
	}
	
	@Override
	public void run() {
		Move(this.direction, this.position);
	}

	public void Locale(int x, int y) {
		this.posXatCharacter += x;
		this.posYatCharacter += y;
		this.Skinlabel.setLocation(this.posXatCharacter, this.posYatCharacter);
		this.PlayerNameLabel.setLocation(Skinlabel.getX() + adjusteNameLabel, Skinlabel.getY() - 12);
	}
	
	public void Move(char direction, int position) {
		position--; //to correct position
		
		this.Skinlabel.setIcon(SkinImages[position][1]);
		
		WaitAFeelTime(250);

		this.Skinlabel.setIcon(SkinImages[position][2]);
		
		WaitAFeelTime(250);

		this.Skinlabel.setIcon(SkinImages[position][0]);
		this.move = false;
	}
	
	public synchronized void MoveTo(char direction, int position) {
		this.move = true;
		this.direction = direction;
		this.position = position;
		
		new Thread(this).start();
	}
	
	public void Pause(boolean pause) {
		this.pause = pause;
	}
	
	//Get e Set para posicao
	public void setCoordenateX(int x) {
		this.CoordenateX = x;
	}
	
	public void setCoordenateY(int y) {
		this.CoordenateY = y;
	}
	
	public int getCoordenateX() {
		return this.CoordenateX;
	}
	
	public int getCoordenateY() {
		return this.CoordenateY;
	}
	
	//
	public int[] getPosInMainCharacter(){
		int[] pos = {this.posXatCharacter, this.posYatCharacter};
		return pos;
	}
	
	//
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
