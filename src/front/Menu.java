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
	private JLabel MenuBackground;
	
	private Timer timer;
	
	private boolean clicked = false;
	
	
	
	
	/**
	 * Create the panel.
	 */
	public Menu() {
		timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica do processamento do evento de teclado aqui
                timer.stop();
            }
        });
		
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
				if(!timer.isRunning()) {
					Connect.setIcon(get.getIcon("icons/ConectarSelected.png"));
					BombIcon.setLocation(20, Connect.getY()-3);
					BombIcon.setVisible(true);
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(!timer.isRunning()) {
					Connect.setIcon(get.getIcon("icons/Conectar.png"));
					BombIcon.setVisible(false);
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				if(!timer.isRunning()) {
					ExploseBomb();
					System.out.println("Car");
					DisableComponents();
					timer.start();
				}
			}
		});
		
		CreateSeason.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(!timer.isRunning()) {
					CreateSeason.setIcon(get.getIcon("icons/Criar PartidaSelected.png"));
					BombIcon.setLocation(20, CreateSeason.getY());
					BombIcon.setVisible(true);
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(!timer.isRunning()) {
					CreateSeason.setIcon(get.getIcon("icons/Criar Partida.png"));
					BombIcon.setVisible(false);
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				if(!timer.isRunning()) {
					ExploseBomb();
					DisableComponents();
					timer.start();
				}
			}
		});
		
		ChangeSkin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(!timer.isRunning()) {
					ChangeSkin.setIcon(get.getIcon("icons/Mudar BonecoSelected.png"));
					BombIcon.setLocation(20, ChangeSkin.getY()-3);
					BombIcon.setVisible(true);
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(!timer.isRunning()) {
					ChangeSkin.setIcon(get.getIcon("icons/Mudar Boneco.png"));
					BombIcon.setVisible(false);
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				if(!timer.isRunning()) {
					ExploseBomb();
					DisableComponents();
					timer.start();
				}
			}
		});
		
		ExitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(!timer.isRunning()) {
					ExitButton.setIcon(get.getIcon("icons/SairSelected.png"));
					BombIcon.setLocation(20, ExitButton.getY());
					BombIcon.setVisible(true);
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(!timer.isRunning()) {
					ExitButton.setIcon(get.getIcon("icons/Sair.png"));
					BombIcon.setVisible(false);
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				if(!timer.isRunning()) {
					ExploseBomb();
					DisableComponents();
					timer.start();
				}
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
