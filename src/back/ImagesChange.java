package back;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

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
		return new ImageIcon("resources/" + (FolderName));
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
	
	public ArrayList<BufferedImage> GetAllImagesGif(String Folder) throws IOException{
		File gifFile = new File("resources/" + Folder);
        ImageInputStream stream = ImageIO.createImageInputStream(gifFile);
        Iterator<ImageReader> readers = ImageIO.getImageReaders(stream);
        
        if (!readers.hasNext()) {
            throw new RuntimeException("No reader for this format");
        }
        
        ImageReader reader = readers.next();
        reader.setInput(stream);
        
        int numImages = reader.getNumImages(true);
        ArrayList<BufferedImage> images = new ArrayList<>();
        
        for (int i = 0; i < numImages; i++) {
            BufferedImage image = reader.read(i);
            images.add(image);
        }
        
        return images;
	}
}
