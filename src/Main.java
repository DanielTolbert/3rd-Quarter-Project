import imaging.ImageProcessor;
import imaging.Position;

public class Main {

    public static void main(String[] args) {
        ImageProcessor image = new ImageProcessor("/resources/images/stad_2.png");
        image.processImage(new Position(1, 1, 1));
    }
}
