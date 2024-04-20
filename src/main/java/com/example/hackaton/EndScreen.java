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
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class EndScreen {

    public static void endScreen(Animal idealAnimal, ArrayList<Animal> animals) {


        // API call to images and data result
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, HelloApplication.WIDTH, HelloApplication.HEIGHT);
        scene.getStylesheets().add("styles.css");
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(20, 20, 20, 20));
        root.getChildren().add(vBox);

        Text text1 = new Text();
        text1.getStyleClass().add("heading");
        Text text2 = new Text();
        text2.getStyleClass().add("subheading");

        VBox textBox = new VBox();
        textBox.setSpacing(5);
        textBox.setAlignment(Pos.CENTER);
        textBox.getChildren().addAll(text1, text2);

        if (animals.isEmpty()) {
            text1.setText("Niestety nie znalezlismy zadnych rezultatow.");
            text2.setText("Przepraszamy :(");
        } else {
            text1.setText("Oto dopasowane zwierzeta!");
            text2.setText("Kliknij na zwierze, by sie mu przyjrzec.");

        }
        vBox.getChildren().add(textBox);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPrefHeight(HelloApplication.HEIGHT);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        GridPane justify = new GridPane();
        justify.setAlignment(Pos.CENTER);
        justify.getChildren().add(scrollPane);
        justify.setPadding(new Insets(50, 0, 0, 0));
        vBox.getChildren().add(justify);
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(20);
        grid.setHgap(20);

        scrollPane.setContent(grid);

        int column = 0;
        int row = 0;
        for (int i = 1; i < animals.size() + 1; i++) {
            Animal animal = animals.get(i - 1);

            VBox animalCard = new VBox();
            animalCard.getStyleClass().add("animal-card");
            animalCard.setPrefWidth(100);
            animalCard.setPrefHeight(150);
            ImageView imageView = null;

            try {
                imageView = new ImageView(new Image(new FileInputStream(animal.pathToImage)));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

            imageView.setFitHeight(100);
            imageView.setPreserveRatio(true);

            animalCard.getChildren().add(imageView);
            Text text;
            if (row == 0 && column == 0) {
                text = new Text("\uD83E\uDD47 " + animal.name);
            }
            else if (row == 0 && column == 1) {
                text = new Text("\uD83E\uDD48 " + animal.name);
            }
            else if (row == 0 && column == 2) {
                text = new Text("\uD83E\uDD49 " + animal.name);
            }
            else {
                text = new Text(animal.name);
            }
            text.setFont(Font.font("Open Sans", FontWeight.BOLD, 20));
            animalCard.getChildren().add(text);
            animalCard.setSpacing(10);
            int num = i - 1;
            if (column < calculateNumberOfRows()) {
                grid.add(animalCard, column, row);
                column++;
            } else {
                column = 0;
                row++;
                grid.add(animalCard, column, row);
            }
            System.out.println(animal);
            animalCard.setOnMouseClicked(mouseEvent -> {
                AnimalStats.statScreen(idealAnimal, animals.get(num), animals);
            });

        }
        scrollPane.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        HelloApplication.stage.setScene(scene);
        HelloApplication.stage.show();
    }

    public static int calculateNumberOfRows() {
        int count = 0;
        int screenWidth = HelloApplication.WIDTH;

        // Subtract left margin
        screenWidth-=120;
        while (screenWidth > 0) {
            screenWidth -= 220;
            count++;
        }
        return count-1;

    }
}
