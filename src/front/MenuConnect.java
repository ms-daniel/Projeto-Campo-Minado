package front;

import javax.swing.JPanel;

import back.ImagesChange;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class MenuConnect extends JPanel {
	private ImagesChange get = new ImagesChange();
	
	private JLabel MenuBackground;
	
	/**
	 * Create the panel.
	 */
	public MenuConnect() {
		setLayout(null);
		
		MenuBackground = new JLabel("");
		MenuBackground.setIcon(get.getIcon("icons/menu.jpg"));
		MenuBackground.setBounds(0, 0, 630, 630);
		add(MenuBackground);

	}
}
