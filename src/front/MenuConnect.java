package front;


import back.ImagesChange;
import front.Menu.Components;

import javax.swing.JLabel;
import java.awt.Cursor;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuConnect{
	private ImagesChange get = new ImagesChange();
	
	private Menu Menu;
	
	
	private JTextField IpText;
	private JTextField PortText;
	
	private JLabel PortLabel;
	private JLabel ipLabel;
	private JLabel IpPortLabel;
	private JLabel Entrar;
	private JLabel Voltar;
	
	public void AddComponents(Menu Menu, JLabel Background) {
		this.Menu = Menu;
		
		ipLabel = new JLabel("");
		ipLabel.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		ipLabel.setVisible(false);
		ipLabel.setLabelFor(IpText);
		ipLabel.setIcon(get.getIcon("icons/ipret.png"));
		ipLabel.setBounds(130, 246, 370, 55);
		
		
		PortLabel = new JLabel("");
		PortLabel.setVisible(false);
		PortLabel.setIcon(get.getIcon("icons/portret.png"));
		PortLabel.setLabelFor(PortText);
		PortLabel.setBounds(130, 346, 200, 55);
		
		
		PortText = new JTextField();
		PortText.setFont(new Font("Unispace", Font.PLAIN, 24));
		PortText.setHorizontalAlignment(SwingConstants.LEFT);
		PortText.setColumns(10);
		PortText.setBorder(new EmptyBorder(0, 0, 0, 0));
		PortText.setBounds(141, 353, 172, 42);
		
		
		IpText = new JTextField();
		IpText.setFont(new Font("Unispace", Font.PLAIN, 24));
		IpText.setBorder(new EmptyBorder(0, 0, 0, 0));
		IpText.setHorizontalAlignment(SwingConstants.LEFT);
		IpText.setBounds(141, 253, 350, 42);
		IpText.setColumns(10);
		
	
		Entrar = new JLabel("");
		Entrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Entrar.setIcon(get.getIcon("icons/entrar.png"));
		Entrar.setBounds(204, 414, 220, 45);
		
		Voltar = new JLabel("");
		Voltar.setIcon(get.getIcon("icons/voltar.png"));
		Voltar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Voltar.setBounds(207, 465, 220, 45);
		
		
		IpPortLabel = new JLabel("");
		IpPortLabel.setIcon(get.getIcon("icons/menuConnect.png"));
		IpPortLabel.setBounds(132, 202, 400, 200);
		
		Menu.add(Entrar);
		Menu.add(Voltar);
		Menu.add(PortText);
		Menu.add(PortLabel);
		Menu.add(IpText);
		Menu.add(ipLabel);
		Menu.add(IpPortLabel);
		Menu.add(Background);
		
		Menu.repaint();
		
		Effects();
	}
	
	private void Effects() {
		PortText.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				PortLabel.setVisible(true);
			}
			
			@Override
			public void focusLost(FocusEvent e) {
				PortLabel.setVisible(false);
			}
		});
		
		IpText.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				ipLabel.setVisible(true);
			}
			
			@Override
			public void focusLost(FocusEvent e) {
				ipLabel.setVisible(false);
			}
		});
		
		Entrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Entrar.setIcon(get.getIcon("icons/entrarSelected.png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Entrar.setIcon(get.getIcon("icons/entrar.png"));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				//TO DO
			}
		});
		
		Voltar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Voltar.setIcon(get.getIcon("icons/voltarSelected.png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Voltar.setIcon(get.getIcon("icons/voltar.png"));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Menu.ChangeComponentsTo(Components.MENU);
			}
		});
	}
}
