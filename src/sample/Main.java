package sample;

import javafx.scene.input.ScrollEvent;
import processing.core.PApplet;
import processing.core.PImage;

public class Main extends PApplet {

    private PImage img;
    private static final int IMAGE_WIDTH = 904;
    private static final int IMAGE_HEIGHT = 368;

    private float scale = 3;
    private int xPos = 0;
    private int yPos = 0;

    public static void main(String[] args) {
        PApplet.main("sample.Main");
    }

    public void settings() {
        size(1920, 1080);
    }

    public void setup() {
        background(0);
        img = loadImage("stad_2.png");
    }

    public void draw() {
        image(img, xPos, yPos, IMAGE_WIDTH * scale, IMAGE_HEIGHT * scale);
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
