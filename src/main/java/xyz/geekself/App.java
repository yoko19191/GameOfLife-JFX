package xyz.geekself;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {

        MainView mainView = new MainView();
        Scene scene = new Scene(mainView, 400, 455);
        stage.setScene(scene);
        stage.setTitle(" R.I.P  John Conway");
        stage.show();
        mainView.draw();

    }

    public static void main(String[] args) {
        launch();
    }

}