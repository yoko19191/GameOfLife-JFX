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


        this.getItems().addAll(step,draw,erase);
    }


    //SetOnAction
    private void handlerStep(ActionEvent actionEvent) {
        System.out.println("Step button pressed");
        this.mainView.getSimulation().step();
        this.mainView.draw();
    }

    private void handlerDraw(ActionEvent actionEvent) {
        System.out.println("Draw button pressed");
        this.mainView.setDrawMode(Simulation.ALIVE);
    }

    private void handlerErase(ActionEvent actionEvent) {
        System.out.println("Erase button pressed");
        this.mainView.setDrawMode(Simulation.DEAD);
    }


}
