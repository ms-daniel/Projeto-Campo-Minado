package front;

import javax.swing.JPanel;

import back.ImagesChange;
import back.BombMenu;

import javax.swing.JLabel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

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
	
	private JFrame mainWindow;
	
	private JPanel Menu = this;
	private JLabel Connect;
	private JLabel CreateSeason;
	private JLabel ChangeSkin;
	private JLabel ExitButton;
	private JLabel BombIcon;
	private JLabel MenuBackground;
	
	private JLabel Transition;
	
	private BombMenu Bomb;
	
	/**
	 * Create the panel.
	 */
	public Menu(JFrame frame) {
		this.mainWindow = frame;
		
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

		Bomb = new BombMenu(this.Menu, this.BombIcon);
		
		EffectsOn();
	}
	
	private void EffectsOn() {
		Connect.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(!Bomb.IsRunning()) 
					mouseEnter(Connect, 3, "ConectarSelected");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(!Bomb.IsRunning()) 
					mouseExit(Connect, "Conectar");
			}

			@Override
			public void mousePressed(MouseEvent e) {
				if(!Bomb.IsRunning())
					Bomb.Explose();
			}
		});
		
		CreateSeason.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(!Bomb.IsRunning()) 
					mouseEnter(CreateSeason, 0, "Criar PartidaSelected");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(!Bomb.IsRunning())
					mouseExit(CreateSeason, "Criar Partida");
			}

			@Override
			public void mousePressed(MouseEvent e) {
				if(!Bomb.IsRunning())
					Bomb.Explose();
			}
		});
		
		ChangeSkin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(!Bomb.IsRunning()) 
					mouseEnter(ChangeSkin, 3, "Mudar BonecoSelected");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(!Bomb.IsRunning()) 
					mouseExit(ChangeSkin, "Mudar Boneco");
			}

			@Override
			public void mousePressed(MouseEvent e) {
				if(!Bomb.IsRunning()) 
					Bomb.Explose();
				
			}
		});
		
		ExitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(!Bomb.IsRunning()) 
					mouseEnter(ExitButton, 0, "SairSelected");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(!Bomb.IsRunning()) 
					mouseExit(ExitButton, "Sair");
			}

			@Override
			public void mousePressed(MouseEvent e) {
				System.exit(ABORT);
			}
		});
	}
	
	private void mouseEnter(JLabel label, int adjustY, String nameArchieve) {
		label.setIcon(get.getIcon("icons/" + nameArchieve + ".png"));
		BombIcon.setLocation(20, label.getY()-adjustY);
		BombIcon.setVisible(true);
	}
	
	private void mouseExit(JLabel label, String nameArchieve) {
		label.setIcon(get.getIcon("icons/" + nameArchieve + ".png"));
		BombIcon.setVisible(false);
	}

}
