package com.replilab.worm;

import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * This project is the *****SANDBOX****** for the studing purpose only
 */

public class Main extends Application {
    Scene scene;
    public boolean isRun = true;

    Canvas canvas = new Canvas(820, 820);
    GraphicsContext gc = canvas.getGraphicsContext2D();
    public Worm worm = new Worm(gc);

    @Override
    public void start(Stage primaryStage) {

        worm.begin();
        Group root = new Group();

        primaryStage.setTitle("Червяг!");
        root.getChildren().add(canvas);
        scene = new Scene(root, 1000, 1000);
        primaryStage.setScene(scene);
        primaryStage.show();

        Timer timer = new Timer();
        timer.schedule(new AliveTask(), 0, 300);

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
                KeyCode pressedKey = ke.getCode();
                if (pressedKey == KeyCode.LEFT | pressedKey == KeyCode.RIGHT) {
                    worm.changeDirection(pressedKey);
                }
                worm.render(gc);
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Timer class for pereodic worm iteration for making a step
     */

    public class AliveTask extends TimerTask {
        @Override
        public void run() {
            worm.render(gc);
        }
    }
}
