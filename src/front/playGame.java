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
import javax.swing.BoxLayout;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import assets.Character;
import back.ImagesChange;
import back.MapMove;

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

public class playGame extends JPanel implements KeyListener {
	private ImagesChange get = new ImagesChange();
	
	private Character character;
	private MapMove mapsMove;
	private Thread mapThread;
	
	private JLabel labelCharacter;
	private JLabel map;
	private JLabel mapT;
	
	private JButton leftButton;
	private JButton rightButton;
	private JButton playButton;
	
	private String name = "lufy-";
	private String ext = ".png";
	
	private Timer timer;
	
	private int atual = 1;
	
	/**
	 * Create the panel.
	 */
	public playGame() {
		
		timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica do processamento do evento de teclado aqui
                timer.stop();
            }
        });
		
		
		setBackground(new Color(0, 0, 0));
		setBounds(new Rectangle(0, 0, 600, 600));
		setLayout(null);
		
		labelCharacter = new JLabel();
		
		character = new Character("Luffy", "character/luffy/" + "lufy-1" + ".png", labelCharacter);
		character.Locale(213, 128);
		character.start(); 

		JLabel arrowkeyslabel = new JLabel("");
		arrowkeyslabel.setToolTipText("keys");
		arrowkeyslabel.setIcon(get.getIcon("icons/", "arrowskeys", ".png"));
		arrowkeyslabel.setBounds(0, 450, 155, 100);

		
		//permite que seja identificada teclas pressionadas no panel
		addKeyListener(this);
		setFocusable(true); //so funciona com essa opcao ativada
		
		add(arrowkeyslabel);
		
		mapT = new JLabel();
		mapT.setBounds(10, -1782, 3000, 3000);
		mapT.setIcon(get.getIcon("maps/map 1/", "map 1 submap", ".png"));
		add(mapT);
		
		add(labelCharacter);
		
		map = new JLabel();
		map.setBounds(10, -1782, 3000, 3000);
		map.setIcon(get.getIcon("maps/map 1/", "map 1", ".jpg"));
		add(map);

		mapsMove = new MapMove(map, mapT);
		//mapThread = new Thread(mapsMove);
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
