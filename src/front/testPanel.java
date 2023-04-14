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
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class testPanel extends JPanel {

	private JLabel image;
	private JButton leftButton;
	private JButton rightButton;
	private String name = "lufy-";
	private String ext = ".png";
	private int atual = 1;
	
	/**
	 * Create the panel.
	 */
	public testPanel() {
		setBounds(new Rectangle(0, 0, 500, 500));
		setLayout(null);
		
		JLabel image = new JLabel();
		image.setHorizontalAlignment(SwingConstants.CENTER);
		image.setAlignmentX(Component.CENTER_ALIGNMENT);
		image.setIcon(getIcon("lufy-1.png"));
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
		leftButton.setIcon(getIcon("next-left.png"));
		
		
		rightButton = new JButton("");
		rightButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		rightButton.setContentAreaFilled(false);
		rightButton.setBorderPainted(false);
		rightButton.setFocusPainted(false);
		rightButton.setToolTipText("right");
		rightButton.setMargin(new Insets(0, 0, 0, 0));
		rightButton.setBounds(new Rectangle(324, 391, 35, 35));
		rightButton.setBorder(null);
		rightButton.setIcon(getIcon("next-right.png"));
		
		add(rightButton);
		add(image);
		add(leftButton);
		
		effects(image);
	}
	
	private void effects(JLabel image) {
		leftButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(atual > 1)
					atual--;
				else
					atual = 4;
				
				image.setIcon(getIcon(name + atual + ext));
			}
		});
		
		rightButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(atual < 4)
					atual++;
				else
					atual = 1;
			
				image.setIcon(getIcon(name + atual + ext));
			}
		});
	}
	
	private ImageIcon getIcon(String name) {
		return new ImageIcon(getClass().getClassLoader().getResource(name));
	}
}
