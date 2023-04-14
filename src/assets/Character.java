package assets;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import back.ImagesChange;

public class Character extends JLabel{
	private ImagesChange get = new ImagesChange();
	
	private String name;
	private String skinLocal;
	private ImageIcon skin;
	
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
		this.skinLocal = FolderSkin;
		
		setBounds(0, 0, 182, 252);
		setHorizontalAlignment(SwingConstants.CENTER);
		setAlignmentX(Component.CENTER_ALIGNMENT);
		this.skin = get.getIcon(FolderSkin);
		setIcon(skin);
	}
	
	public void Locale(int x, int y) {
		this.posX = x;
		this.posY = y;
		setLocation(x, y);
	}
	
	public void Move() {
		String first = "lufy-1-1";
		String second = "lufy-1-2";
		String stop = "lufy-1";

        // Criação da thread
        new Thread(new Runnable() {
            @Override
            public void run() {
            	for(int i = 0; i < 100; i++) {
        			if(i%2 != 0)
        				skin = get.getIcon("character/luffy/" + first + ".png");
        			else
        				skin = get.getIcon("character/luffy/" + second + ".png");
        			
        			setIcon(skin);
        			try {
        				Thread.sleep(300);
        			} catch (InterruptedException e) {
        				// TODO Auto-generated catch block
        				e.printStackTrace();
        			}
        		}
        		
        		skin = get.getIcon("character/luffy/" + stop + ".png");
        		setIcon(skin);
            }
        }).start();
		
		
	}
	
	public void Resize(int width, int height) {
		skin = get.Resize(skin, width, height);
		setIcon(skin);
	}
	
	public int GetSkinWidth() {
		return skin.getIconWidth();
	}
	
	public int GetSkinHeight() {
		return skin.getIconHeight();
	}
}
