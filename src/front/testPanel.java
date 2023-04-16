package front;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Panel;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.CardLayout;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.SwingConstants;

import assets.Character;
import back.ImagesChange;

import javax.swing.JButton;
import java.awt.Insets;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class testPanel extends JPanel implements KeyListener {
	private ImagesChange get = new ImagesChange();
	
	private Character character;
	private JLabel labelCharacter;
	private JButton leftButton;
	private JButton rightButton;
	private JButton playButton;
	private String name = "lufy-";
	private String ext = ".png";
	private int atual = 1;
	
	/**
	 * Create the panel.
	 */
	public testPanel() {
		
		setBackground(new Color(223, 223, 223));
		setBounds(new Rectangle(0, 0, 500, 500));
		setLayout(null);
		
		labelCharacter = new JLabel();
		
		character = new Character("Luffy", "character/luffy/" + "lufy-1" + ".png", labelCharacter);
		character.Locale(213, 128);
		character.start(); 

		JLabel arrowkeyslabel = new JLabel("");
		arrowkeyslabel.setToolTipText("keys");
		arrowkeyslabel.setIcon(get.getIcon("icons/", "arrowskeys", ".png"));
		arrowkeyslabel.setBounds(0, 409, 205, 137);
		
		add(labelCharacter);

		
		//permite que seja identificada teclas pressionadas no panel
		addKeyListener(this);
		setFocusable(true); //so funciona com essa opcao ativada
		
		add(arrowkeyslabel);

	}
	
	@Override
    public void keyTyped(KeyEvent e) {
        // Implemente o método keyTyped se necessário
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Verifique se a tecla pressionada é a tecla desejada
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
        	character.MoveTo('R', 2);
        	
        }else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
        	character.MoveTo('L', 4);

        }else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
        	character.MoveTo('D', 1);
      
        }
        else if(e.getKeyCode() == KeyEvent.VK_UP) {
        	character.MoveTo('T', 3);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Implemente o método keyReleased se necessário
    }
}
