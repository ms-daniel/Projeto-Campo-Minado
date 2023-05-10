package front;

import javax.swing.JPanel;

import back.ImagesChange;
import front.Menu.Components;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JInternalFrame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Enumeration;
import java.awt.Cursor;
import java.awt.Rectangle;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import java.awt.Insets;

public class ChangeSkin extends JPanel {
	private ImagesChange get = new ImagesChange();
	
	private JLabel bakcgrouhd;
	private JLabel ChangeSkinButton;
	private JLabel BackButton;
	private JLabel panelCharacter;
	private Menu Menu;
	private JLabel BabyLabel;
	private JRadioButton BabyRadio;
	private JRadioButton ClaireRadio;
	private JLabel ClaireLabel;
	private JRadioButton GreenDRadio;
	private JLabel GreenDLabel;
	private JRadioButton JillRadio;
	private JLabel JillLabel;
	private JRadioButton LuffyRadio;
	private JLabel LuffyLabel;
	private JRadioButton BluePenRadio;
	private JLabel BluePenLabel;
	
	private StringBuilder SkinName;
	
	private ButtonGroup GroupRadio;
	private JRadioButton[] RadioList = new JRadioButton[6];
	
	public void AddComponents(Menu Menu, JLabel Background, StringBuilder SkinName) {
		this.Menu = Menu;
		this.SkinName = SkinName;
		
		GroupRadio = new ButtonGroup();
		
		BabyRadio = new JRadioButton("Baby");
		BabyRadio.setToolTipText("baby");
		BabyRadio.setHorizontalAlignment(SwingConstants.CENTER);
		BabyRadio.setHorizontalTextPosition(SwingConstants.RIGHT);
//		BabyRadio.setOpaque(false);
		BabyRadio.setBounds(42, 177, 95, 23);
		
		
		BabyLabel = new JLabel("");
		BabyLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		BabyLabel.setHorizontalAlignment(SwingConstants.CENTER);
		BabyLabel.setBackground(new Color(169, 169, 169));
		BabyLabel.setOpaque(true);
		BabyLabel.setIcon(get.getIcon("character/baby-perfil.png"));
		BabyLabel.setBounds(42, 177, 95, 127);
		
		
		BackButton = new JLabel("");
		BackButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		BackButton.setIcon(get.getIcon("icons/voltar.png"));
		BackButton.setBounds(73, 550, 220, 42);
		
		ChangeSkinButton = new JLabel("");
		ChangeSkinButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		ChangeSkinButton.setIcon(get.getIcon("icons/mudar.png"));
		ChangeSkinButton.setBounds(350, 550, 200, 42);
		
		panelCharacter = new JLabel("");
		panelCharacter.setIcon(get.getIcon("/icons/painelChangeSkin.png"));
		panelCharacter.setBackground(new Color(0, 0, 0));
		panelCharacter.setBounds(12, 150, 606, 404);
		
		ClaireRadio = new JRadioButton("Claire Redfield");
		ClaireRadio.setBorder(null);
		ClaireRadio.setToolTipText("claire-redfield");
		ClaireRadio.setMargin(new Insets(0, 0, 0, 0));
//		ClaireRadio.setOpaque(false);
		ClaireRadio.setHorizontalTextPosition(SwingConstants.RIGHT);
		ClaireRadio.setHorizontalAlignment(SwingConstants.CENTER);
		ClaireRadio.setBounds(156, 177, 95, 23);
		
		
		GreenDRadio = new JRadioButton("Green Dragon");
		GreenDRadio.setToolTipText("green-dragon");
		GreenDRadio.setBorder(null);
		GreenDRadio.setMargin(new Insets(0, 0, 0, 0));
//		GreenDRadio.setOpaque(false);
		GreenDRadio.setHorizontalTextPosition(SwingConstants.RIGHT);
		GreenDRadio.setHorizontalAlignment(SwingConstants.CENTER);
		GreenDRadio.setBounds(270, 177, 95, 23);
		
		
		GreenDLabel = new JLabel("");
		GreenDLabel.setIcon(get.getIcon("character/green-perfil.png"));
		GreenDLabel.setOpaque(true);
		GreenDLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		GreenDLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GreenDLabel.setBackground(new Color(169, 169, 169));
		GreenDLabel.setBounds(270, 177, 95, 127);
		
		
		ClaireLabel = new JLabel("");
		ClaireLabel.setIcon(get.getIcon("character/claire-perfil.png"));
		ClaireLabel.setOpaque(true);
		ClaireLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		ClaireLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ClaireLabel.setBackground(new Color(169, 169, 169));
		ClaireLabel.setBounds(156, 177, 95, 127);
		
		JillRadio = new JRadioButton("Jill Valentine");
//		JillRadio.setOpaque(false);
		JillRadio.setToolTipText("jill-valentine");
		JillRadio.setMargin(new Insets(0, 0, 0, 0));
		JillRadio.setHorizontalTextPosition(SwingConstants.RIGHT);
		JillRadio.setHorizontalAlignment(SwingConstants.CENTER);
		JillRadio.setBorder(null);
		JillRadio.setBounds(384, 177, 95, 23);
		
		
		LuffyRadio = new JRadioButton("M D Luffy");
//		LuffyRadio.setOpaque(false);
		LuffyRadio.setToolTipText("luffy");
		LuffyRadio.setMargin(new Insets(0, 0, 0, 0));
		LuffyRadio.setHorizontalTextPosition(SwingConstants.RIGHT);
		LuffyRadio.setHorizontalAlignment(SwingConstants.CENTER);
		LuffyRadio.setBorder(null);
		LuffyRadio.setBounds(498, 177, 95, 23);
		
		LuffyLabel = new JLabel("");
		LuffyLabel.setIcon(get.getIcon("character/luffy-perfil.png"));
		LuffyLabel.setOpaque(true);
		LuffyLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		LuffyLabel.setHorizontalAlignment(SwingConstants.CENTER);
		LuffyLabel.setBackground(new Color(169, 169, 169));
		LuffyLabel.setBounds(498, 177, 95, 127);
		
		
		JillLabel = new JLabel("");
		JillLabel.setIcon(get.getIcon("character/jill-perfil.png"));
		JillLabel.setOpaque(true);
		JillLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		JillLabel.setHorizontalAlignment(SwingConstants.CENTER);
		JillLabel.setBackground(new Color(169, 169, 169));
		JillLabel.setBounds(384, 177, 95, 127);
		
		BluePenRadio = new JRadioButton("Blue Pen");
//		BluePenRadio.setOpaque(false);
		BluePenRadio.setToolTipText("blue-pen");
		BluePenRadio.setMargin(new Insets(0, 0, 0, 0));
		BluePenRadio.setHorizontalTextPosition(SwingConstants.RIGHT);
		BluePenRadio.setHorizontalAlignment(SwingConstants.CENTER);
		BluePenRadio.setBorder(null);
		BluePenRadio.setBounds(42, 315, 95, 23);
		
		
		BluePenLabel = new JLabel("");
		BluePenLabel.setIcon(get.getIcon("character/blue-perfil.png"));
		BluePenLabel.setOpaque(true);
		BluePenLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		BluePenLabel.setHorizontalAlignment(SwingConstants.CENTER);
		BluePenLabel.setBackground(new Color(169, 169, 169));
		BluePenLabel.setBounds(42, 315, 95, 127);
		
		RadioList[0] = BabyRadio;
		RadioList[1] = BluePenRadio;
		RadioList[2] = LuffyRadio;
		RadioList[3] = JillRadio;
		RadioList[4] = GreenDRadio;
		RadioList[5] = ClaireRadio;
		
		GroupRadio.add(BabyRadio);
		GroupRadio.add(BluePenRadio);
		GroupRadio.add(LuffyRadio);
		GroupRadio.add(JillRadio);
		GroupRadio.add(GreenDRadio);
		GroupRadio.add(ClaireRadio);
		
		VerifySkin();
		
		Menu.add(BluePenRadio);
		Menu.add(LuffyRadio);
		Menu.add(JillRadio);
		Menu.add(GreenDRadio);
		Menu.add(ClaireRadio);
		Menu.add(BabyRadio);
		
		Menu.add(LuffyLabel);
		Menu.add(JillLabel);
		Menu.add(ClaireLabel);
		Menu.add(GreenDLabel);
		Menu.add(BabyLabel);
		Menu.add(BluePenLabel);
		
		Menu.add(BackButton);
		Menu.add(ChangeSkinButton);
		Menu.add(panelCharacter);
		Menu.add(Background);
		
		Menu.repaint();

		Events();
	}
	
