package front;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import back.ImagesChange;
import back.WaitingDialog;
import config.Config;

import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;


public class Lobby extends JPanel {
	private ImagesChange get = new ImagesChange();
	
	private Timer timer;
	private JLabel GifBlueBen;
	private JOptionPane LoadConnection;
	
	/**
	 * Create the panel.
	 */
	public Lobby() {

	}
	
	public void AddComponents(Menu Menu, JLabel Background) {
		WaitingDialog dialog = new WaitingDialog(Menu);
		
		Background.setEnabled(false);
		Menu.add(Background);
		
		Menu.repaint();
		
		dialog.setVisible(true);
	}

}
