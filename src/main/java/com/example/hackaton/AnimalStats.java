package com.example.hackaton;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.util.ArrayList;

import static com.example.hackaton.HelloApplication.traits;

public class AnimalStats {
    
    public static int panel_Height = 300;
    public static int panel_Width = 500;
    public static void statScreen(Animal idealAnimal, Animal animal, ArrayList<Animal> animals){
        
        HBox hBox = new HBox();
        VBox vBox1 = new VBox();
        VBox vBox2 = new VBox();
        
        StackPane root = new StackPane();
        Scene scene = new Scene(root, HelloApplication.WIDTH, HelloApplication.HEIGHT);

        Text text = new Text();
        text.setFont(Font.font(50));
        

        text.setText(animal.name + "'s info details.");

        text.setTextAlignment(TextAlignment.CENTER);
        root.getChildren().addAll(text);
        StackPane.setAlignment(text, Pos.TOP_CENTER);

        root.setPadding(new Insets(20));
        ImageView imageView;
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
        name.setText("Imię: " + animal.name);
        name.setFont(Font.font(30));
        dataBox.getChildren().add(name);
        Text sex = new Text();
        sex.setText("Płeć: " + (animal.sex==0?" Samiec":"Samica"));
        sex.setFont(Font.font(30));
        dataBox.getChildren().add(sex);
        Text age = new Text();
        age.setText("Wiek: " + animal.age);
        age.setFont(Font.font(30));
        dataBox.getChildren().add(age);
        Text breed = new Text();
        breed.setText("Gatunek: " + animal.species);
        breed.setFont(Font.font(30));
        dataBox.getChildren().add(breed);
        Text desc = new Text();
        desc.setText("Czemu jestem dla Ciebie idealny?: " + "I am a " + animal.species + " and I am " + animal.age + " years old.");
        desc.setFont(Font.font(30));
        StringBuilder prompt = new StringBuilder();
        prompt.append(" - ile czasu potrzebuje zwierzę - 0 oznacza prawie nic, 10 oznacza dużo - ").append(animal.careTimeNeeded);
        prompt.append(" - wymagana zaradność - 0 oznacza, że potrzeba mu bardzo zaradnego opiekuna, 10 oznacza, że opiekun nie musi być tak zaradny").append(animal.resourcefulness);
        prompt.append(" - jak dużym wyzwaniem dla właściciela jest to zwierzę - 0 oznacza, że jest wielkim wyzwaniem, 10, że nie stanowi wyzwania").append(animal.competentWithAnimals);
        prompt.append(" - jak impulsywny może być właściciel - 0 oznacza, że właściciel nie może być w ogóle impulsywny, 10 znaczy, że może być nieco impulsywny").append(animal.impulsiveness);
        prompt.append(" - jak dużo właściciel powinien zarabaiać - 0 znaczy, że właściciel może zarabiać nawet bardzo mało, 10 oznacza, że właściciel musi zarabiać jak najwięcej").append(animal.income);
        prompt.append(" - rozmiar ogrodu - 0 oznacza, że zwierzę nie potrzebuje ogrodu, 10 znaczy, że potrzebny będzie większy ogród - ").append(animal.gardenSize);
        prompt.append(" - czas wolny - 0 znaczy, że zwierze nie potrzebuje zbyt wiele uwagi, 10 znaczy, że potrzebuje jej bardzo dużo - ").append(animal.freeTime);
        prompt.append(" - aktywny styl życia - 0 znaczy, że właściciel nie musi być zbyt aktywny, 10 oznacza, że zwierze potrzebuje wiele czasu na zewnątrz - ").append(animal.activeLifestyle);
        prompt.append(" - przestrzeń życiowa - 0 znaczy, że zwierzę może mieszkać w zatłoczonym miejscu, jak na przykład centrum miasta, 10 znaczy, że musi żyć w spokojniejszym miejscu - ").append(animal.livingArea);
        prompt.append(" - obecne zwierzęta - 0 znaczy, że zwierzę nie chce innych zwierząt dookoła, 10 znaczy, że im więcej zwierzaków w domu, tym lepiej - ").append(animal.currentAnimals);
        prompt.append(" - rodzaj domu - 0 znaczy, że zwierzę nie potrzebuje dużego domou, 10 znaczy, że im większy dom, tym lepiej - ").append(animal.houseType);
        prompt.append(" - liczba domowników - 0 oznacza, że zwierzę nie chce zbyt wielu domowników, 10 znaczy, że mu nie przeszkadzają - ").append(animal.housemateCount);
        prompt.append(" - ile lat powinien mieć właściciel - 0 znaczy, że właściciel powinien być młodszy, 10 znaczy, że właściciel powinien być starszy - ").append(animal.qustionareeAge);
        prompt.append(" - liczba dzieci - 0 znaczy, że zwierze nie chce dzieci, 10 znaczy, że zwierzę je lubi - ").append(animal.children);
        prompt.append(" - aktywność fizyczna - 0 oznacza, że zwierzę nie jest zbyt aktywne, 10 znaczy, że jest bardzo aktywne - ").append(animal.animalsActivity);
        desc.setText(prompt.toString());
        desc.setFont(Font.font(12));

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
            if(i<=9){
                Slider slider;
                try {
                    slider = new Slider(HelloApplication.haszkomora.get(fieldsIdeal[i].getName()), Integer.parseInt(fieldsIdeal[i].get(idealAnimal).toString()), Integer.parseInt(fields[i].get(animal).toString()), true);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }

                sliderBox.getChildren().add(slider);
            }else{
                Slider slider;
                try {
                    slider = new Slider(HelloApplication.haszkomora.get(fieldsIdeal[i].getName()), Integer.parseInt(fieldsIdeal[i].get(idealAnimal).toString()), Integer.parseInt(fields[i].get(animal).toString()), false);
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
        stackPane10.setOnMouseClicked(_ -> EndScreen.endScreen(HelloApplication.idealAnimal, animals));
        root.getChildren().add(stackPane10);
        StackPane.setAlignment(stackPane10,Pos.TOP_LEFT);

        hBox.getChildren().addAll(vBox1,vBox2);
        hBox.setMaxHeight(panel_Height+panel_Height);
        root.getChildren().add(hBox);
        hBox.setMaxWidth(panel_Width*2+(20));
        StackPane.setAlignment(hBox,Pos.BOTTOM_CENTER);
        HelloApplication.stage.setScene(scene);
        HelloApplication.stage.show();
    }
}
