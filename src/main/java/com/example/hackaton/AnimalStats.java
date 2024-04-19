package com.example.hackaton;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.ArrayList;

public class AnimalStats {
    
    static int panel_Height = 300;
    static int panel_Width = 500;
    public static void statScreen(Animal animal, ArrayList<Animal> animals){
        
        StackPane root = new StackPane();
        Scene scene = new Scene(root, HelloApplication.WIDTH, HelloApplication.HEIGHT);
        Stage stage = new Stage();

        Text text = new Text();
        text.setFont(Font.font(50));
        VBox vBox1 = new VBox();
        HBox hBox = new HBox();
        HBox hBox1 = new HBox();

        text.setText(animal.getName() + "'s info details.");

        text.setTextAlignment(TextAlignment.CENTER);
        root.getChildren().addAll(text);
        root.setAlignment(text, Pos.TOP_CENTER);

        root.setPadding(new Insets(20));
        ImageView imageView = new ImageView(animal.getImage());
        imageView.setFitHeight(panel_Height);
        imageView.setFitWidth(panel_Width);
        hBox.getChildren().add(imageView);

        ImageView  imageView1 = new ImageView(getAIphoto(animal));
        imageView1.setFitHeight(panel_Height);
        imageView1.setFitWidth(panel_Width);
        hBox.getChildren().add(imageView1);

        VBox vBox = new VBox();
        ScrollPane scrollPane = new ScrollPane();
        Text name = new Text();
        name.setText("Name: " + animal.getName());
        name.setFont(Font.font(30));
        vBox.getChildren().add(name);
        Text sex = new Text();
        sex.setText("Sex: " + animal.getSex());
        sex.setFont(Font.font(30));
        vBox.getChildren().add(sex);
        Text age = new Text();
        age.setText("Age: " + Integer.toString(animal.getAge()));
        age.setFont(Font.font(30));
        vBox.getChildren().add(age);
        Text breed = new Text();
        breed.setText("Breed: " + animal.getBreed());
        breed.setFont(Font.font(30));
        vBox.getChildren().add(breed);
        Text desc = new Text();
        desc.setText("Why Am I right for you: " + animal.getDesc());
        desc.setFont(Font.font(30));
        desc.wrappingWidthProperty().bind(scrollPane.widthProperty());
        vBox.getChildren().add(desc);


        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setContent(vBox);
        scrollPane.setPrefSize(panel_Width,panel_Height-80);
        scrollPane.setMaxSize(panel_Width,panel_Height-80);


        VBox vBox2 = new VBox();
        ScrollPane scrollPane2 = new ScrollPane();


        scrollPane2.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane2.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane2.setContent(vBox2);
        scrollPane2.setPrefSize(panel_Width,panel_Height-80);
        scrollPane2.setMaxSize(panel_Width,panel_Height-80);
        hBox1.getChildren().add(scrollPane);
        hBox1.getChildren().add(scrollPane2);


        vBox1.setSpacing(35);
        vBox.setPadding(new Insets(5));
        hBox1.setSpacing(35);
        hBox.setSpacing(35);
        vBox1.getChildren().addAll(hBox,hBox1);
        vBox1.setMaxHeight(panel_Height+panel_Height-80+45);
        root.getChildren().add(vBox1);
        vBox1.setMaxWidth(panel_Width*2+(20));
        root.setAlignment(vBox1,Pos.BOTTOM_CENTER);
        stage.setScene(scene);
        stage.show();


    }

    public static Image getAIphoto(Animal animal){
        return animal.getImage();
    }
}
