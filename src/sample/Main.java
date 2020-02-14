package sample;

import processing.core.PApplet;
import processing.core.PImage;

public class Main extends PApplet {

    private PImage img;

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
        image(img, 0, 0, 2712, 1104);
    }
}
