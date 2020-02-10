package sample;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Point3D;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Box;
import javafx.scene.shape.Sphere;
import javafx.scene.shape.Cylinder;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    private static final double SCENE_WIDTH = 1400;
    private static final double SCENE_HEIGHT = 800;

    private double anchorX, anchorY;
    private double anchorAngleX = 0;
    private double anchorAngleY = 0;
    private final DoubleProperty angleX = new SimpleDoubleProperty(0);
    private final DoubleProperty angleY = new SimpleDoubleProperty(0);

    private SmartGroup root;
    private Scene scene;
    private Box object;
    private Rectangle virtualScreen;
    private Sphere virtualCamera;
    private List<Cylinder> perspectiveLines;

    // input values:
    private static Point3D cameraPosition = new Point3D(-50, 20, 0); // x:[-200, 200], y:[-150, 150], z:[-100, 100]

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        createObjects();
        createPerspectiveLines();
        createIntersects();
        createScene();



        handleMouseControl(primaryStage);

        primaryStage.setTitle("Sample Render");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void createScene() {
        root = new SmartGroup();
        root.getChildren().addAll(object, virtualScreen, virtualCamera);
        root.getChildren().addAll(perspectiveLines);

        Camera camera = new PerspectiveCamera();
        scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        scene.setFill(Color.SILVER);
        scene.setCamera(camera);
    }

    private void createObjects() {
        object = new Box(100, 20, 50);
        virtualScreen = new Rectangle(300, 200);
        virtualCamera = new Sphere(5);

        object.translateXProperty().set(SCENE_WIDTH/2);
        object.translateYProperty().set(SCENE_HEIGHT/2);
        object.translateZProperty().set(200);

        virtualScreen.translateXProperty().set(SCENE_WIDTH/2 - virtualScreen.getWidth()/2);
        virtualScreen.translateYProperty().set(SCENE_HEIGHT/2 - virtualScreen.getHeight()/2);
        virtualScreen.translateZProperty().set(0);
        virtualScreen.setFill(Color.WHITESMOKE);
        virtualScreen.setOpacity(0.3);

        cameraPosition = cameraPosition.add(SCENE_WIDTH/2, SCENE_HEIGHT/2, -200);
        virtualCamera.translateXProperty().set(cameraPosition.getX());
        virtualCamera.translateYProperty().set(cameraPosition.getY());
        virtualCamera.translateZProperty().set(cameraPosition.getZ());
        virtualCamera.setMaterial(new PhongMaterial(Color.BLUE));
    }

    private void createPerspectiveLines() {
        perspectiveLines = new ArrayList<>();
        Point3D[] objectCorners = getObjectCorners();

        for (Point3D p : objectCorners) {
            Cylinder line = createConnection(cameraPosition, p);
            line.setMaterial(new PhongMaterial(Color.LIGHTGREEN));

            perspectiveLines.add(line);
        }
    }

    private void createIntersects() {

    }

    private Point3D[] getObjectCorners() {
        int numOfCorners = 4;
        Point3D[] corners = new Point3D[numOfCorners];

        corners[0] = new Point3D(object.getTranslateX() - object.getWidth()/2, object.getTranslateY() - object.getHeight()/2, 200 - object.getDepth()/2);
        corners[1] = new Point3D(object.getTranslateX() - object.getWidth()/2, object.getTranslateY() + object.getHeight()/2, 200 - object.getDepth()/2);
        corners[2] = new Point3D(object.getTranslateX() + object.getWidth()/2, object.getTranslateY() - object.getHeight()/2, 200 - object.getDepth()/2);
        corners[3] = new Point3D(object.getTranslateX() + object.getWidth()/2, object.getTranslateY() + object.getHeight()/2, 200 - object.getDepth()/2);

        return corners;
    }

    private Cylinder createConnection(Point3D origin, Point3D target) {
        Point3D yAxis = new Point3D(0, 1, 0);
        Point3D diff = target.subtract(origin);
        double height = diff.magnitude();

        Point3D mid = target.midpoint(origin);
        Transform moveToMidpoint = new Translate(mid.getX(), mid.getY(), mid.getZ());

        Point3D axisOfRotation = diff.crossProduct(yAxis);
        double angle = Math.acos(diff.normalize().dotProduct(yAxis));
        Rotate rotateAroundCenter = new Rotate(-Math.toDegrees(angle), axisOfRotation);

        Cylinder line = new Cylinder(0.5, height);

        line.getTransforms().addAll(moveToMidpoint, rotateAroundCenter);

        return line;
    }

    private void handleMouseControl(Stage primaryStage) {
        initMouseControl(root, scene, primaryStage);

        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            switch (event.getCode()) {
                case W:
                    root.translateZProperty().setValue(object.getTranslateZ() - 10);
                    break;
                case S:
                    root.translateZProperty().setValue(object.getTranslateZ() + 10);
                    break;
                case RIGHT:
                    root.rotateY(-10);
                    break;
                case LEFT:
                    root.rotateY(10);
                    break;
                case UP:
                    root.rotateX(10);
                    break;
                case DOWN:
                    root.rotateX(-10);
                    break;
            }
        });
    }

    private void initMouseControl(SmartGroup group, Scene scene, Stage stage) {
        Rotate xRotate;
        Rotate yRotate;
        group.getTransforms().addAll(
                xRotate = new Rotate(0, SCENE_WIDTH/2, SCENE_HEIGHT/2, 0, Rotate.X_AXIS),
                yRotate = new Rotate(0, SCENE_WIDTH/2, SCENE_HEIGHT/2, 0, Rotate.Y_AXIS)
        );

        xRotate.angleProperty().bind(angleX);
        yRotate.angleProperty().bind(angleY);

        scene.setOnMousePressed(event -> {
            anchorX = event.getSceneX();
            anchorY = event.getSceneY();
            anchorAngleX = angleX.get();
            anchorAngleY = angleY.get();
        });

        scene.setOnMouseDragged(event -> {
            angleX.set(anchorAngleX - (anchorY - event.getSceneY()));
            angleY.set(anchorAngleY + (anchorX - event.getSceneX()));
        });

        stage.addEventHandler(ScrollEvent.SCROLL, event -> {
            double delta = event.getDeltaY();
            group.translateZProperty().set(group.getTranslateZ() - delta);
        });
    }

    static class SmartGroup extends Group {
        Rotate r;
        Transform t = new Rotate();

        void rotateX(int ang) {
            r = new Rotate(ang, SCENE_WIDTH/2, SCENE_HEIGHT/2, 0, Rotate.X_AXIS);
            t = t.createConcatenation(r);

            this.getTransforms().clear();
            this.getTransforms().add(t);
        }

        void rotateY(int ang) {
            r = new Rotate(ang, SCENE_WIDTH/2, SCENE_HEIGHT/2, 0, Rotate.Y_AXIS);
            t = t.createConcatenation(r);

            this.getTransforms().clear();
            this.getTransforms().add(t);
        }
    }
}
