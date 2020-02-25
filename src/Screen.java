import processing.core.PApplet;
import processing.core.PImage;

import java.util.List;

public class Screen extends PApplet {

    private PImage img;
    private static final int IMAGE_WIDTH = 904;
    private static final int IMAGE_HEIGHT = 368;
    private static final int SCREEN_WIDTH = 1920;
    private static final int SCREEN_HEIGHT = 1080;

    private float scale = 3;
    private float xPos;
    private float yPos;

    private List<Point> points;

    public static void main(String[] args) {
        PApplet.main("Screen");
    }

    public void settings() {
        size(SCREEN_WIDTH, SCREEN_HEIGHT);
    }

    public void setup() {
        points = DataParser.getPoints();
        background(0);
        img = loadImage("stad_2.png");
    }

    public void draw() {
        clear();
        xPos = SCREEN_WIDTH/2 - ((IMAGE_WIDTH/2) * scale);
        yPos = SCREEN_HEIGHT/2 - ((IMAGE_HEIGHT/2) * scale);
        updatePosition();
        image(img, xPos, yPos, IMAGE_WIDTH * scale, IMAGE_HEIGHT * scale);
    }

    private void updatePosition() {

    }

    public void keyPressed() {
        if (key == CODED) {
            switch (keyCode) {
                case LEFT: xPos++; break;
                case RIGHT: xPos--; break;
                case UP: yPos--; break;
                case DOWN: yPos++; break;
            }
        }
        else {
            switch (key) {
                case 'w': scale += 0.01; break;
                case 's': scale -= 0.01; break;
            }
        }
    }
}
