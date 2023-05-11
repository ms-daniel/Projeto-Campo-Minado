package front;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import assets.Character;
import assets.SecundaryCharacter;
import assets.TMove;
import back.ImagesChange;
import back.MapMove;
import config.Config;
import server.ServerInterface;
import server.TPlays;

public class CharactersPanel extends JPanel {
	private ImagesChange get = new ImagesChange();
	
	private MapMove mapsMove;
	//testes
	private SecundaryCharacter character;
	private JLabel LabelCharacter;
	private JLabel PlayerNameLabel;
	//=========================

	private Timer timer;

	/**
	 * Create the panel.
	 */
	public CharactersPanel(MapMove maps) {
		setOpaque(false);
		
		JLabel mapT = new JLabel();
		mapT.setBounds(Config.mapPositionX, Config.mapPositionY, 2250, 2250);
		mapT.setIcon(get.getIcon("maps/map 1/", "map 1 submap", ".png"));
		add(mapT,0);
		
		this.mapsMove = maps;
		maps.setSecondMap(mapT);

		AddPlayers();
		
		TMove moveChar = new TMove(character);
		
		moveChar.start();
	}

	/**
     * Introduce another player in the map
     * @param PlayerName: player name
     * @param SkinName: name of skin used by the player
     * @param x: coordenate x for player position
     * @param y: coordenate y for player position
     */
    public void AddOtherPlayer(String PlayerName, String SkinName, int x, int y) {
    	LabelCharacter = new JLabel();
		PlayerNameLabel = new JLabel();
    	
    	character = new SecundaryCharacter(PlayerName, SkinName, LabelCharacter, PlayerNameLabel);
		character.Locale(x, y);
		setLayout(null);
	
		add(PlayerNameLabel);
		add(LabelCharacter);
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

	private void AddPlayers(){
		String[] allInfos = ServerInterface.infos.split(":");
		for (String playeString : allInfos) {
			String[] infos = playeString.split(";");
			/*
			 * infos[0] == nome do jogador
			 * infos[1] == skin do jogador
			 * infos[2] == posicao x
			 * infos[3] == posicao y
			 */
			if(!(ServerInterface.playerName.equals(infos[0]))){
				AddOtherPlayer(infos[0], infos[1], Integer.parseInt(infos[2]), Integer.parseInt(infos[3]));
				mapsMove.AddCharacterToMap(character);
			}
		}
	}
}
