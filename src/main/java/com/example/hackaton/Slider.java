package com.example.hackaton;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.LinearGradient;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class Slider extends VBox {
    private String text;
    private double ideal;
    private double current;

    private boolean isMoreBad;

    public Slider(String text, double ideal, double current, boolean isMoreBad, double posY) {
        Text text1 = new Text(text);
        text1.setFont(Font.font(20));
        text1.setTextAlignment(TextAlignment.CENTER);
        text1.setWrappingWidth(AnimalStats.panel_Width);
        this.getChildren().add(text1);
        Text text10 = new Text("0%");
        Text text20 = new Text("100%");
        text10.setTextAlignment(TextAlignment.CENTER);
        text20.setTextAlignment(TextAlignment.CENTER);
        text1.setTextAlignment(TextAlignment.CENTER);
        text1.setTextAlignment(TextAlignment.CENTER);
        LinearGradient linearGradient = LinearGradient.valueOf("from 0% 0% to 100% 0%, red  0%, orange 33%, yellowgreen 66%, green 100%");
        Rectangle rectangle = new Rectangle(AnimalStats.panel_Width-50,AnimalStats.panel_Height/10,linearGradient);
        double posX;
        if(isMoreBad){
            posX = text10.getLayoutBounds().getWidth() + rectangle.getTranslateX() + rectangle.getWidth()*(10 - (Math.abs(ideal-current)))/10;
        }else{
            if(current > ideal){
                posX = text10.getLayoutBounds().getWidth() +rectangle.getTranslateX() + rectangle.getWidth();
            }else{
                posX = text10.getLayoutBounds().getWidth() +rectangle.getTranslateX() + rectangle.getWidth()*(10 - (Math.abs(ideal-current)))/10;
            }
        }
        Rectangle rectangle1 = new Rectangle(10,AnimalStats.panel_Height/10);
        rectangle1.setTranslateX(posX);
        rectangle1.setTranslateY(-rectangle.getHeight()-(Math.abs(rectangle.getHeight()-rectangle1.getHeight())/2));
        HBox hBox = new HBox();
        hBox.getChildren().addAll(text10,rectangle,text20);
        this.getChildren().add(hBox);
        this.getChildren().add(rectangle1);

    }
}
