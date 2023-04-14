package back;

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
	public ImageIcon getIcon (String FolderName, String name, String extension){
		return new ImageIcon(getClass().getClassLoader().getResource(FolderName + name + extension));
	}
	/**
	 * Abrir uma imagem como icon usando a string toda junta (local + nome do arquivo + extensao)
	 * @param FolderName: local + nome do arquivo + extensao
	 * @return
	 */
	public ImageIcon getIcon (String FolderName){
		return new ImageIcon(getClass().getClassLoader().getResource(FolderName));
	}
}
