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
		return new ImageIcon("resources/" + FolderName + name + extension);
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
	
	/**
	 * Separa um gif em varias imagens separadas(frames)
	 * @param Folder: a pasta + nome do arquivo do gif a ser lido
	 * @return um array list de imagens (BufferedImage)
	 * @throws IOException
	 */
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
	
	public BufferedImage[][] GetSkinImages(String SkinName){
		File inputFile = new File("resources/character/" + SkinName + ".png");
		BufferedImage inputImage;
		BufferedImage[][] outputImages = null;
		
		try {
			inputImage = ImageIO.read(inputFile);
			
			int rows = 4; // número de linhas para dividir a imagem
			int cols = 3; // número de colunas para dividir a imagem
			int partWidth = inputImage.getWidth() / cols; // largura de cada parte
			int partHeight = inputImage.getHeight() / rows; // altura de cada parte

			outputImages = new BufferedImage[rows][cols];
			int count = 0;
			for (int y = 0; y < rows; y++) {
				for (int x = 0; x < cols; x++) {
					int xPos = x * partWidth;
		            int yPos = y * partHeight;
		            BufferedImage chunk = inputImage.getSubimage(xPos, yPos, partWidth, partHeight);
		            outputImages[y][x] = chunk;
		            count++;
		        }
		    }

		} catch (IOException e) {
			e.printStackTrace();
		}

		return outputImages;
	}
}
