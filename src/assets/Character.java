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
	
	protected String PlayerName;
	protected ImageIcon[][] SkinImages;
	protected String SkinName;
	protected ImageIcon skin;
	
	private boolean pause = false;
	private boolean move = false;
	protected int position;
	protected int adjusteNameLabel = 0;
	
	protected int posXatCharacter = 0;
	protected int posYatCharacter = 0;
	
	protected int CoordenateX = 0;
	protected int CoordenateY = 0;
	
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
		MoveAnimation(this.position);
	}

	public void Locale(int x, int y) {
		this.posXatCharacter += x;
		this.posYatCharacter += y;
		this.Skinlabel.setLocation(this.posXatCharacter, this.posYatCharacter);
		this.PlayerNameLabel.setLocation(Skinlabel.getX() + adjusteNameLabel, Skinlabel.getY() - 12);
	}
	
	protected void MoveAnimation(int position) {
		position--; //to correct position
		
		this.Skinlabel.setIcon(SkinImages[position][1]);
		
		WaitAFeelTime(250);

		this.Skinlabel.setIcon(SkinImages[position][2]);
		
		WaitAFeelTime(250);

		this.Skinlabel.setIcon(SkinImages[position][0]);
		this.move = false;
	}
	
	public synchronized void MoveTo(int position) {
		this.move = true;
		this.position = position;

		new Thread(this).start();
	}
	
	public void Pause(boolean pause) {
		this.pause = pause;
	}
	
	//Get e Set
	public void setPlayerName(String name) {
		this.PlayerName = name.toUpperCase();
		
		this.adjusteNameLabel = 23 - (this.PlayerName.length()*4);
		
		this.PlayerNameLabel.setText(this.PlayerName);
		this.PlayerNameLabel.setBounds(0, 0, this.PlayerName.length()*8, 9);
	}
	
	public String getPlayerName() {
		return this.PlayerName;
	}
	
	public void setSkin(String SkinName) {
		this.SkinName = SkinName;
		
		SkinImages = ImageToIcon(get.GetSkinImages(SkinName));
		
		this.skin = SkinImages[0][0];
		this.Skinlabel.setIcon(skin);
	}
	
	public void incrementCoordenateX(int x) {
		this.CoordenateX += x;
	}
	
	public void incrementCoordenateY(int y) {
		this.CoordenateY += y;
	}
	
	public int getCoordenateX() {
		return this.CoordenateX;
	}
	
	public int getCoordenateY() {
		return this.CoordenateY;
	}
	
	//
	public int[] getPosInMainCharacter(){
		int[] pos = new int[2];
		pos[0] = this.posXatCharacter;
		pos[1] = this.posYatCharacter;
		
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
	protected void WaitAFeelTime(long time){
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void Move() {
		// TODO Auto-generated method stub
		
	}
	
}
