package graphing;

import peasy.PeasyCam;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Graph extends PApplet {

    private PeasyCam peasyCam;
    private static ArrayList<Point> points = new ArrayList<>();
    private int maxDistance = 20000;
    private int minDistance = 100;

    public static void setData(Point...pointsCollection) {
        points.addAll(Arrays.asList(pointsCollection));

    }

    public static void main(String[] args) {
        PApplet.main("graphing.Graph");
    }

    public void settings () {
        size(500, 500, P3D);
    }

    public void setup() {
        peasyCam = new PeasyCam(this, 500);
        peasyCam.setMinimumDistance(minDistance);
        peasyCam.setMaximumDistance(maxDistance);

        int pointColors = color(255, 0, 0);
        createDataPoint(10, 10, 10, 5, pointColors);
        createDataPoint(25, 10, 50, 5, pointColors);
        createDataPoint(25, 30, 10, 5, pointColors);
        createDataPoint(75, 10, 50, 5, pointColors);
        createDataPoint(100, 5, 75, 5, pointColors);
        createDataPoint(68, 2, 10, 5, pointColors);
        createDataPoint(47, 87, 30, 5, pointColors);
    }


    public void draw() {

        rotateX(-.001f);
        rotateY(-.001f);
        background(0);
        translate(0,0,0);


        pushMatrix();
        fill(200);
        rect(0,0, maxDistance, maxDistance);

        // asse x
        stroke(0,100,0);
        line(0, 0, 0, maxDistance, 0, 0);
        fill(0,100,0);
        text("X Axis",140,-5,0);





        stroke(255,0,0);
        line(0, 0, 0, 0, maxDistance, 0);

        pushMatrix();
        rotate(-HALF_PI);
        fill(255,0,0);
        text("Y Axis",-160,-5,0);
        popMatrix();



        keyPressed();




        stroke(0,0,255);
        line(0, 0, 0, 0, 0, maxDistance);
        pushMatrix();
        rotateY(-HALF_PI);
        fill(0,0,255);
        text("Z Axis",140,-5,0);
        popMatrix();



        drawGraphLinesVertical(maxDistance);
        drawGraphLinesHorizontal(maxDistance);
//        drawLines(points);

//        translate(10, 10, 10);
//        noStroke();
//        lights();
//        fill(0,255,0);
//        sphere(2);
        DataCollector.startTimer();
        setData(new Point(DataCollector.getRunTimeSeconds(),
                DataCollector.getRunTimeSeconds(),
                DataCollector.getRunTimeSeconds(),
                DataCollector.getRunTimeSeconds()));
        System.out.println(DataCollector.getRunTimeSeconds());
        drawDataPoints(points);

        fill(color(255, 0, 0));
        line(25, 30, 10, 75, 10, 50);

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
            line(i, 0, 0, i, 0, maxDistance);
            line(0, i, 0, 0, i, maxDistance);
        }
    }

    public void keyPressed() {
        if (key != 'f') {
            peasyCam.setYawRotationMode();
        } else {
            peasyCam.setFreeRotationMode();
        }
    }

    private void drawGraphLinesHorizontal(int lines) {
        //Horizontal Lines Y Axis
        stroke(200);

        //Horizontal Lines X Axis
        stroke(200);


        for (int i = 10; i < (lines * 10); i += 10) {
            line(0, 0, i, 0, maxDistance, i);
            line(0, 0, i, maxDistance, 0, i);
        }

    }

    private void createDataPoint(int x, int y, int z, int pointRadius, int color) {
        points.add(new Point(x, y, z, pointRadius, color));
    }

    private void drawDataPoints(List<Point> points) {
        for (Point point : points) {
            System.out.println("Creating point " + point.toString());
            sphere(point.getPointRadius());
            translate(point.getX(), point.getY(), point.getZ());
            noStroke();
            lights();
            fill(point.getColor());
        }
    }

    private void drawLines(ArrayList<Point> points) {

        for (int i = 1; i < points.stream().sorted().collect(Collectors.toList()).size(); i++) {
            Point prev = points.get( i - 1 );
            Point curr = points.get( i );
            line(prev.getX(), prev.getY(), prev.getZ(), curr.getX(), curr.getY(), curr.getZ());
        }

    }
}
