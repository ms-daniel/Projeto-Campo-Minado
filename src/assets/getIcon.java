package assets;

import javax.swing.ImageIcon;

public class getIcon {
	/**
	 * Fazer o carregamendo de uma imagem como icone
	 * 
	 * @param name: nome do arquivo
	 * @param FolderName: em qual pasta esta
	 * @param extension: abreviacao da extensao do arquivo (.jpg, .png)
	 * @return
	 */
	public ImageIcon getIcon (String name, String FolderName, String extension){
		return new ImageIcon(getClass().getClassLoader().getResource(FolderName + name + extension));
	}
}
