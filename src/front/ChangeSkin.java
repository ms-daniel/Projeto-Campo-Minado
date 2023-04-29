package front;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JInternalFrame;

public class ChangeSkin extends JPanel {

	/**
	 * Create the panel.
	 */
	public ChangeSkin() {
		setLayout(null);
		
		JLabel panelCharacter = new JLabel("");
		panelCharacter.setOpaque(true);
		panelCharacter.setBackground(new Color(0, 0, 0));
		panelCharacter.setBounds(30, 150, 570, 380);
		add(panelCharacter);
		
		JLabel bakcgrouhd = new JLabel("");
		bakcgrouhd.setIcon(new ImageIcon(ChangeSkin.class.getResource("/icons/menu.jpg")));
		bakcgrouhd.setBounds(0, 0, 630, 630);
		add(bakcgrouhd);

	}
}
