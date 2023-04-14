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

import assets.getIcon;

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
	private getIcon get = new getIcon();
	
	private JLabel image = new JLabel();
	private JButton leftButton;
	private JButton rightButton;
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
		
		image.setHorizontalAlignment(SwingConstants.CENTER);
		image.setAlignmentX(Component.CENTER_ALIGNMENT);
		image.setIcon(get.getIcon("lufy-1", "character/", ".png"));
		image.setBounds(213, 128, 182, 252);
		
		
		leftButton = new JButton("");
		leftButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		leftButton.setContentAreaFilled(false);
		leftButton.setBorderPainted(false);
		leftButton.setFocusPainted(false);
		leftButton.setBorder(null);
		leftButton.setBounds(new Rectangle(253, 391, 35, 35));
		leftButton.setMargin(new Insets(0, 0, 0, 0));
		leftButton.setToolTipText("left");
		leftButton.setIcon(get.getIcon("next-left", "character/", ".png"));
		
		
		rightButton = new JButton("");
		rightButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		rightButton.setContentAreaFilled(false);
		rightButton.setBorderPainted(false);
		rightButton.setFocusPainted(false);
		rightButton.setToolTipText("right");
		rightButton.setMargin(new Insets(0, 0, 0, 0));
		rightButton.setBounds(new Rectangle(324, 391, 35, 35));
		rightButton.setBorder(null);
		rightButton.setIcon(get.getIcon("next-right", "character/", ".png"));
		
		add(rightButton);
		add(image);
		add(leftButton);
		
		//permite que seja identificada teclas pressionadas no panel
		addKeyListener(this);
		setFocusable(true); //so funciona com essa opcao ativada
		//
		
		effects();
	}
	
	private void effects() {
		leftButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(atual > 1)
					atual--;
				else
					atual = 4;
				
				image.setIcon(get.getIcon(name + atual, "character/", ".png"));
				requestFocus(); //da o foco ao painel/janela dps q pressionar o botao
			}
		});
		
		rightButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(atual < 4)
					atual++;
				else
					atual = 1;
			
				image.setIcon(get.getIcon(name + atual, "character/", ".png"));
				requestFocus(); //da o foco ao painel/janela dps q pressionar o botao
			}
		});
	}
	
	@Override
    public void keyTyped(KeyEvent e) {
        // Implemente o método keyTyped se necessário
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Verifique se a tecla pressionada é a tecla desejada
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            // Abra a caixa de diálogo quando a tecla F1 for pressionada
        	if(atual < 4)
				atual++;
			else
				atual = 1;
		
			image.setIcon(get.getIcon(name + atual, "character/", ".png"));
        }
        
        else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
        	if(atual > 1)
				atual--;
			else
				atual = 4;
			
			image.setIcon(get.getIcon(name + atual, "character/", ".png"));
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Implemente o método keyReleased se necessário
    }
}
