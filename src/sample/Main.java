package sample;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Sphere;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.stage.Stage;

public class Main extends Application {

    private static final int SCENE_WIDTH = 1400;
    private static final int SCENE_HEIGHT = 800;

    private double anchorX, anchorY;
    private double anchorAngleX = 0;
    private double anchorAngleY = 0;
    private final DoubleProperty angleX = new SimpleDoubleProperty(0);
    private final DoubleProperty angleY = new SimpleDoubleProperty(0);

    @Override
    public void start(Stage primaryStage) throws Exception{
        Box box = new Box(100, 20, 50);

        SmartGroup root = new SmartGroup();
        root.getChildren().add(box);

        Camera camera = new PerspectiveCamera();
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        scene.setFill(Color.SILVER);
        scene.setCamera(camera);

        root.translateXProperty().set(SCENE_WIDTH/2);
        root.translateYProperty().set(SCENE_HEIGHT/2);
        root.translateZProperty().set(-1200);

        initMouseControl(root, scene);

        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            switch (event.getCode()) {
                case W:
                    root.translateZProperty().setValue(box.getTranslateZ() - 10);
                    break;
                case S:
                    root.translateZProperty().setValue(box.getTranslateZ() + 10);
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

        primaryStage.setTitle("Sample Render");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void initMouseControl(SmartGroup group, Scene scene) {
        Rotate xRotate;
        Rotate yRotate;
        group.getTransforms().addAll(
                xRotate = new Rotate(0, Rotate.X_AXIS),
                yRotate = new Rotate(0, Rotate.Y_AXIS)
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
    }


    public static void main(String[] args) {
        launch(args);
    }

    class SmartGroup extends Group {
        Rotate r;
        Transform t = new Rotate();

        void rotateX(int ang) {
            r = new Rotate(ang, Rotate.X_AXIS);
            t = t.createConcatenation(r);

            this.getTransforms().clear();
            this.getTransforms().add(t);
        }

        void rotateY(int ang) {
            r = new Rotate(ang, Rotate.Y_AXIS);
            t = t.createConcatenation(r);

            this.getTransforms().clear();
            this.getTransforms().add(t);
        }
    }
}
