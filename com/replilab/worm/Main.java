package com.replilab.worm;

import java.io.*;
import java.util.HashMap;
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
 *
 *
 * Launch class, preparing objects, canvas.
 */

public class Main extends Application {
    Scene scene;
    public boolean isRun=true;
    public Worm worm = new NewWorm();
    Canvas canvas = new Canvas(800, 800);
    GraphicsContext gc = canvas.getGraphicsContext2D();

    /**
     * Launch method, make first cell, place food, prepare canvas window, add task to timer
     *
     * @param primaryStage
     * @throws Exception
     */
        @Override
    public void start(Stage primaryStage) throws Exception {

        worm.begin();
        worm.foodRepositioning();
        Group root = new Group();
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        primaryStage.setTitle("Червяг!");
        root.getChildren().add(canvas);
        scene = new Scene(root, 1000, 800);
        primaryStage.setScene(scene);
        primaryStage.show();

        Timer timer = new Timer();
        timer.schedule(new AliveTask(), 0, 300);

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            /**
             * keyboard listener method, filters pressed keys and calls change direction method, aloso call a render
             *
             * @param ke
             */
            public void handle(KeyEvent ke) {
                //    KeyCode key= ke.getCode();
                KeyCode cc = ke.getCode();
                //  int cc=key.getCode();
                //if(cc==37 ){System.out.print("LEFT");}
                String getDrirection = ke.getCode().toString();
                //if(getDrirection=="DIVIDE"){worm.foodRepositioning();}
                //if(getDrirection=="MULTIPLY"){worm.addCell();}
                if (getDrirection == "LEFT" | getDrirection == "RIGHT" | getDrirection == "UP" | getDrirection == "DOWN") {
                    worm.changeDirection(getDrirection);
                }
                if (getDrirection == "S") {
                    try {
                        saveState();
                    } catch (Exception FileNotFoundException) {
                    }

                }
                if (getDrirection == "L") {
                    try {
                        loadState();
                    } catch (Exception FileNotFoundException) {
                    }

                }
                worm.render(gc);
            }
        });
    }

public static void main(String[] args) {launch(args);}

    /**
     * Timer class for pereodic worm iteration for making a step
     */

public class AliveTask extends TimerTask{
    @Override
        public void run() {
            worm.makeStep();
            worm.render(gc);
        }
    }
    private void saveState() throws FileNotFoundException, IOException {
        FileOutputStream fos = new FileOutputStream("save.dat");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(worm.cellsHashMap);
        oos.flush();
        oos.close();
        fos.close();

    }

    public void loadState() throws FileNotFoundException, IOException, ClassNotFoundException {
    /*    FileInputStream fis = new FileInputStream("save.dat");
        ObjectInputStream ois = new ObjectInputStream(fis);
       // Worm w1;
        HashMap<Integer, Worm.Cells>newCell = new HashMap<Integer, Worm.Cells>();
        //worm.cellsHashMap = (HashMap<Integer, Worm.Cells>) ois.readObject();
        newCell = (HashMap<Integer, Worm.Cells>) ois.readObject();
        worm.cellsHashMap = newCell;
        ois.close();
        fis.close();
*/
    }
}
