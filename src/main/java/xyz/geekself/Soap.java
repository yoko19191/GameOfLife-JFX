package xyz.geekself;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.util.Duration;

//Simulator
public class Soap {
    private Timeline timeline;
    private MainView mainView;
    private Simulation simulation;

    public Soap(MainView mainView,Simulation simulation){
        this.timeline = new Timeline(new KeyFrame(Duration.millis(500),this::doStep));
        this.timeline.setCycleCount(Timeline.INDEFINITE); //very important, don't forget
        this.mainView = mainView;
        this.simulation = simulation;
    }

    private void doStep(ActionEvent actionEvent) {
        this.simulation.step();
        this.mainView.draw(); //refresh
    }

    public void start(){
        this.timeline.play();
    }

    public void stop(){
        this.timeline.stop();
    }

}
