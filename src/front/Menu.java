package front;

import javax.swing.JPanel;

import back.ImagesChange;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;

public class Menu extends JPanel {

	private ImagesChange get = new ImagesChange();
	
	/**
	 * Create the panel.
	 */
	public Menu() {
		setLayout(null);
		
		JLabel Conectar = new JLabel("New label");
		Conectar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Conectar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Conectar.setIcon(get.getIcon("icons/ConectarSelected.png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Conectar.setIcon(get.getIcon("icons/Conectar.png"));
			}
		});
		Conectar.setIcon(get.getIcon("icons/Conectar.png"));
		Conectar.setBorder(null);
		Conectar.setBounds(67, 205, 300, 45);
		add(Conectar);
		
		JLabel MenuBackground = new JLabel("");
		MenuBackground.setIcon(get.getIcon("icons/menu.jpg"));
		MenuBackground.setBounds(0, 0, 630, 630);
		add(MenuBackground);

	}
}
