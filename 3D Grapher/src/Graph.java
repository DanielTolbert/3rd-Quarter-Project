import peasy.PeasyCam;
import processing.core.PApplet;
import processing.core.*;
import processing.opengl.PGraphics3D;

import java.awt.*;

public class Graph extends PApplet {

    private PeasyCam peasyCam;

    public void settings () {
        size(500, 500, P3D);

    }

    public void setup() {
        peasyCam = new PeasyCam(this, 500);
        peasyCam.setMinimumDistance(100);
        peasyCam.setMaximumDistance(500);
    }

    public void draw() {
        int blue = (color (0,0,255));
        int yellow = (color (255,255,0));

        rotateX(-.001f);
        rotateY(-.001f);
        background(255);
        translate(0,0,0);


        pushMatrix();
        fill(200);
        rect(0,0,125,125);

        // asse x
        stroke(0,100,0);
        line(0, 0, 0, 150, 0, 0);
        fill(0,100,0);
        text("X Axis",140,-5,0);

        stroke(200);
        line(0, 0, 10, 100, 0, 10);
        line(0, 0, 20, 100, 0, 20);
        line(0, 0, 30, 100, 0, 30);
        line(0, 0, 40, 100, 0, 40);
        line(0, 0, 50, 100, 0, 50);


        stroke(255,0,0);
        line(0, 0, 0, 0, 150, 0);

        pushMatrix();
        rotate(-HALF_PI);
        fill(255,0,0);
        text("Y Axis",-160,-5,0);
        popMatrix();



        stroke(200);
        line(0, 0, 10, 0, 100, 10);
        line(0, 0, 20, 0, 100, 20);
        line(0, 0, 30, 0, 100, 30);
        line(0, 0, 40, 0, 100, 40);
        line(0, 0, 50, 0, 100, 50);





        stroke(0,0,255);
        line(0, 0, 0, 0, 0, 150);
        pushMatrix();
        rotateY(-HALF_PI);
        fill(0,0,255);
        text("Z Axis",140,-5,0);
        popMatrix();



        stroke(200);
        line(10, 0, 0, 10, 0, 100);
        line(20, 0, 0, 20, 0, 100);
        line(30, 0, 0, 30, 0, 100);
        line(40, 0, 0, 40, 0, 100);
        line(50, 0, 0, 50, 0, 100);
        line(0, 10, 0, 0, 10, 100);
        line(0, 20, 0, 0, 20, 100);
        line(0, 30, 0, 0, 30, 100);
        line(0, 40, 0, 0, 40, 100);
        line(0, 50, 0, 0, 50, 100);


        translate(10, 10, 10);
        noStroke();
        lights();
        fill(0,255,0);
        sphere(5);


        translate(25, 10, 50);
        noStroke();
        lights();
        fill(blue);
        sphere(5);

        translate(25, 30, 10);
        noStroke();
        lights();
        fill(255,0,0);
        sphere(5);

        translate(75, 10, 50);
        noStroke();
        lights();
        fill(yellow);
        sphere(5);

        translate(0,0,50);
        popMatrix();
        printCamera();
    }

    public static void main(String[] args) {
        PApplet.main("Graph");
    }

}
