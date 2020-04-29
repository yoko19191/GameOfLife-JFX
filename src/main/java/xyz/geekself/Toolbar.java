package xyz.geekself;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;

public class Toolbar extends ToolBar {

    private MainView mainView;

    public Toolbar(MainView mainView) {
        this.mainView = mainView;
        //Create Button
        Button step = new Button("Step");
        step.setOnAction(this::handlerStep);
        Button draw = new Button("Draw");
        draw.setOnAction(this::handlerDraw);
        Button erase = new Button("Erase");
        erase.setOnAction(this::handlerErase);
        Button reset = new Button("Reset");
        reset.setOnAction(this::handlerReset);
        Button start = new Button("Start");
        start.setOnAction(this::handlerStart);
        Button stop = new Button("Stop");
        stop.setOnAction(this::handlerStop);

        this.getItems().addAll(draw,erase,reset,step,start,stop);
    }

    private void handlerStop(ActionEvent actionEvent) {
        this.mainView.getSoap().stop();
    }

    private void handlerStart(ActionEvent actionEvent) {
        this.mainView.setApplicationState(MainView.SIMULATING);
        this.mainView.getSoap().start();
    }

    private void handlerReset(ActionEvent actionEvent) {
        this.mainView.setApplicationState(MainView.EDITING);
        this.mainView.draw();
    }


    //SetOnAction
    private void handlerStep(ActionEvent actionEvent) {
        //System.out.println("Step button pressed");
        this.mainView.setApplicationState(MainView.SIMULATING);
        this.mainView.getSimulation().step();
        this.mainView.draw();
    }

    private void handlerDraw(ActionEvent actionEvent) {
        //System.out.println("Draw button pressed");
        this.mainView.setDrawMode(Simulation.ALIVE);
    }

    private void handlerErase(ActionEvent actionEvent) {
        //System.out.println("Erase button pressed");
        this.mainView.setDrawMode(Simulation.DEAD);
    }


}
