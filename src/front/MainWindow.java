
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
	    MENU, PLAYGAME
	}
	
	private JPanel contentPane = new JPanel();
	private MapMove mapsMove = new MapMove();
	private Menu Menu;
	private PlayGame play;

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
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainWindow.class.getResource("/icons/icon-game.png")));
		setTitle("Campo Minado");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 646, 669);
		setResizable(false);
		setLocationRelativeTo(null);
		this.Menu = new Menu(this);

		ChangeTo(Panels.MENU);
	}
	
	public void ChangeTo(Panels panels) {
		
		switch(panels) {
			case MENU:
				contentPane = this.Menu;
				break;
			case PLAYGAME:
				this.play = new PlayGame(mapsMove, Menu.getSkinName(), Menu.getPlayerName());
				
				contentPane = this.play;
				
				break;
			default:
				break;
		}
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.requestFocus();
		this.repaint();
		this.revalidate();
	}
}
