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
import java.awt.Color;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;

public class Menu extends JPanel {
	public enum Components{
		MENU, CONNECT, CREATE, CHANGESKIN
	}
	
	private ImagesChange get = new ImagesChange();
	
	private JFrame mainWindow;
	
	private Menu Menu = this;
	private JLabel Connect;
	private JLabel CreateSeason;
	private JLabel ChangeSkin;
	private JLabel ExitButton;
	private JLabel BombIcon;
	private JLabel MenuBackground;
	
	private BombMenu Bomb;
	
	/**
	 * Create the panel.
	 */
	public Menu(JFrame frame) {
		setLayout(null);
		
		Connect = new JLabel("");
		Connect.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Connect.setBorder(null);
		Connect.setBounds(67, 205, 295, 45);
		
		CreateSeason = new JLabel("");
		CreateSeason.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		CreateSeason.setBorder(null);
		CreateSeason.setBounds(67, 270, 460, 45);
		
		ChangeSkin = new JLabel("");
		ChangeSkin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		ChangeSkin.setBorder(null);
		ChangeSkin.setBounds(68, 340, 425, 45);
		
		ExitButton = new JLabel("");
		ExitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		ExitButton.setBorder(null);
		ExitButton.setBounds(68, 403, 150, 45);
		
		BombIcon = new JLabel("");
		BombIcon.setBorder(null);
		BombIcon.setBounds(0, 0, 50, 45);
		
		MenuBackground = new JLabel("");
		MenuBackground.setIcon(get.getIcon("icons/menu.jpg"));
		MenuBackground.setBounds(0, 0, 630, 630);
		
		AddComponents();
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
	
	public void ChangeComponentsTo(Components next) {
		this.removeAll();
		
		switch(next) {
		case MENU:
			AddComponents();
			break;
		case CONNECT:
			new MenuConnect().AddComponents(Menu, MenuBackground);
			break;
		case CREATE:
			
			break;
		case CHANGESKIN:
			
			break;
		default:
			
			break;
		}
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
	
	private void AddComponents() {
		Connect.setIcon(get.getIcon("icons/Conectar.png"));
		CreateSeason.setIcon(get.getIcon("icons/Criar Partida.png"));
		ChangeSkin.setIcon(get.getIcon("icons/Mudar Boneco.png"));
		BombIcon.setIcon(get.getIcon("icons/Bomb-pixel.png"));
		ExitButton.setIcon(get.getIcon("icons/Sair.png"));
		
		BombIcon.setVisible(false);
		
		add(Connect);
		add(CreateSeason);
		add(ChangeSkin);
		add(ExitButton);
		add(BombIcon);
		add(MenuBackground);
		
		Bomb = new BombMenu(this.Menu, this.BombIcon);
		
		EffectsOn();
		
		repaint();
	}

}
