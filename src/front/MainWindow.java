package front;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;

public class MainWindow extends JFrame {
	public enum Panels {
	    MENU, PLAYGAME, CREATE, CONNECT, CHANGESKIN
	}
	
	private JPanel contentPane;

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
//		contentPane = new PlayGame();
		contentPane = new Menu(this);
//		contentPane = new terstSkin();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
	}
	
	public void ChangeTo(Panels frame) {
		switch(frame) {
			case MENU:
				
				break;
			case PLAYGAME:
				
				break;
			case CREATE:
				
				break;
			case CONNECT:
				
				break;
			case CHANGESKIN:
				
				break;
			default:
				
				break;
		}
	}

}
