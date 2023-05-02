package front;

import javax.swing.JPanel;

import back.ImagesChange;
import front.Menu.Components;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JInternalFrame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;

public class ChangeSkin extends JPanel {
	private ImagesChange get = new ImagesChange();
	
	private JLabel bakcgrouhd;
	private JLabel ChangeSkinButton;
	private JLabel BackButton;
	private JLabel panelCharacter;
	private Menu Menu;
	
	/**
	 * Create the panel.
	 */
	public ChangeSkin() {
		setLayout(null);

		
		BackButton = new JLabel("");
		BackButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		BackButton.setIcon(get.getIcon("icons/voltar.png"));
		BackButton.setBounds(73, 540, 220, 42);
		add(BackButton);
		
		ChangeSkinButton = new JLabel("");
		ChangeSkinButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		ChangeSkinButton.setIcon(get.getIcon("icons/mudar.png"));
		ChangeSkinButton.setBounds(350, 540, 200, 42);
		add(ChangeSkinButton);
		
		panelCharacter = new JLabel("");
		panelCharacter.setOpaque(true);
		panelCharacter.setBackground(new Color(0, 0, 0));
		panelCharacter.setBounds(30, 150, 570, 380);
		add(panelCharacter);
		
		bakcgrouhd = new JLabel("");
		bakcgrouhd.setIcon(get.getIcon("icons/menu.jpg"));
		bakcgrouhd.setBounds(0, 0, 630, 630);
		add(bakcgrouhd);

		Events();
	}
	
	public void AddComponents(Menu Menu, JLabel Background) {
		this.Menu = Menu;
		
		BackButton = new JLabel("");
		BackButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		BackButton.setIcon(get.getIcon("icons/voltar.png"));
		BackButton.setBounds(73, 540, 220, 42);
		
		ChangeSkinButton = new JLabel("");
		ChangeSkinButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		ChangeSkinButton.setIcon(get.getIcon("icons/mudar.png"));
		ChangeSkinButton.setBounds(350, 540, 200, 42);
		
		panelCharacter = new JLabel("");
		panelCharacter.setOpaque(true);
		panelCharacter.setBackground(new Color(0, 0, 0));
		panelCharacter.setBounds(30, 150, 570, 380);
		
//		bakcgrouhd = new JLabel("");
//		bakcgrouhd.setIcon(get.getIcon("icons/menu.jpg"));
//		bakcgrouhd.setBounds(0, 0, 630, 630);
		
		Menu.add(BackButton);
		Menu.add(ChangeSkinButton);
		Menu.add(panelCharacter);
//		Menu.add(bakcgrouhd);
		Menu.add(Background);
		
		Menu.repaint();

		Events();
	}
	
	private void Events() {
		BackButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				BackButton.setIcon(get.getIcon("icons/voltarSelected.png"));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				BackButton.setIcon(get.getIcon("icons/voltar.png"));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				Menu.ChangeComponentsTo(Components.MENU);
			}
		});
		
		ChangeSkinButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				ChangeSkinButton.setIcon(get.getIcon("icons/mudarSelected.png"));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				ChangeSkinButton.setIcon(get.getIcon("icons/mudar.png"));
			}
		});
	}
}
