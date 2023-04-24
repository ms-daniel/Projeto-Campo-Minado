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
	private JLabel Conectar;
	private JLabel CriarPartida;
	private JLabel MenuBackground;
	
	/**
	 * Create the panel.
	 */
	public Menu() {
		setLayout(null);
		
		Conectar = new JLabel("");
		Conectar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Conectar.setIcon(get.getIcon("icons/Conectar.png"));
		Conectar.setBorder(null);
		Conectar.setBounds(67, 205, 300, 45);
		add(Conectar);
		
		CriarPartida = new JLabel("");
		CriarPartida.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		CriarPartida.setIcon(get.getIcon("icons/Criar Partida.png"));
		CriarPartida.setBorder(null);
		CriarPartida.setBounds(67, 270, 465, 45);
		add(CriarPartida);
		
		MenuBackground = new JLabel("");
		MenuBackground.setIcon(new ImageIcon(Menu.class.getResource("/icons/menureflexc.jpg")));
		MenuBackground.setBounds(0, 0, 630, 630);
		//add(MenuBackground);

	}
	
	private void EffectsOn() {
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
		
		CriarPartida.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				CriarPartida.setIcon(get.getIcon("icons/Criar PartidaSelected.png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				CriarPartida.setIcon(get.getIcon("icons/Criar Partida.png"));
			}
		});
	}
}
