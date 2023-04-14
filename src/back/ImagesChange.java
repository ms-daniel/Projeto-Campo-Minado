package back;

import java.awt.Image;

import javax.swing.ImageIcon;

public class ImagesChange {
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
	/**
	 * Metodo para redimensionar imagem
	 * 
	 * @param icon: icone de origem, pode ser obtido atraves de .getIcon()
	 * @param width: largura
	 * @param height; altura
	 * @return
	 */
	public ImageIcon Resize(ImageIcon icon, int width, int height) {
		Image image = icon.getImage();

        // Redimensiona a imagem
        Image resizedImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);

        // Cria um novo ImageIcon com a imagem redimensionada
        ImageIcon resizedImageIcon = new ImageIcon(resizedImage);
		
		return resizedImageIcon;
	}
}
