package com.example.hackaton;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Box;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.util.ArrayList;

import static com.example.hackaton.HelloApplication.HEIGHT;
import static com.example.hackaton.HelloApplication.traits;

public class AnimalStats {

    public static int panel_Width = HelloApplication.WIDTH/2 - 80;
    public static void statScreen(Animal idealAnimal, Animal animal, ArrayList<Animal> animals){
        
        HBox root = new HBox();
        root.setSpacing(20);
        Scene scene = new Scene(root, HelloApplication.WIDTH, HelloApplication.HEIGHT);
        scene.getStylesheets().add("styles.css");

        HBox heading = new HBox();
        heading.setSpacing(10);
        heading.setAlignment(Pos.CENTER_LEFT);
        Text icon = new Text("←");
        icon.setFont(Font.font(40));
        icon.setOnMouseClicked(_ -> EndScreen.endScreen(HelloApplication.idealAnimal, animals));
        heading.getChildren().add(icon);
        Text text = new Text();
        text.getStyleClass().add("heading");
        text.setText(animal.name + "'s info details.");
        heading.getChildren().add(text);
        VBox leftPanel = new VBox();
        leftPanel.setSpacing(10);
        leftPanel.setPadding(new Insets(20, 40, 20, 20));
        leftPanel.getChildren().add(heading);
        root.getChildren().add(leftPanel);

        ImageView imageView;
        try {
            imageView = new ImageView(new Image(new FileInputStream(animal.pathToImage)));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        imageView.setFitWidth(HelloApplication.WIDTH/2 - 80);
        imageView.setPreserveRatio(true);
        leftPanel.getChildren().add(imageView);

        HBox dataBox = new HBox();
        dataBox.setSpacing(15);
        dataBox.setAlignment(Pos.CENTER_LEFT);
        VBox scrollBox = new VBox();
        scrollBox.setSpacing(30);
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPrefWidth(leftPanel.getWidth());
        scrollPane.setMaxHeight(HEIGHT*10);

        HBox textBox = new HBox();
        Text name = new Text();
        name.setText("Imię: " + animal.name);
        name.getStyleClass().add("subheading");
        textBox.getChildren().add(name);
        textBox.setPadding(new Insets(0,20,0,0));
        dataBox.getChildren().add(textBox);
        Text sex = new Text();
        sex.setText("Płeć: " + (animal.sex==0?" Samiec":"Samica"));
        name.getStyleClass().add("data-text");
        dataBox.getChildren().add(sex);
        Text age = new Text();
        age.setText("Wiek: " + animal.age);
        name.getStyleClass().add("data-text");
        dataBox.getChildren().add(age);
        Text breed = new Text();
        breed.setText("Gatunek: " + animal.species);
        name.getStyleClass().add("data-text");
        dataBox.getChildren().add(breed);
        Text desc = new Text();
        desc.setText("Czemu jestem dla Ciebie idealny?: " + "I am a " + animal.species + " and I am " + animal.age + " years old.");
        name.getStyleClass().add("data-text");
        StringBuilder prompt = new StringBuilder();

    prompt.append(" - ile czasu potrzebuje zwierzę - 0 oznacza prawie nic, 10 oznacza dużo").append(animal.careTimeNeeded);
        prompt.append(" - wymagana zaradność - 0 oznacza, że potrzeba mu bardzo zaradnego opiekuna, 10 oznacza, że opiekun nie musi być tak zaradny").append(animal.resourcefulness);
        prompt.append(" - jak dużym wyzwaniem dla właściciela jest to zwierzę - 0 oznacza, że jest wielkim wyzwaniem, 10, że nie stanowi wyzwania").append(animal.competentWithAnimals);
        prompt.append(" - jak impulsywny może być właściciel - 0 oznacza, że właściciel nie może być w ogóle impulsywny, 10 znaczy, że może być nieco impulsywny").append(animal.impulsiveness);
        prompt.append(" - jak dużo właściciel powinien zarabaiać - 0 znaczy, że właściciel może zarabiać nawet bardzo mało, 10 oznacza, że właściciel musi zarabiać jak najwięcej").append(animal.income);
        prompt.append(" - rozmiar ogrodu - 0 oznacza, że zwierzę nie potrzebuje ogrodu, 10 znaczy, że potrzebny będzie większy ogród").append(animal.gardenSize);
        prompt.append(" - czas wolny - 0 znaczy, że zwierze nie potrzebuje zbyt wiele uwagi, 10 znaczy, że potrzebuje jej bardzo dużo").append(animal.freeTime);
        prompt.append(" - aktywny styl życia - 0 znaczy, że właściciel nie musi być zbyt aktywny, 10 oznacza, że zwierze potrzebuje wiele czasu na zewnątrz").append(animal.activeLifestyle);
        prompt.append(" - przestrzeń życiowa - 0 znaczy, że zwierzę może mieszkać w zatłoczonym miejscu, jak na przykład centrum miasta, 10 znaczy, że musi żyć w spokojniejszym miejscu").append(animal.livingArea);
        prompt.append(" - obecne zwierzęta - 0 znaczy, że zwierzę nie chce innych zwierząt dookoła, 10 znaczy, że im więcej zwierzaków w domu, tym lepiej").append(animal.currentAnimals);
        prompt.append(" - rodzaj domu - 0 znaczy, że zwierzę nie potrzebuje dużego domou, 10 znaczy, że im większy dom, tym lepiej").append(animal.houseType);
        prompt.append(" - liczba domowników - 0 oznacza, że zwierzę nie chce zbyt wielu domowników, 10 znaczy, że mu nie przeszkadzają").append(animal.housemateCount);
        prompt.append(" - ile lat powinien mieć właściciel - 0 znaczy, że właściciel powinien być młodszy, 10 znaczy, że właściciel powinien być starszy").append(animal.qustionareeAge);
        prompt.append(" - liczba dzieci - 0 znaczy, że zwierze nie chce dzieci, 10 znaczy, że zwierzę je lubI").append(animal.children);
        prompt.append(" - aktywność fizyczna - 0 oznacza, że zwierzę nie jest zbyt aktywne, 10 znaczy, że jest bardzo aktywne").append(animal.animalsActivity);
        desc.setText(ChatGPTController.chatGPT(prompt.toString()));
        desc.setFont(Font.font(12));

        desc.wrappingWidthProperty().bind(scrollPane.widthProperty());
        scrollBox.getChildren().add(dataBox);
        scrollBox.getChildren().add(desc);

        VBox rightPanel = new VBox();
        root.getChildren().add(rightPanel);
        rightPanel.setPadding(new Insets(20, 20, 20, 20));
        rightPanel.setSpacing(10);
        rightPanel.setAlignment(Pos.CENTER);

        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setContent(scrollBox);
        // TODO: Change this
        leftPanel.getChildren().add(scrollPane);



        Text heading2 = new Text("Kompatybilność Twoja\n i Zwierzęcia");
        heading2.getStyleClass().add("smaller-heading");
        heading2.setTextAlignment(TextAlignment.CENTER);
        rightPanel.getChildren().add(heading2);

        VBox scrollBox2 = new VBox();
        scrollBox2.setSpacing(10);
        scrollBox2.setPadding(new Insets(10, 10, 10, 10));

        Field[] fields = animal.getClass().getDeclaredFields();
        Field[] fieldsIdeal = idealAnimal.getClass().getDeclaredFields();

        for (int i = 6; i < fields.length-1; i++) {
            if(i<=9){
                Slider slider;
                try {
                    slider = new Slider(HelloApplication.haszkomora.get(fieldsIdeal[i].getName()), Integer.parseInt(fieldsIdeal[i].get(idealAnimal).toString()), Integer.parseInt(fields[i].get(animal).toString()), true);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }

                scrollBox2.getChildren().add(slider);
            } else {
                Slider slider;
                try {
                    slider = new Slider(HelloApplication.haszkomora.get(fieldsIdeal[i].getName()), Integer.parseInt(fieldsIdeal[i].get(idealAnimal).toString()), Integer.parseInt(fields[i].get(animal).toString()), false);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
                scrollBox2.getChildren().add(slider);
            }
        }

        ScrollPane scrollPane2 = new ScrollPane();
        scrollPane2.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane2.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane2.setContent(scrollBox2);
        // TODO: fix this
        rightPanel.getChildren().add(scrollPane2);

        HelloApplication.stage.setScene(scene);
        HelloApplication.stage.show();
    }
}
