package xyz.geekself;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import javafx.scene.transform.NonInvertibleTransformException;

import java.nio.channels.Pipe;

public class MainView extends VBox {
    private InfoBar infoBar;       //it's infoBar
    private Canvas canvas;  //component container, somewhere you can draw and put on components

    private Affine affine;  //Transform logic matrix into pixels, adapter

    private Simulation simulation;  //logic core
    private int drawMode = Simulation.ALIVE;   //Default drawMode = 1

    public MainView() {

           this.canvas = new Canvas(400,400);
           this.canvas.setOnMousePressed(this::setEventHandler);   //Draw_Press
           this.canvas.setOnMouseDragged(this::setEventHandler);   //Draw_Drag
           this.canvas.setOnMouseMoved(this::handlerMoved);

           this.setOnKeyPressed(this::OnKeyPress);      //setOnHotKey

           Toolbar toolbar = new Toolbar(this);

           this.infoBar = new InfoBar();
           this.infoBar.setDrawMode(this.drawMode);
           this.infoBar.setCursorPostion(0,0);

           Pane blanker = new Pane();                //a blank component
           blanker.setMinSize(0,0);
           blanker.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
           VBox.setVgrow(blanker, Priority.ALWAYS);  //pull infoBar down

           this.getChildren().addAll(toolbar,this.canvas,blanker,infoBar);  //Add components here

           this.affine = new Affine();
           this.affine.appendScale(400/10f, 400/10f);

           this.simulation = new Simulation(10,10);


    }

    private void handlerMoved(MouseEvent mouseEvent) {
        Point2D simCoord = this.getSimulationCoordinates(mouseEvent);
        this.infoBar.setCursorPostion((int) simCoord.getX(),(int) simCoord.getY());
    }

    private void OnKeyPress(KeyEvent keyEvent) {
        if(keyEvent.getCode() == KeyCode.D){
            this.drawMode = Simulation.ALIVE;
        }else if(keyEvent.getCode() == KeyCode.E){
            this.drawMode = Simulation.DEAD;
        }
    }

    private void setEventHandler(MouseEvent mouseEvent) {

        Point2D simCoord = this.getSimulationCoordinates(mouseEvent);

        int simX = (int) simCoord.getX();
        int simY = (int) simCoord.getY();
        System.out.println(simX+","+simY);
        this.simulation.setState(simX,simY,drawMode);
        draw();

    }

    private  Point2D getSimulationCoordinates(MouseEvent mouseEvent){
        double mouseX = mouseEvent.getX();
        double mouseY = mouseEvent.getY();

        try {
            Point2D simCoord = this.affine.inverseTransform(mouseX, mouseY);
            return simCoord;
        }catch (NonInvertibleTransformException e) {
            throw new RuntimeException("Non invertible transform");  //Allow return nothing
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
                if(this.simulation.getState(x,y) == Simulation.ALIVE){
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

    public Simulation getSimulation() {
        return this.simulation;
    }

    public void setDrawMode(int setMode) {
        this.drawMode = setMode;
        this.infoBar.setDrawMode(setMode);
    }
}
