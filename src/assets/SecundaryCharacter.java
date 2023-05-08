package assets;

import javax.swing.JLabel;

public class SecundaryCharacter extends Character{
	public SecundaryCharacter(String name, String SkinName, JLabel SkinLabel, JLabel PlayerName) {
		super(name, SkinName, SkinLabel, PlayerName);
	}
	
	@Override
	public void run() {
		// TODO Criar movimentação feita pelo servidor
	}
	
	/**
	 * increment or decrement player location
	 * @param x: increment or decrement x
	 * @param y: increment or decrement y
	 */
	public void IncrementLocale(int x, int y) {
		this.Skinlabel.setLocation(Skinlabel.getX() + x, Skinlabel.getY() + y);
		this.PlayerNameLabel.setLocation(Skinlabel.getX() + adjusteNameLabel, Skinlabel.getY() - 12);
	}
}
