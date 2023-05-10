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
import java.util.Arrays;
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
import javax.swing.border.BevelBorder;
import javax.swing.SwingConstants;

public class Menu extends JPanel {
	public enum Components{
		MENU, CONNECT, CREATE, CHANGESKIN
	}

	private ImagesChange get = new ImagesChange();
	
	private JFrame mainWindow;
	
	private Menu Menu = this;
	private JLabel Connect;
	private JLabel CreateSeasonButton;
	private JLabel ChangeSkinButton;
	private JLabel ExitButton;
	private JLabel BombIcon;
	private JLabel MenuBackground;
	
	private BombMenu Bomb;
	
	private StringBuilder SkinName = new StringBuilder("baby");
	private String PLayerName = "";
	
	private MenuConnect MenuConnect;
	private ChangeSkin ChangeSkin;
	private CreateSeason CreateSeason;
	private JLabel SkinLabel;
	private JLabel SkinTextLabel;
	
	/**
	 * Create the panel.
	 */
	public Menu(JFrame frame) {
		setLayout(null);
		
		MenuConnect = new MenuConnect();
		ChangeSkin = new ChangeSkin();
		CreateSeason = new CreateSeason();
		
		Connect = new JLabel("");
		Connect.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Connect.setBorder(null);
		Connect.setBounds(67, 205, 295, 45);
		
		CreateSeasonButton = new JLabel("");
		CreateSeasonButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		CreateSeasonButton.setBorder(null);
		CreateSeasonButton.setBounds(67, 270, 460, 45);
		
		ChangeSkinButton = new JLabel("");
		ChangeSkinButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		ChangeSkinButton.setBorder(null);
		ChangeSkinButton.setBounds(68, 340, 425, 45);
		
		ExitButton = new JLabel("");
		ExitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		ExitButton.setBorder(null);
		ExitButton.setBounds(68, 403, 150, 45);
		
		BombIcon = new JLabel("");
		BombIcon.setBorder(null);
		BombIcon.setBounds(0, 0, 50, 45);
		
		MenuBackground = new JLabel("");
		MenuBackground.setBounds(0, 0, 630, 630);
		
		SkinLabel = new JLabel("");
		SkinLabel.setBounds(530, 237, 95, 127);
		
		SkinTextLabel = new JLabel("Minha Skin");
		SkinTextLabel.setHorizontalAlignment(SwingConstants.CENTER);
		SkinTextLabel.setOpaque(true);
		SkinTextLabel.setBounds(530, 362, 95, 16);
		
		AddComponents();
	}
	
	private void Events() {
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
					Bomb.Explose(1);
			}
		});
		
		CreateSeasonButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(!Bomb.IsRunning()) 
					mouseEnter(CreateSeasonButton, 0, "Criar PartidaSelected");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(!Bomb.IsRunning())
					mouseExit(CreateSeasonButton, "Criar Partida");
			}

			@Override
			public void mousePressed(MouseEvent e) {
				if(!Bomb.IsRunning())
					Bomb.Explose(2);
			}
		});
		
		ChangeSkinButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(!Bomb.IsRunning()) 
					mouseEnter(ChangeSkinButton, 3, "Mudar BonecoSelected");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(!Bomb.IsRunning()) 
					mouseExit(ChangeSkinButton, "Mudar Boneco");
			}

			@Override
			public void mousePressed(MouseEvent e) {
				if(!Bomb.IsRunning()) 
					Bomb.Explose(3);
				
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
		add(MenuBackground);
		
		switch(next) {
		case MENU:
			AddComponents();
			break;
			
		case CONNECT:
			MenuConnect.AddComponents(Menu, MenuBackground);
			break;
			
		case CREATE:
			CreateSeason.AddComponents(Menu, MenuBackground);
			break;
			
		case CHANGESKIN:
			ChangeSkin.AddComponents(Menu, MenuBackground, this.SkinName);
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
		SkinLabel.setIcon(get.getIcon("character/" + SkinLoad() + ".png"));
		Connect.setIcon(get.getIcon("icons/Conectar.png"));
		CreateSeasonButton.setIcon(get.getIcon("icons/Criar Partida.png"));
		ChangeSkinButton.setIcon(get.getIcon("icons/Mudar Boneco.png"));
		BombIcon.setIcon(get.getIcon("icons/Bomb-pixel.png"));
		ExitButton.setIcon(get.getIcon("icons/Sair.png"));
		MenuBackground.setIcon(get.getIcon("icons/Menu.jpg"));
		
		BombIcon.setVisible(false);
		
		
		add(SkinTextLabel);
		
		add(SkinLabel);
		add(Connect);
		add(CreateSeasonButton);
		add(ChangeSkinButton);
		add(ExitButton);
		add(BombIcon);
		add(MenuBackground);
		
		Bomb = new BombMenu(this.Menu, this.BombIcon);
		
		Events();
		
		repaint();
		revalidate();
	}
	
	private String SkinLoad() {
		String[] name = new String(SkinName).split("-");
		
		System.out.println(Arrays.toString( name));
		return name[0] + "-perfil";
	}

}
