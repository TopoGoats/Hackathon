package com.example.hackaton;

import com.example.hackaton.form.*;
import com.jfoenix.controls.JFXButton;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HelloApplication extends Application {
    public static GridPane root = new GridPane();
    public static int WIDTH = (int) Screen.getPrimary().getBounds().getWidth() * 3 / 4;
    public static int HEIGHT = (int) Screen.getPrimary().getBounds().getHeight() * 3 / 4;
    public static List<FormQuestion> questions = new ArrayList<>();
    public static List<Scene> surveyScenes = new ArrayList<>();
    public static Stage stage;

    @Override
    public void start(Stage stage1) {
        root.setAlignment(javafx.geometry.Pos.CENTER);
        root.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles.css")).toExternalForm());
        stage1.setResizable(false);
        DatabaseController.connectToDatabase();
        stage = stage1;
        Scene scene = new Scene(root, WIDTH, HEIGHT);

        // Create 4 survey scenes for survey questions
        for (int i = 0; i < 5; i++) {
            GridPane surveyRoot = new GridPane();
            surveyRoot.setAlignment(javafx.geometry.Pos.CENTER);
            // Wrap surveyroot in scrollpane
            ScrollPane scrollPane = new ScrollPane();
            scrollPane.getStyleClass().add("scroll-pane");
            VBox content = new VBox();
            content.setPadding(new javafx.geometry.Insets(10, 40, 10, 40));
            scrollPane.setContent(content);
            scrollPane.setMaxSize(WIDTH, HEIGHT);
            scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            scrollPane.setLayoutY(0);

            surveyRoot.getChildren().add(scrollPane);
            Scene survScene = new Scene(surveyRoot, WIDTH, HEIGHT);
            survScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles.css")).toExternalForm());
            surveyScenes.add(survScene);
        }

        JFXButton button = new JFXButton("Start survey");
        button.getStyleClass().add("main-button");
        button.setOnAction(event -> {
            setupSurvey();
            stage.setScene(surveyScenes.getFirst());
        });
        root.getChildren().add(button);

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void setupSurvey() {
        questions.clear();
        questions.addAll(List.of(
            new SingleChoiceQuestion("Ile średnio masz czasu dziennie?", List.of("Prawie wcale (1-2 godziny)", "Średnio (3-4 godziny)", "Dużo (5-6 godzin)", "Zawsze mam wolne (7+ godzin)")),
            new SingleChoiceQuestion("Jaki styl życia prowadzisz?", List.of("Nie wychodzę z domu", "Czasem wyjdę na spacer", "Dość aktywny", "Aktywny")),
            new SingleChoiceQuestion("W jakim miejscu mieszkasz?", List.of("Centrum miasta", "Przedmieścia", "Wieś", "Wyrwa czasoprzestrzenna")),
            new SingleChoiceQuestion("W jakim domu mieszkasz?", List.of("Dom wolnostojący", "Bliźniak", "Pełnym cudzych żon, słyszysz?", "Małe mieszkanie")),
            new SingleChoiceQuestion("Ilu masz domowników?", List.of("Tylko ja", "2-3", "4-6", "7+")),
            // break
            new SingleChoiceQuestion("Ile masz lat?", List.of("Poniżej 10", "11-17", "18-24", "Ponad 25")),
            new SingleChoiceQuestion("Czy masz już jakieś zwierzęta? Ile?", List.of("Nie", "1-2", "3-4", "5+")),
            new SingleChoiceQuestion("Czy w twoim otoczeniu przebywają dzieci?", List.of("Tak", "Nie")),
            new SingleChoiceQuestion("Czego \"oczekujesz\" od zwierzaka?", List.of("Towarzystwa", "Motywacji do aktywności", "Chcę go szkolić", "Ma dobrze smakować")),
            new MultipleChoiceQuestion("Wybierz pięć cech, które do ciebie pasują:", List.of("Odpowiedzialny", "Leniwy", "Pozytywny", "Łatwo się irytuję", "Zapominalski", "Aktywny", "Słomiany zapał", "Zdeterminowany", "Pochmurny", "Masło")),
            // break
            new SliderQuestion("Jak oceniasz swoją odpowiedzialność?", "Nieodpowiedzialny", "Bardzo odpowiedzialny"),
            new SliderQuestion("Ile czasu jesteś w stanie poświęcić zwierzęciu?", "Prawie wcale", "Cały swój czas"),
            new SliderQuestion("Jak zaradny jesteś?", "Niezaradny", "Bardzo zaradny"),
            new SliderQuestion("Jak oceniasz swoje kompetencje w opiekowaniu się zwierzętami?", "Brak kompetencji", "Mistrz opieki"),
            new SliderQuestion("Jak bardzo porywczy jesteś?", "Totalny chillout", "Dzika bestia"),
            // break
            new SliderQuestion("Jakie są twoje zarobki?", "Bezdomny", "Milioner"),
            new SliderQuestion("Czy masz ogród? Jak duży?", "Nie mam", "Gigantyczny"),
            new OpenEndedQuestion("Czego nie lubisz w zwierzętach?"),
            new OpenEndedQuestion("Jakie są twoje przeciwskazania? (alergie, choroby itp.)"),
            new OpenEndedQuestion("Jakie jest twoje doświadczenie ze zwierzętami?"),
            // break
            new OpenEndedQuestion("Dlaczego chcesz mieć zwierzę?"),
            new OpenEndedQuestion("Czym się zajmujesz?"),
            new OpenEndedQuestion("Jak spędzasz czas wolny?"),
            new OpenEndedQuestion("Opisz siebie"),
            new OpenEndedQuestion("Dodatkowe uwagi")
        ));

        // Add questions and buttons to a VBox and add the VBox to each survey scene
        for (int i = 0; i < 5; i++) {
            VBox questionBox = new VBox();

            for (int j = 0; j < 5; j++) {
                questionBox.getChildren().add((Node) questions.get(i * 5 + j));
            }

            // javafx shenanigans
            int[] index = {i};

            HBox buttonBox = new HBox();
            buttonBox.setPadding(new javafx.geometry.Insets(5, 20, 20, 20));
            buttonBox.setSpacing(20);
            buttonBox.setAlignment(javafx.geometry.Pos.CENTER);
            if (i > 0) {
                JFXButton prevButton = new JFXButton("Previous");
                prevButton.setOnAction(event -> {
                    stage.setScene(surveyScenes.get(index[0] - 1));
                });
                buttonBox.getChildren().add(prevButton);
            }
            if (i < 4) {
                JFXButton nextButton = new JFXButton("Next");
                nextButton.setOnAction(event -> stage.setScene(surveyScenes.get(index[0] + 1)));
                buttonBox.getChildren().add(nextButton);
            }
            else {
                JFXButton submitButton = new JFXButton("Submit");
                submitButton.setOnAction(event -> {
                    System.out.println("Survey submitted!");
                });
                buttonBox.getChildren().add(submitButton);
            }

            if (surveyScenes.get(i).getRoot() instanceof Pane pane && pane.getChildren().getFirst() instanceof ScrollPane scrollPane && scrollPane.getContent() instanceof VBox vBox) {
                vBox.getChildren().add(questionBox);
                vBox.getChildren().add(buttonBox);
            }
        }
    }

    public static void main(String[] args) {
        launch();
    }
}