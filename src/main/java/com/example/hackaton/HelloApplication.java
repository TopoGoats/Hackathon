package com.example.hackaton;

import com.example.hackaton.form.*;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

import static com.example.hackaton.ChatGPTController.chatGPT;

public class HelloApplication extends Application {
    public static Pane root = new Pane();
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;
    public static List<FormQuestion> questions = new ArrayList<>();
    public static List<Pane> surveyRoots = new ArrayList<>();
    public static List<Scene> surveyScenes = new ArrayList<>();
    public static Stage stage;

    @Override
    public void start(Stage stage1) {
        stage = stage1;
        Scene scene = new Scene(root, WIDTH, HEIGHT);

        // Create 4 survey scenes for survey questions
        for (int i = 0; i < 5; i++) {
            Pane surveyRoot = new Pane();
            surveyRoots.add(surveyRoot);
            surveyScenes.add(new Scene(surveyRoot, WIDTH, HEIGHT));
        }

//        System.out.println(chatGPT("give me the definition af a dog"));

        Button button = new Button("Start survey");
        button.setLayoutX(100);
        button.setLayoutY(100);
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
        for (FormQuestion question : questions) {
            root.getChildren().remove(question);
        }
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
            new SingleChoiceQuestion("Czy w twoim otoczeniu przebuwają dzieci?", List.of("Tak", "Nie")),
            new SingleChoiceQuestion("Czego \"oczekujesz\" od zwierzaka?", List.of("Towarzystwa", "Motywacji do aktywności", "Chcę go szkolić", "Ma dobrze smakować")),
            new MultipleChoiceQuestion("Wybierz pięć cech, które do ciebie pasują:", List.of("Odpowiedzialny", "Leniwy", "Pozytywny", "Ładwo się irytuję", "Zapominalski", "Aktywny", "Słomiany zapał", "Zdeterminowany", "Pochmurny", "Masło")),
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
            questionBox.setLayoutX(WIDTH / 2.0 - 200);
            questionBox.setLayoutY(100);
            for (int j = 0; j < 5; j++) {
                questionBox.getChildren().add((Node) questions.get(i * 5 + j));
            }

            // javafx shenanigans
            int[] index = {i};

            HBox buttonBox = new HBox();
            if (i > 0) {
                Button prevButton = new Button("Previous");
                prevButton.setLayoutX(WIDTH / 2.0 - 150);
                prevButton.setLayoutY(HEIGHT - 100);
                prevButton.setOnAction(event -> stage.setScene(surveyScenes.get(index[0] - 1)));
                buttonBox.getChildren().add(prevButton);
            }
            if (i < 4) {
                Button nextButton = new Button("Next");
                nextButton.setLayoutX(WIDTH / 2.0 - 50);
                nextButton.setLayoutY(HEIGHT - 100);
                nextButton.setOnAction(event -> stage.setScene(surveyScenes.get(index[0] + 1)));
                buttonBox.getChildren().add(nextButton);
            }
            else {
                Button submitButton = new Button("Submit");
                submitButton.setLayoutX(WIDTH / 2.0 - 50);
                submitButton.setLayoutY(HEIGHT - 100);
                submitButton.setOnAction(event -> {
                    System.out.println("Survey submitted!");
                });
                buttonBox.getChildren().add(submitButton);
            }

            questionBox.getChildren().add(buttonBox);
            surveyRoots.get(i).getChildren().add(questionBox);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}