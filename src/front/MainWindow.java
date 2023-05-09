
package front;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import back.MapMove;

import java.awt.Toolkit;

public class MainWindow extends JFrame {
	public enum Panels {
	    MENU, PLAYGAME, CREATE, CONNECT, CHANGESKIN
	}
	
	private JPanel contentPane;
	private MapMove mapsMove = new MapMove();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setIconImage(new ImageIcon("resources/icons/icon-game.png").getImage());
		setTitle("Campo Minado");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 646, 669);
		setResizable(false);
		setLocationRelativeTo(null);
		
		contentPane = new PlayGame(mapsMove);
//		contentPane = new Menu(this);
//		contentPane = new terstSkin();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
//		contentPane.setBounds(0, 0, 630, 630);
//		add(contentPane);
//		add(contentPane2);
	}
}
