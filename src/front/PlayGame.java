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
	private MapMove mapsMove;
	private Thread mapThread;
	
	private JLabel LabelCharacter;
	private JLabel PlayerName;
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
		
		timer = new Timer(570, new ActionListener() {
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
		
		
		character = new Character("CARLINHOS", "character/luffy/" + "lufy-1" + ".png", LabelCharacter, PlayerName);
		character.Locale(270,235);
		character.start(); 

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
		mapT.setIcon(get.getIcon("maps/map 1/", "mapa 1", ".png"));
		add(mapT);
		
		map = new JLabel();
		map.setBounds(0, 0, 2250, 2250);
		map.setIcon(get.getIcon("maps/map 1/", "map 1", ".jpg"));
		add(map);

		mapsMove = new MapMove(map, mapT);
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
	        	character.MoveTo('R', 2);
	
	        	//move o mapa
	        	mapsMove.MoveMaps(-45, 0);
	        	
	        	timer.start();
	        	validKey = true; 
	        }else if((key == KeyEvent.VK_LEFT) ||
	        		(key == KeyEvent.VK_A)) {
	        	character.MoveTo('L', 4);
	        	
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
	        	character.MoveTo('T', 3);
	        	
	        	//move o mapa
	        	mapsMove.MoveMaps(0, +45);
	        	
	        	timer.start();
	        	validKey = true; 
	        }
	        if(validKey) {
		        mapThread = new Thread(mapsMove);
		        mapThread.start();
		    }
    	}
    }

    @Override
    public synchronized void keyReleased(KeyEvent e) {
        // Implemente o método keyReleased se necessário
    }
}
