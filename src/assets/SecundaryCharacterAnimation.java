package assets;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class SecundaryCharacterAnimation extends Thread{
	private ImageIcon[][] SkinImages;
	private JLabel SkinLabel;
	
	public SecundaryCharacterAnimation(JLabel label, ImageIcon[][] Images ) {
		this.SkinLabel = label;
		this.SkinImages = Images;
	}
}
