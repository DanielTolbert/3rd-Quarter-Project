import peasy.PeasyCam;
import processing.core.PApplet;

public class Graph extends PApplet {

    private PeasyCam peasyCam;

    public void settings () {
        size(500, 500, P3D);

    }

    public void setup() {
        peasyCam = new PeasyCam(this, 500);
        peasyCam.setMinimumDistance(200);
        peasyCam.setMaximumDistance(500);
    }

    public void draw() {
        int blue = (color (0,0,255));
        int yellow = (color (255,255,0));

        rotateX(-.001f);
        rotateY(-.001f);
        background(0);
        translate(0,0,0);


        pushMatrix();
        fill(200);
        rect(0,0,125,125);

        // asse x
        stroke(0,100,0);
        line(0, 0, 0, 150, 0, 0);
        fill(0,100,0);
        text("X Axis",140,-5,0);





        stroke(255,0,0);
        line(0, 0, 0, 0, 150, 0);

        pushMatrix();
        rotate(-HALF_PI);
        fill(255,0,0);
        text("Y Axis",-160,-5,0);
        popMatrix();








        stroke(0,0,255);
        line(0, 0, 0, 0, 0, 150);
        pushMatrix();
        rotateY(-HALF_PI);
        fill(0,0,255);
        text("Z Axis",140,-5,0);
        popMatrix();



        drawGraphLinesVertical(20);
        drawGraphLinesHorizontal(20);


//        translate(10, 10, 10);
//        noStroke();
//        lights();
//        fill(0,255,0);
//        sphere(2);

        createDataPoint(10, 10, 10);
        createDataPoint(25, 10, 50);
        createDataPoint(25, 30, 10);
        createDataPoint(75, 10, 50);


//        translate(25, 10, 50);
//        noStroke();
//        lights();
//        fill(blue);
//        sphere(2);
//
//        translate(25, 30, 10);
//        noStroke();
//        lights();
//        fill(255,0,0);
//        sphere(2);
//
//        translate(75, 10, 50);
//        noStroke();
//        lights();
//        fill(yellow);
//        sphere(5);

        translate(0,0,50);
        popMatrix();
    }

    private void drawGraphLinesVertical(int lines) {
        stroke(200);
        for (int i = 10; i < (lines * 10); i += 10) {
            line(i, 0, 0, i, 0, 250);
            line(0, i, 0, 0, i, 250);
        }

    }

    private void drawGraphLinesHorizontal(int lines) {
        //Horizontal Lines Y Axis
        stroke(200);
//        line(0, 0, 10, 0, 100, 10);
//        line(0, 0, 20, 0, 100, 20);
//        line(0, 0, 30, 0, 100, 30);
//        line(0, 0, 40, 0, 100, 40);
//        line(0, 0, 50, 0, 100, 50);

        //Horizontal Lines X Axis
        stroke(200);
//        line(0, 0, 10, 100, 0, 10);
//        line(0, 0, 20, 100, 0, 20);
//        line(0, 0, 30, 100, 0, 30);
//        line(0, 0, 40, 100, 0, 40);
//        line(0, 0, 50, 100, 0, 50);

        for (int i = 10; i < (lines * 10); i += 10) {
            line(0, 0, i, 0, 200, i);
            line(0, 0, i, 200, 0, i);
        }

    }

    private void createDataPoint(int x, int y, int z) {
        translate(x, y, z);
        noStroke();
        lights();
        fill(255, 255, 255);
        sphere(2);
    }

    private void createDataPoint(int x, int y, int z, int pointRadius) {
        translate(x, y, z);
        noStroke();
        lights();
        fill(255, 255, 255);
        sphere(pointRadius);
    }

    public static void main(String[] args) {
        PApplet.main("Graph");
    }

}
