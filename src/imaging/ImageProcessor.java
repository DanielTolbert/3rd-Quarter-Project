package imaging;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageProcessor {

    private static final int IMAGE_WIDTH = 904;
    private static final int IMAGE_HEIGHT = 368;
    private static final int Z_SCALE = 400;
    private static final int X_SCALE = 100;
    private static final int Y_SCALE = 50;

    private BufferedImage image;

    public ImageProcessor(String filePath) {
        image = readImage(filePath);
    }

    private BufferedImage readImage(String filePath) {

        try {
            return ImageIO.read(getClass().getResource(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void processImage(Position p) {
        int cropCenterX = IMAGE_WIDTH/2 - (p.getX() * X_SCALE);
        int cropCenterY = IMAGE_HEIGHT/2 + (p.getY() * Y_SCALE);

        int cropWidth = p.getZ() * Z_SCALE;
        int cropHeight = (int) (cropWidth / ((double) IMAGE_WIDTH / IMAGE_HEIGHT));

        cropCenterX = Math.min(cropCenterX, IMAGE_WIDTH - (cropWidth/2));
        cropCenterX = Math.max(cropCenterX, 0);
        cropCenterY = Math.min(cropCenterY, IMAGE_HEIGHT - (cropHeight/2));
        cropCenterY = Math.max(cropCenterY, 0);

        System.out.println(cropCenterX + " " + cropWidth + " : " + cropCenterY + " " + cropHeight);
        System.out.println((cropCenterX - (cropWidth/2) + cropWidth) + " " + (cropCenterY - (cropHeight/2) + cropHeight));

        image = image.getSubimage(cropCenterX - (cropWidth/2), cropCenterY - (cropHeight/2), cropWidth - 1, cropHeight - 1);
        image = scaleImage(image);

        writeImage();
    }

    private BufferedImage scaleImage(BufferedImage inputImage) {
        BufferedImage scaledImage = new BufferedImage(IMAGE_WIDTH, IMAGE_HEIGHT, inputImage.getType());

        Graphics2D g2d = scaledImage.createGraphics();
        g2d.drawImage(inputImage, 0, 0, IMAGE_WIDTH, IMAGE_HEIGHT, null);
        g2d.dispose();

        return scaledImage;
    }

    private void writeImage() {
        try {
            ImageIO.write(image, "png", new File("src/resources/images/output_image.png"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

//    private void defineXPos() {
//        xPos = SCREEN_WIDTH/2 - ((IMAGE_WIDTH/2) * zoomScale);
//
//        xPos += position.getX() * transmutationScale;
//    }
//
//    private void defineYPos() {
//        yPos = SCREEN_HEIGHT/2 - ((IMAGE_HEIGHT/2) * zoomScale);
//
//        yPos += position.getY() * transmutationScale;
//    }
}
