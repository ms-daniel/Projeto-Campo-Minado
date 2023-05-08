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
import assets.SecundaryCharacter;
import back.ImagesChange;
import back.MapMove;
import config.Config;
import server.ServerInterface;

import javax.swing.JButton;
import java.awt.Insets;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Arrays;
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
	private SecundaryCharacter character2;
	private JLabel LabelCharacter2;
	private JLabel PlayerName2;
	//=========================
	
	private MapMove mapsMove;
	
	/**
	 * y: linhas, x: colunas
	 */
	private int[][] Walls = Config.matrizMap1;
	
	private int[] PlayerPosition = {0,0};
 	
	private JLabel map;
	private JLabel mapT;

	private Timer timer;

	private int[][] matrizMap = Config.matrizMap1;
	
	/**
	 * Create the panel.
	 */
	public PlayGame() {
		
		timer = new Timer(550, new ActionListener() {
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
		character.Locale(270, 235);

		JLabel arrowkeyslabel = new JLabel("");
		arrowkeyslabel.setToolTipText("keys");
		arrowkeyslabel.setIcon(get.getIcon("icons/", "arrowskeys", ".png"));
		arrowkeyslabel.setBounds(0, 450, 155, 100);

		//permite que seja identificada teclas pressionadas no panel
		addKeyListener(this);
		setFocusable(true); //so funciona com essa opcao ativada
		
		add(arrowkeyslabel);
		
		
		add(PlayerName, 0);
		add(LabelCharacter);
		
		mapT = new JLabel();
		mapT.setBounds(Config.mapPositionX, Config.mapPositionY, 2250, 2250);
		mapT.setIcon(get.getIcon("maps/map 1/", "mapa 1", ".png"));
//		mapT.setIcon(get.getIcon("maps/map 1/", "map 1 submap", ".png"));
		add(mapT,3);
		
		
		map = new JLabel();
		map.setBounds(Config.mapPositionX, Config.mapPositionY, 2250, 2250);
		map.setIcon(get.getIcon("maps/map 1/map 1.jpg"));
		add(map);
		
		character.setCoordenateX(MapToArray(map.getX()));
		character.setCoordenateY(MapToArray(map.getY()));
		
		PlayerPosition[0] = character.getCoordenateY();
		PlayerPosition[1] = character.getCoordenateX();
		
		mapsMove = new MapMove(map, mapT);
		
//		ServerInterface.startServer(Config.port, "10.11.157.251");
//		ServerInterface.connectPlayer("10.11.157.251", Config.port);
//		
//		try {
//			System.out.println(ServerInterface.sandNameAndSkin("SKULLMONKEY", "baby"));
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		//add other players
		AddOtherPlayer("MJ", "luffy", 270+45,235-45);
		mapsMove.AddCharacterToMap(character2);
				
		AddOtherPlayer("la", "green-dragon", 270-45,235-45);
		mapsMove.AddCharacterToMap(character2);
		
		AddOtherPlayer("Chocolate", "jill-valentine", 270,235-90);
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
    	boolean validMove = false;
    	
    	
    	if(!timer.isRunning()) {
	        // Verifique se a tecla pressionada é a tecla desejada
	        if ((key == KeyEvent.VK_RIGHT) ||
	        	(key == KeyEvent.VK_D )) {
	        	character.MoveTo('R', 3);
	        	
	        	if(Walls[ PlayerPosition[0] ][ PlayerPosition[1]+1 ] != 1) {
	        		character.setCoordenateX(1);
		        	PlayerPosition[1]++;
		        	
		        	//move o mapa
		        	mapsMove.MoveMaps(-45, 0);
		        	
		        	validMove = true;
	        	}
	        	
	        	validKey = true; 
	        }else if((key == KeyEvent.VK_LEFT) ||
	        		(key == KeyEvent.VK_A)) {
	        	character.MoveTo('L', 2);
	        	
	        	if(Walls[ PlayerPosition[0] ][ PlayerPosition[1]-1 ] != 1) {
		        	character.setCoordenateX(-1);
		        	PlayerPosition[1]--;
	        	
		        	//move o mapa
		        	mapsMove.MoveMaps(+45, 0);
		        	
		        	validMove = true;
		        }
	
	        	validKey = true; 
	        }else if((key == KeyEvent.VK_DOWN) ||
	        		(key == KeyEvent.VK_S)) {
	        	character.MoveTo('D', 1);
	        	
	        	if(Walls[ PlayerPosition[0]+1 ][ PlayerPosition[1] ] != 1) {
		        	character.setCoordenateY(1);
		        	PlayerPosition[0]++;
		        		        	
		        	//move o mapa
		        	mapsMove.MoveMaps(0, -45);
		        	
		        	validMove = true;
	        	}
	      
	        	validKey = true; 
	        }
	        else if((key == KeyEvent.VK_UP) ||
	        	   (key == KeyEvent.VK_W)) {
	        	character.MoveTo('T', 4);
	        	if(Walls[ PlayerPosition[0]-1 ][ PlayerPosition[1] ] != 1) {
		        	character.setCoordenateY(-1);
		        	PlayerPosition[0]--;
		        	
		        	//move o mapa
		        	mapsMove.MoveMaps(0, +45);
		        	
		        	validMove = true;
	        	}
	        	validKey = true; 
	        }
	        
	        //checks if a valid key was pressed
	        //to start the moves in map and timer
	        if(validKey) {
	        	if(validMove)
	        		new Thread(mapsMove).start();
	        	
		        timer.start();
		    }
//	        System.out.println("x: " + PlayerPosition[0] + "\ny: " + PlayerPosition[1] + "\n");
	        
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
    	
    	character2 = new SecundaryCharacter(PlayerName, SkinName, LabelCharacter2, PlayerName2);
		character2.Locale(x, y);
		//character2.start(); 
		
		add(PlayerName2, 2);
		add(LabelCharacter2, 3);
    }
    
    /**
     * Calcula o x/y da matriz para o mapa
     * especifica
     * @param xy: coordenada de x/y
     * @return: posiçao do mapa para aquele valor de x/y
     */
    private int ArrayToMap(int xy) {
    	return (((xy-1)*45) - 270)*-1;
    }
    
    //Xm = ((-x + 270)/45) + 1
    /**
     * Calcula a posiçao do mapa na matriz
     * @param xy: posiçao x/y que deseja ser recebida
     * @return: posiçao equivalente na matriz
     */
    private int MapToArray(int xy) {
    	return ((-xy + 270)/45);
    }
}
