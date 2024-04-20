package com.example.hackaton;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class AnimalStats {
    
    public static int panel_Height = 300;
    public static int panel_Width = 500;
    public static void statScreen(Animal idealAnimal, Animal animal, ArrayList<Animal> animals){
        
        HBox hBox = new HBox();
        VBox vBox1 = new VBox();
        VBox vBox2 = new VBox();
        
        StackPane root = new StackPane();
        Scene scene = new Scene(root, HelloApplication.WIDTH, HelloApplication.HEIGHT);
        Stage stage = new Stage();

        Text text = new Text();
        text.setFont(Font.font(50));
        

        text.setText(animal.name + "'s info details.");

        text.setTextAlignment(TextAlignment.CENTER);
        root.getChildren().addAll(text);
        root.setAlignment(text, Pos.TOP_CENTER);

        root.setPadding(new Insets(20));
        ImageView imageView = null;
        try {
            imageView = new ImageView(new Image(new FileInputStream(animal.pathToImage)));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        imageView.setFitHeight(panel_Height);
        imageView.setFitWidth(panel_Width);
        vBox1.getChildren().add(imageView);

        
        VBox dataBox = new VBox();
        ScrollPane scrollPane = new ScrollPane();
        Text name = new Text();
        name.setText("Name: " + animal.name);
        name.setFont(Font.font(30));
        dataBox.getChildren().add(name);
        Text sex = new Text();
        sex.setText("Sex: " + animal.sex);
        sex.setFont(Font.font(30));
        dataBox.getChildren().add(sex);
        Text age = new Text();
        age.setText("Age: " + Integer.toString(animal.age));
        age.setFont(Font.font(30));
        dataBox.getChildren().add(age);
        Text breed = new Text();
        breed.setText("Species: " + animal.species);
        breed.setFont(Font.font(30));
        dataBox.getChildren().add(breed);
        Text desc = new Text();
        desc.setText("Why Am I right for you: " + "I am a " + animal.species + " and I am " + animal.age + " years old.");
        desc.setFont(Font.font(30));
        desc.wrappingWidthProperty().bind(scrollPane.widthProperty());
        dataBox.getChildren().add(desc);


        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setContent(dataBox);
        scrollPane.setPrefSize(panel_Width,panel_Height);
        scrollPane.setMaxSize(panel_Width,panel_Height);
        vBox1.getChildren().add(scrollPane);


        VBox sliderBox = new VBox();
        sliderBox.setSpacing(10);
        sliderBox.setPadding(new Insets(15,0,0,0));
        Text text100 = new Text("Kompatybilność Ciebie i Zwierzęcia");
        text100.setWrappingWidth(panel_Width);
        text100.setTextAlignment(TextAlignment.CENTER);
        text100.setFont(Font.font("", FontWeight.BOLD, 25));
        sliderBox.getChildren().add(text100);

        Field[] fields = animal.getClass().getDeclaredFields();

        Field[] fieldsIdeal = idealAnimal.getClass().getDeclaredFields();

        try {
            System.out.println(fieldsIdeal[1].get(idealAnimal).toString()+ " XDDD");
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        for (int i = 6; i < fields.length-1; i++) {
            if(i>=6&&i<=9){
                Slider slider = null;
                try {
                    slider = new Slider(HelloApplication.haszkomora.get(fieldsIdeal[i].getName().toString()), Integer.parseInt(fieldsIdeal[i].get(idealAnimal).toString()), Integer.parseInt(fields[i].get(animal).toString()), true, 0.0);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }

                sliderBox.getChildren().add(slider);
            }else{
                Slider slider = null;
                try {
                    slider = new Slider(HelloApplication.haszkomora.get(fieldsIdeal[i].getName().toString()), Integer.parseInt(fieldsIdeal[i].get(idealAnimal).toString()), Integer.parseInt(fields[i].get(animal).toString()), false, 0.0);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
                sliderBox.getChildren().add(slider);
            }
        }

        ScrollPane scrollPane2 = new ScrollPane();
        scrollPane2.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane2.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane2.setContent(sliderBox);
        scrollPane2.setPrefSize(panel_Width,panel_Height*2);
        scrollPane2.setMaxSize(panel_Width,panel_Height*2);
        vBox2.getChildren().add(scrollPane2);


        StackPane stackPane10 = new StackPane();
        stackPane10.setMaxSize(30,30);
        Text text1 = new Text("←");
        text1.setFont(Font.font(40));
        stackPane10.getChildren().add(text1);
        stackPane10.setOnMouseClicked(mouseEvent -> {
            EndScreen.endScreen(HelloApplication.idealAnimal, animals);
        });
        root.getChildren().add(stackPane10);
        root.setAlignment(stackPane10,Pos.TOP_LEFT);


        hBox.getChildren().addAll(vBox1,vBox2);
        hBox.setMaxHeight(panel_Height+panel_Height);
        root.getChildren().add(hBox);
        hBox.setMaxWidth(panel_Width*2+(20));
        root.setAlignment(hBox,Pos.BOTTOM_CENTER);
        HelloApplication.stage.setScene(scene);
        HelloApplication.stage.show();


    }

    public static Image getAIphoto(Animal animal){
        try {
            return new Image(new FileInputStream(animal.pathToImage));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
