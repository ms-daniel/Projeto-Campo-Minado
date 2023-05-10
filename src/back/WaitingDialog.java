package back;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import front.Menu;
import front.Menu.Components;
import server.ServerInterface;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JTextField;

public class WaitingDialog extends JDialog {
	private ImagesChange get = new ImagesChange();
	
	private final JPanel contentPanel = new JPanel();
	private String PlayerName;
	private String skin;
	
	private WaitingDialog me = this;

	/**
	 * Create the dialog.
	 */
	public WaitingDialog(Menu Menu) {
		StringBuilder PN = Menu.getPlayerNameSB();
		PN.delete(0, PN.length());
		PN.append(JOptionPane.showInputDialog(this, "Nome do jogador: "));
		
		PlayerName = PN.toString();
		
		setTitle("Aguardando");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 434, 228);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
		contentPanel.setLayout(null);
		
		JLabel TextLabel = new JLabel("Aguardando outros jogadores!");
		TextLabel.setHorizontalAlignment(SwingConstants.CENTER);
		TextLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		TextLabel.setBounds(10, 0, 414, 56);
		contentPanel.add(TextLabel);
		
		JLabel NumPlayersLabel = new JLabel("Conectados: ");
		NumPlayersLabel.setBorder(null);
		NumPlayersLabel.setBounds(33, 67, 115, 23);
		
		JLabel GifBlueBen = new JLabel("");
		GifBlueBen.setIcon(get.getIcon("character/blue-pen2.gif"));
		GifBlueBen.setBounds(375, 152, 49, 65);
		contentPanel.add(GifBlueBen);
		
		contentPanel.add(NumPlayersLabel);
		
		JLabel playerNameLabel = new JLabel("Nome: " + PlayerName);
		playerNameLabel.setBounds(33, 95, 100, 14);
		contentPanel.add(playerNameLabel);
		
		JLabel playerSkinLabel = new JLabel("Skin: " + Menu.getSkinName());
		playerSkinLabel.setBounds(33, 111, 100, 14);
		contentPanel.add(playerSkinLabel);
		
		JLabel PlayerCountLabel = new JLabel("0");
		PlayerCountLabel.setBounds(105, 71, 46, 14);
		contentPanel.add(PlayerCountLabel);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 228, 434, 33);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
//				JButton okButton = new JButton("Iniciar");
//				okButton.setActionCommand("OK");
//				okButton.setEnabled(false);
//				buttonPane.add(okButton);
//				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				skin = Menu.getSkinName();
				
				System.out.println("TEste");
				
				waitPlayes(Menu);
				cancelButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						me.dispose();
						//TODO cancelar conexao com servidor
						try {
							ServerInterface.closeConnection();
							ServerInterface.closeServer();
						}catch(IOException e1) {
							
						}
						Menu.ChangeComponentsTo(Components.MENU);
						
					}
				});
			}
		}
	}

	private void waitPlayes(Menu Menu){
		Thread thread = new Thread(new Runnable() {
			public void run() {
				String bombs;
				try {
					bombs = ServerInterface.sandNameAndSkin(PlayerName, skin);
					System.out.println(bombs);
					me.dispose();
					Menu.ChangeComponentsTo(Components.PLAYGAME);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		thread.start();
	}
}
