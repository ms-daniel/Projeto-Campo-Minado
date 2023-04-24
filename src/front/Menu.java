package front;

import javax.swing.JPanel;
import javax.swing.Timer;

import back.ImagesChange;

import javax.swing.JLabel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Menu extends JPanel {

	private ImagesChange get = new ImagesChange();
	private JLabel Connect;
	private JLabel CreateSeason;
	private JLabel ChangeSkin;
	private JLabel ExitButton;
	private JLabel BombIcon;
	private boolean clicked = false;
	
	private JLabel MenuBackground;
	
	
	/**
	 * Create the panel.
	 */
	public Menu() {
		setLayout(null);
		
		Connect = new JLabel("");
		Connect.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Connect.setIcon(get.getIcon("icons/Conectar.png"));
		Connect.setBorder(null);
		Connect.setBounds(67, 205, 295, 45);
		add(Connect);
		
		CreateSeason = new JLabel("");
		CreateSeason.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		CreateSeason.setIcon(get.getIcon("icons/Criar Partida.png"));
		CreateSeason.setBorder(null);
		CreateSeason.setBounds(67, 270, 460, 45);
		add(CreateSeason);
		
		ChangeSkin = new JLabel("");
		ChangeSkin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		ChangeSkin.setIcon(get.getIcon("icons/Mudar Boneco.png"));
		ChangeSkin.setBorder(null);
		ChangeSkin.setBounds(68, 340, 425, 45);
		add(ChangeSkin);
		
		ExitButton = new JLabel("");
		ExitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		ExitButton.setIcon(get.getIcon("icons/Sair.png"));
		ExitButton.setBorder(null);
		ExitButton.setBounds(68, 403, 150, 45);
		add(ExitButton);
		
		BombIcon = new JLabel("");
		BombIcon.setIcon(get.getIcon("icons/Bomb-pixel.png"));
		BombIcon.setBorder(null);
		BombIcon.setBounds(0, 0, 50, 45);
		BombIcon.setVisible(false);
		add(BombIcon);
		
		MenuBackground = new JLabel("");
		MenuBackground.setIcon(new ImageIcon(Menu.class.getResource("/icons/menu.jpg")));
		MenuBackground.setBounds(0, 0, 630, 630);
		add(MenuBackground);

		EffectsOn();
	}
	
	private void EffectsOn() {
		Connect.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Connect.setIcon(get.getIcon("icons/ConectarSelected.png"));
				BombIcon.setLocation(20, Connect.getY()-3);
				BombIcon.setVisible(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(!clicked) {
					Connect.setIcon(get.getIcon("icons/Conectar.png"));
					BombIcon.setVisible(false);
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				clicked = true;
				
				ExploseBomb();
				
				DisableComponents();
			}
		});
		
		CreateSeason.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				CreateSeason.setIcon(get.getIcon("icons/Criar PartidaSelected.png"));
				BombIcon.setLocation(20, CreateSeason.getY());
				BombIcon.setVisible(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				CreateSeason.setIcon(get.getIcon("icons/Criar Partida.png"));
				BombIcon.setVisible(false);
			}
		});
		
		ChangeSkin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				ChangeSkin.setIcon(get.getIcon("icons/Mudar BonecoSelected.png"));
				BombIcon.setLocation(20, ChangeSkin.getY()-3);
				BombIcon.setVisible(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				ChangeSkin.setIcon(get.getIcon("icons/Mudar Boneco.png"));
				BombIcon.setVisible(false);
			}
		});
		
		ExitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				ExitButton.setIcon(get.getIcon("icons/SairSelected.png"));
				BombIcon.setLocation(20, ExitButton.getY());
				BombIcon.setVisible(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				ExitButton.setIcon(get.getIcon("icons/Sair.png"));
				BombIcon.setVisible(false);
			}
		});
	}
	
	//TO_DO finalizacao do metodo com um timer 
	private void ExploseBomb() {
		BombIcon.setVisible(true);
		BombIcon.setIcon(get.getIcon("icons/bomb-gif.gif"));
	}
	
	private void DisableComponents() {
		Component[] components = getRootPane().getComponents();
	    for (Component component : components) {
	        component.setEnabled(false);
	    }
	}
	
}
