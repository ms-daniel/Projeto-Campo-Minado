package back;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class BombMenu extends Thread{
	private ImagesChange get = new ImagesChange();
	private Timer timer;
	
	private JPanel Menu;
	private JLabel Bomb;
	
	public BombMenu(JPanel Menu, JLabel Bomb) {
		timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica do processamento do evento de teclado aqui
                timer.stop();
            }
        });
		
		this.Bomb = Bomb;
		this.Menu = Menu;
	}
	
	@Override
	public synchronized void run() {
		int cont = 1;
		while(timer.isRunning()) {
			System.out.println("Dan " + cont);
			cont++;
		}
		
		EnableComponents();
		this.interrupt();
		
		try {
			this.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public synchronized void Explose() {
		Bomb.setVisible(true);
		Bomb.setIcon(get.getIcon("icons/bomb-gif.gif"));
		
		DisableComponents();
		
		timer.start(); //inicia timer
		new Thread(this).start();
	}
	
	public boolean IsRunning() {
		return timer.isRunning();
	}
	
	private void DisableComponents() {
		Component[] components = Menu.getRootPane().getComponents();
	    for (Component component : components) {
	        component.setEnabled(false);
	    }
	}
	
	private void EnableComponents() {
		Component[] components = Menu.getRootPane().getComponents();
	    for (Component component : components) {
	        component.setEnabled(true);
	    }
	    
	    this.Bomb.setIcon(get.getIcon("icons/Bomb-pixel.png"));
	}
}