	private void Events() {
		BackButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				BackButton.setIcon(get.getIcon("icons/voltarSelected.png"));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				BackButton.setIcon(get.getIcon("icons/voltar.png"));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				Menu.ChangeComponentsTo(Components.MENU);
			}
		});
		
		ChangeSkinButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				ChangeSkinButton.setIcon(get.getIcon("icons/mudarSelected.png"));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				ChangeSkinButton.setIcon(get.getIcon("icons/mudar.png"));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				SkinName.delete(0, SkinName.length());
				SkinName.append(VerifySelected());
				
				System.out.println("Nome novo: " + SkinName);
				
				JOptionPane.showMessageDialog(null, "Skin selecionada!","Skin", JOptionPane.INFORMATION_MESSAGE );
				//Menu.ChangeComponentsTo(Components.MENU);
			}
		});
	}
	
	private void VerifySkin() {
		Enumeration<AbstractButton> radios = GroupRadio.getElements();
		
		
		while(radios.hasMoreElements()) {
			JRadioButton radioButton = (JRadioButton) radios.nextElement();
			if(radioButton.getToolTipText().equals(this.SkinName)) {
				radioButton.setSelected(true);
				GroupRadio.setSelected(radioButton.getModel(), true);
				break;
			}
		}
	}
	
	private StringBuilder VerifySelected() {
		ButtonModel radios = GroupRadio.getSelection();
		
		for(int i = 0; i < RadioList.length; i++) {
			if(RadioList[i].getModel() == radios)
				return new StringBuilder(RadioList[i].getToolTipText());
		}
		
		return null;
	}
}
