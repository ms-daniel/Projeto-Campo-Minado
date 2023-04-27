package back;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import front.Menu;

public class BombMenu extends Thread{
	private ImagesChange get = new ImagesChange();
	ArrayList<BufferedImage> images;
	
	private Timer timer;
	
	private Thread thread;
	
	private Menu menu;
	private JLabel Bomb;
	
	private int cont = 15;
	
	public BombMenu(Menu Menu, JLabel Bomb) {
		try {
			images = get.GetAllImagesGif("icons/bomb-gif.gif");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		
		timer = new Timer(300, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	timer.stop();
            }
        });
		
		this.Bomb = Bomb;
		this.menu = Menu;
	}
	
	@Override
	public synchronized void run() {
		while(cont < 30) {
			ImageIcon icon = new ImageIcon(images.get(cont));
			Bomb.setIcon(icon);
        	cont++;
        	
        	try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		cont = 15;
		
		EnableComponents();
		this.interrupt();
		
		try {
			this.join();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.menu.ChangeComponentsTo(Menu.Components.CONNECT);
		}
	}
	
	public synchronized void Explose() {
		ImageIcon icon = new ImageIcon(images.get(cont));
		Bomb.setIcon(icon);
		Bomb.setVisible(true);
		
		
		DisableComponents();
		
		timer.start(); //inicia timer
		thread = new Thread(this);
		thread.start();
	}
	
	public boolean IsRunning() {
		return timer.isRunning();
	}
	
	private void DisableComponents() {
		Component[] components = menu.getRootPane().getComponents();
	    for (Component component : components) {
	        component.setEnabled(false);
	    }
	}
	
	private void EnableComponents() {
		Component[] components = menu.getRootPane().getComponents();
	    for (Component component : components) {
	        component.setEnabled(true);
	    }
	    
	    this.Bomb.setIcon(get.getIcon("icons/Bomb-pixel.png"));
	}
}
