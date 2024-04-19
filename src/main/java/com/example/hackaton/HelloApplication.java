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

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HelloApplication extends Application {
    public static Pane root = new Pane();
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;
    public static List<FormQuestion> questions = new ArrayList<>();
    public static List<Pane> surveyRoots = new ArrayList<>();
    public static List<Scene> surveyScenes = new ArrayList<>();
    public static Stage stage;
    public static HashMap<String, Integer> traits = new HashMap<>();
    public static Animal idealAnimal;

    @Override
    public void start(Stage stage1) {
        DatabaseController.connectToDatabase();
        stage = stage1;
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        setupAttributes();

        // Create 4 survey scenes for survey questions
        for (int i = 0; i < 5; i++) {
            Pane surveyRoot = new Pane();
            surveyRoots.add(surveyRoot);
            surveyScenes.add(new Scene(surveyRoot, WIDTH, HEIGHT));
        }

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
            new SingleChoiceQuestion("Ile średnio masz czasu dziennie?", List.of("Prawie wcale (1-2 godziny)", "Średnio (3-4 godziny)", "Dużo (5-6 godzin)", "Zawsze mam wolne (7+ godzin)"), "freeTime"),
            new SingleChoiceQuestion("Jaki styl życia prowadzisz?", List.of("Nie wychodzę z domu", "Czasem wyjdę na spacer", "Dość aktywny", "Aktywny"), "activeLifestyle"),
            new SingleChoiceQuestion("W jakim miejscu mieszkasz?", List.of("Centrum miasta", "Przedmieścia", "Wieś", "Wyrwa czasoprzestrzenna"), "livingArea"),
            new SingleChoiceQuestion("W jakim domu mieszkasz?", List.of("Małe mieszkanie", "Bliźniak", "Dom wolnostojący", "Pełnym cudzych żon, słyszysz?"), "houseType"),
            new SingleChoiceQuestion("Ilu masz domowników?", List.of( "7+", "4-6", "2-3", "Tylko ja"), "housemateCount"),
            // break
            new SingleChoiceQuestion("Ile masz lat?", List.of("Poniżej 10", "11-17", "18-24", "Ponad 25"), "qustionareeAge"),
            new SingleChoiceQuestion("Czy masz już jakieś zwierzęta? Ile?", List.of("Nie", "1-2", "3-4", "5+"), "currentAnimals"),
            new SingleChoiceQuestion("Czy w twoim otoczeniu przebywają dzieci?", List.of("Tak", "Nie"), "children"),
            new SingleChoiceQuestion("Czego \"oczekujesz\" od zwierzaka?", List.of( "Ma dobrze smakować", "Towarzystwa", "Motywacji do aktywności", "Chcę go szkolić"), "animalsActivity"),
            new SingleChoiceQuestion("Jaką płeć preferujesz?", List.of("Samiec", "Samica", "Obojętnie", "Inne"), "preferredSex"),
            // break
                new MultipleChoiceQuestion("Wybierz pięć cech, które do ciebie pasują:", List.of("Odpowiedzialny", "Leniwy", "Pozytywny", "Łatwo się irytuję", "Zapominalski", "Aktywny", "Słomiany zapał", "Zdeterminowany", "Optymistyczny", "Masło")),
                new SliderQuestion("Ile czasu jesteś w stanie poświęcić zwierzęciu?", "Prawie wcale", "Cały swój czas", "careTimeNeeded"),
                new SliderQuestion("Jak zaradny jesteś?", "Niezaradny", "Bardzo zaradny", "resourcefulness"),
                new SliderQuestion("Jak oceniasz swoje kompetencje w opiekowaniu się zwierzętami?", "Brak kompetencji", "Mistrz opieki", "competentWithAnimals"),
                new SliderQuestion("Jak bardzo porywczy jesteś?", "Totalny chillout", "Dzika bestia", "impulsiveness"),
                // break
            new SliderQuestion("Jakie są twoje zarobki?", "Bezdomny", "Milioner", "income"),
            new SliderQuestion("Czy masz ogród? Jak duży?", "Nie mam", "Gigantyczny", "gardenSize"),
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
                    String[] ownerTraits = {""};
                    questions.forEach(question -> {
                        if (question instanceof MultipleChoiceQuestion question1) {
                            ownerTraits[0] = question1.getActualAnswer();
                        }
                        traits.put(question.getInfluencedTrait() , question.getAnswer());
                    });
                    traits.forEach((key, value) -> {
                        if (value == -1) {
                            System.out.println("You didn't answer all questions!");
                            return;
                        }

                        idealAnimal = new Animal(
                                true,
                                "skibidi ",
                                "Dog",
                                10,
                                "image.png",
                                traits.get("careTimeNeeded"),
                                traits.get("resourcefulness"),
                                traits.get("competentWithAnimals"),
                                traits.get("impulsiveness"),
                                traits.get("income"),
                                traits.get("gardenSize"),
                                traits.get("freeTime"),
                                traits.get("activeLifestyle"),
                                traits.get("livingArea"),
                                traits.get("currentAnimals"),
                                traits.get("houseType"),
                                traits.get("housemateCount"),
                                traits.get("qustionareeAge"),
                                traits.get("children"),
                                traits.get("animalsActivity"),
                                traits.get("preferredSex"),
                                ownerTraits[0]
                        );
                        System.out.println(idealAnimal.toString());
                    });
                });
                buttonBox.getChildren().add(submitButton);
            }

            questionBox.getChildren().add(buttonBox);
            surveyRoots.get(i).getChildren().add(questionBox);
        }
    }

    public static void setupAttributes() {
        // Sliders
        traits.put("careTimeNeeded", -1);
        traits.put("resourcefulness", -1);
        traits.put("competentWithAnimals", -1);
        traits.put("impulsiveness", -1);
        traits.put("income", -1);
        traits.put("gardenSize", -1);

        // Single choice
        traits.put("freeTime", -1);
        traits.put("activeLifestyle", -1);
        traits.put("livingArea", -1);
        traits.put("currentAnimals", -1);
        traits.put("houseType", -1);
        traits.put("housemateCount", -1);
        traits.put("qustionareeAge", -1);
        traits.put("children", -1);
        traits.put("animalsActivity", -1);
        traits.put("preferredSex", -1);

        // Traits
        traits.put("responsibility", -1);
        traits.put("forgetfulness", -1);
        traits.put("shortLivedEnthusiasm", -1);
        traits.put("determined", -1);
        traits.put("optimistic", -1);
    }

    public static void main(String[] args) {
        launch();
    }
}