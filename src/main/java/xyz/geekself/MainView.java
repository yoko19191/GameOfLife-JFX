package xyz.geekself;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import javafx.scene.transform.NonInvertibleTransformException;

import java.nio.channels.Pipe;

public class MainView extends VBox {

    private Button stepButton;
    private Canvas canvas;  //component container

    private Affine affine;  //Transform logic to pixel

    private Simulation simulation;
    private int drawMode;

    public MainView() {
           this.stepButton = new Button("step");
           this.stepButton.setOnAction(actionEvent -> {
               simulation.step();
               draw();
           });

           this.canvas = new Canvas(400,400);
           this.canvas.setOnMousePressed(this::setEventHandler);   //Draw_Press
           this.canvas.setOnMouseDragged(this::setEventHandler);   //Draw_Drag

           this.setOnKeyPressed(this::OnKeyPress);

           this.getChildren().addAll(this.stepButton,this.canvas);

           this.affine = new Affine();
           this.affine.appendScale(400/10f, 400/10f);

           this.simulation = new Simulation(10,10);


    }

    private void OnKeyPress(KeyEvent keyEvent) {
        if(keyEvent.getCode() == KeyCode.S){
            this.drawMode = 1;
        }else if(keyEvent.getCode() == KeyCode.E){
            this.drawMode = 0;
        }
    }

    private void setEventHandler(MouseEvent mouseEvent) {
        double mouseX = mouseEvent.getX();
        double mouseY = mouseEvent.getY();

        try {
            Point2D simCoord = this.affine.inverseTransform(mouseX,mouseY);
            int simX = (int) simCoord.getX();
            int simY = (int) simCoord.getY();
            System.out.println(simX+","+simY);
            this.simulation.setState(simX,simY,drawMode);
            draw();

        } catch (NonInvertibleTransformException e) {
            System.out.println("InverseTransform failed!");
        }
    }

    public void draw(){
        GraphicsContext g = this.canvas.getGraphicsContext2D();
        g.setTransform(this.affine);

        g.setFill(Color.LIGHTGREY);
        g.fillRect(0,0,450,450);

        g.setFill(Color.BLACK);
        for(int x=0;x<this.simulation.width;x++){
            for(int y=0; y<this.simulation.height;y++){
                if(this.simulation.getState(x,y) == 1){
                    g.fillRect(x,y,1,1);
                }

            }
        }

        g.setStroke(Color.GRAY);
        g.setLineWidth(0.05);
        for(int x=0; x<=this.simulation.width;x++){
            g.strokeLine(x,0,x,10);
        }
        for(int y=0; y<=this.simulation.height;y++){
            g.strokeLine(0,y,10,y);
        }


    }

}
