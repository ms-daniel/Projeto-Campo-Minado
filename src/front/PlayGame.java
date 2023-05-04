package front;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Panel;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.CardLayout;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import assets.Character;
import back.ImagesChange;
import back.MapMove;
import config.Config;

import javax.swing.JButton;
import java.awt.Insets;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayGame extends JPanel implements KeyListener {
	private ImagesChange get = new ImagesChange();
	
	private Character character;
	private JLabel LabelCharacter;
	private JLabel PlayerName;
	
	//testes
	private Character character2;
	private JLabel LabelCharacter2;
	private JLabel PlayerName2;
	//=========================
	
	private MapMove mapsMove;
	
	private int[][] walls = Config.matrizMap1;
 	
	private JLabel map;
	private JLabel mapT;
	
	private JButton leftButton;
	private JButton rightButton;
	private JButton playButton;
	
	private String name = "lufy-";
	private String ext = ".png";
	
	private Timer timer;
	
	private int atual = 1;
	private int[][] matrizMap = Config.matrizMap1;
	
	/**
	 * Create the panel.
	 */
	public PlayGame() {
		
		timer = new Timer(600, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica do processamento do evento de teclado aqui
                timer.stop();
            }
        });
		
		
		setBackground(new Color(0, 0, 0));
		setBounds(new Rectangle(0, 0, 630, 630));
		setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		setLayout(null);
		
		LabelCharacter = new JLabel();
		PlayerName = new JLabel();
		
		
		character = new Character("SKULLMONKEY", "baby", LabelCharacter, PlayerName);
		character.Locale(270,235);
		//character.start(); 

		JLabel arrowkeyslabel = new JLabel("");
		arrowkeyslabel.setToolTipText("keys");
		arrowkeyslabel.setIcon(get.getIcon("icons/", "arrowskeys", ".png"));
		arrowkeyslabel.setBounds(0, 450, 155, 100);

		//permite que seja identificada teclas pressionadas no panel
		addKeyListener(this);
		setFocusable(true); //so funciona com essa opcao ativada
		
		add(arrowkeyslabel);
		
		
		add(PlayerName);
		add(LabelCharacter);
		
		mapT = new JLabel();
		mapT.setBounds(0, 0, 2250, 2250);
//		mapT.setIcon(get.getIcon("maps/map 1/", "mapa 1", ".png"));
		mapT.setIcon(get.getIcon("maps/map 1/", "map 1 submap", ".png"));
//		add(mapT,0);
		
		
		map = new JLabel();
		map.setBounds(0, 0, 2250, 2250);
		map.setIcon(get.getIcon("maps/map 1/", "map 1", ".jpg"));
		add(map);

		mapsMove = new MapMove(map, mapT);
		//add other players
		AddOtherPlayer("Moises", "luffy", 270+90,235);
		mapsMove.AddCharacterToMap(character2);
		
		AddOtherPlayer("Law", "green-dragon", 270+180,235);
		mapsMove.AddCharacterToMap(character2);
		
		AddOtherPlayer("Chocolate", "jill-valentine", 270+90,235+90);
		mapsMove.AddCharacterToMap(character2);
		
		AddOtherPlayer("Leitinho", "claire-redfield", 270+180,235+90);
		mapsMove.AddCharacterToMap(character2);
	}
	
	@Override
    public synchronized void keyTyped(KeyEvent e) {
        // Implemente o método keyTyped se necessário
    }

    @Override
    public synchronized void keyPressed(KeyEvent e) {
    	Integer key = e.getKeyCode();
    	boolean validKey = false; 
    	
    	if(!timer.isRunning()) {
	        // Verifique se a tecla pressionada é a tecla desejada
	        if ((key == KeyEvent.VK_RIGHT) ||
	        		(key == KeyEvent.VK_D )) {
	        	character.MoveTo('R', 3);
	
	        	//move o mapa
	        	mapsMove.MoveMaps(-45, 0);
	        	
	        	timer.start();
	        	validKey = true; 
	        }else if((key == KeyEvent.VK_LEFT) ||
	        		(key == KeyEvent.VK_A)) {
	        	character.MoveTo('L', 2);
	        	
	        	//move o mapa
	        	mapsMove.MoveMaps(+45, 0);
	
	        	timer.start();
	        	validKey = true; 
	        }else if((key == KeyEvent.VK_DOWN) ||
	        		(key == KeyEvent.VK_S)) {
	        	character.MoveTo('D', 1);
	        	
	        	//move o mapa
	        	mapsMove.MoveMaps(0, -45);
	      
	        	timer.start();
	        	validKey = true; 
	        }
	        else if((key == KeyEvent.VK_UP) ||
				(key == KeyEvent.VK_W)) {
	        	character.MoveTo('T', 4);
	        	
	        	//move o mapa
	        	mapsMove.MoveMaps(0, +45);
	        	
	        	timer.start();
	        	validKey = true; 
	        }
	        if(validKey) {
		        new Thread(mapsMove).start();
		    }
	        validKey = false; 
    	}
    }

    @Override
    public synchronized void keyReleased(KeyEvent e) {
        // Implemente o método keyReleased se necessário
    }
    /**
     * Introduce another player in the map
     * @param PlayerName: player name
     * @param SkinName: name of skin used by the player
     * @param x: coordenate x for player position
     * @param y: coordenate y for player position
     */
    public void AddOtherPlayer(String PlayerName, String SkinName, int x, int y) {
    	LabelCharacter2 = new JLabel();
		PlayerName2 = new JLabel();
    	
    	character2 = new Character(PlayerName, SkinName, LabelCharacter2, PlayerName2);
		character2.Locale(x, y);
		//character2.start(); 
		
		add(PlayerName2, 2);
		add(LabelCharacter2, 3);
    }
}
