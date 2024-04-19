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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class EndScreen {
    public static void endScreen(Animal idealAnimal, ArrayList<Animal> animals){
        for (int i = 0; i < 80; i++) {
            Animal animal = new Animal(true, "skibidi", "dog", 10, "img.png" ,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,"trait");
            animals.add(animal);
        }
        //API call to images and data result
        StackPane root = new StackPane();
        root.setPadding(new Insets(20));
        Scene scene = new Scene(root, HelloApplication.WIDTH, HelloApplication.HEIGHT);
        Stage stage = new Stage();

        Text text = new Text();
        text.setFont(Font.font(50));


        if(animals.isEmpty()){
            text.setText("We didn't find any animals to fit your criteria. \n We are sorry ;(");
        }else{
            text.setText("Here are the animals that match your criteria. \n Click on them to learn more!");
            /*ImageView imageView = new ImageView();
            try {
                imageView = new ImageView(new Image(new FileInputStream("img.png")));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            root.getChildren().add(imageView);*/

        }
        text.setTextAlignment(TextAlignment.CENTER);
        root.getChildren().addAll(text);
        root.setAlignment(text, Pos.TOP_CENTER);

        int stackWidth = 100;
        int stackHeight = 150;
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setMaxWidth(stackWidth*10+(20*10+(4)));
        scrollPane.setMaxHeight(400);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        VBox vBox = new VBox();
        vBox.setSpacing(20);
        vBox.setMaxWidth(stackWidth*10+(20*10+(4)));
        int counter = 0;
        HBox currentHBOX = new HBox();
        currentHBOX.setSpacing(20);
        for (int i = 1; i < animals.size()+1; i++) {
            System.out.println("lol");
            Animal animal = animals.get(i-1);
            StackPane stackPane = new StackPane();
            stackPane.setPrefSize(stackWidth,stackHeight);
            ImageView imageView = null;
            try {
                imageView = new ImageView(new Image(new FileInputStream(animal.pathToImage)));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            imageView.setFitHeight(stackHeight-30);
            imageView.setFitWidth(stackWidth);
            stackPane.getChildren().add(imageView);
            Text text1 = new Text(animal.name);
            text1.setFont(Font.font(20));
            stackPane.getChildren().add(text1);
            stackPane.setAlignment(imageView,Pos.TOP_CENTER);
            stackPane.setAlignment(text1, Pos.BOTTOM_CENTER);
            stackPane.setBorder(new Border(new BorderStroke(Color.BLACK,
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            int num = i-1;
            stackPane.setOnMouseClicked(mouseEvent -> {
                AnimalStats.statScreen(animals.get(num), animals);
            });
            if(i%10==0&&i!=1){
                currentHBOX.getChildren().add(stackPane);
                HBox now = currentHBOX;
                vBox.getChildren().add(now);
                currentHBOX = new HBox();
                currentHBOX.setSpacing(20);
            }else{
                currentHBOX.getChildren().add(stackPane);
            }
            if(i%5!=0&&i==animals.size()){
                vBox.getChildren().add(currentHBOX);
            }

        }
        scrollPane.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        scrollPane.setContent(vBox);
        root.getChildren().add(scrollPane);
        root.setAlignment(Pos.BOTTOM_CENTER);

        HelloApplication.stage.setScene(scene);
        HelloApplication.stage.show();
    }
}
