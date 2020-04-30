package xyz.geekself;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class InfoBar extends HBox {

    //Setup Display format
    private static String drawModeFormat = "DrawMode: %s     ";
    private static String cursorPosFormat = "Cursor: (%d,%d)";

    private Label cursor;
    private Label editingTool;

    public InfoBar(){

        this.cursor = new Label();
        this.editingTool = new Label();
        /*
        Pane blanker = new Pane();                //a blank component
        blanker.setMinSize(0,0);
        blanker.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        VBox.setVgrow(blanker, Priority.ALWAYS);  //pull infoBar down
        */

        this.getChildren().addAll(this.editingTool,this.cursor);
    }

    public void setDrawMode(int drawMode){
        String drawModeString;

        if(drawMode == Simulation.ALIVE){
            drawModeString = "Drawing";
        }else{
            drawModeString = "Erasing";
        }
        this.editingTool.setText(String.format(drawModeFormat,drawModeString));
    }

    public void setCursorPostion(int x, int y){
        this.cursor.setText(String.format(cursorPosFormat,x,y));
    }


}
