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
	private SecundaryCharacter character2;
	private JLabel LabelCharacter2;
	private JLabel PlayerName2;
	//=========================

	private Timer timer;

	/**
	 * Create the panel.
	 */
	public CharactersPanel(MapMove maps) {
		setOpaque(false);
		setFocusable(false);
		this.mapsMove = maps;
		
		AddPlayers();
		
		TMove moveChar = new TMove(character2);
				
		/*AddOtherPlayer("la", "green-dragon", 225, 190);
		mapsMove.AddCharacterToMap(character2);
		
		AddOtherPlayer("Chocolate", "jill-valentine", 270,145);
		mapsMove.AddCharacterToMap(character2);*/
		
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
    	LabelCharacter2 = new JLabel();
    	LabelCharacter2.setBounds(230, 5, 0, 0);
		PlayerName2 = new JLabel();
		PlayerName2.setBounds(225, 5, 0, 0);
    	
    	character2 = new SecundaryCharacter(PlayerName, SkinName, LabelCharacter2, PlayerName2, null);
		character2.Locale(x, y);
		setLayout(null);
	
		add(PlayerName2);
		add(LabelCharacter2);
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
				mapsMove.AddCharacterToMap(character2);
			}
		}
	}
}
