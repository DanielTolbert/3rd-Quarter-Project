import imaging.ImageProcessor;
import imaging.Position;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static List<Position> positions;

    public static void main(String[] args) {
        createPositions();
        calculateImages();
        createVideo();
    }

    private static void createPositions() {
        positions = new ArrayList<>();

        positions.add(new Position(0, 0, 1, 1));

        for (int i = 0; i < 150; i++) {
            positions.add(new Position(i/149.0f, 0, 1, i+1));
        }
    }

    private static void calculateImages() {
        for (Position p : positions) {
            ImageProcessor image = new ImageProcessor("/resources/images/stad_2.png");
            image.processImage(p);
        }
    }

    private static void createVideo() {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(
                    "ffmpeg",
                    "-r", "30",
                    "-s", "904x368",
                    "-i", "../../images/output/output_image%d.png",
                    "-vcodec", "libx264",
                    "-crf", "25",
                    "-pix_fmt", "yuv420p",
                    "output_video.mp4",
                    "-y"
            );

            processBuilder.directory(new File("src/resources/videos/output").getAbsoluteFile());

            Process process = processBuilder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
