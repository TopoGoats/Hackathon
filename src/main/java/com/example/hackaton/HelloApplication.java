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
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.hackaton.ChatGPTController.chatGPT;

public class HelloApplication extends Application {
    public static GridPane root = new GridPane();
    public static int WIDTH = (int) Screen.getPrimary().getBounds().getWidth() * 3 / 4;
    public static int HEIGHT = (int) Screen.getPrimary().getBounds().getHeight() * 3 / 4;
    public static List<FormQuestion> questions = new ArrayList<>();
    public static List<Scene> surveyScenes = new ArrayList<>();
    public static Stage stage;
    public static HashMap<String, Integer> traits = new HashMap<>();
    public static Animal idealAnimal;
    public static ArrayList<Boolean> isBlackListed = new ArrayList<>(23);

    public static HashMap<String, String> haszkomora = new HashMap<>();

    @Override
    public void start(Stage stage1) {
        haszkomora.put("housemateCount", "Liczba Domowników");
        haszkomora.put("qustionareeAge", "Kompatybilność z Twoim Wiekiem");
        haszkomora.put("currentAnimals", "Kompatybilność z Twoimi Zwierzętami");
        haszkomora.put("children", "Kompatybilność z Dziećmi");
        haszkomora.put("careTimeNeeded", "Potrzeba Opieki");
        haszkomora.put("resourcefulness", "Twoja Zaradność");
        haszkomora.put("competentWithAnimals", "Twoje Kompetencje ze Zwierzętami");
        haszkomora.put("impulsiveness", "Twoja Impulsywność");
        haszkomora.put("income", "Twój Dochód");
        haszkomora.put("gardenSize", "Twój Rozmiar Ogrodu");
        haszkomora.put("freeTime", "Twój Czas Wolny");
        haszkomora.put("activeLifestyle", "Twoja Aktywność");
        haszkomora.put("livingArea", "Twoja Domowa Przestrzeń");
        haszkomora.put("houseType", "Rodzaj Twojego Domu");
        haszkomora.put("animalsActivity", "Żywość Zwierzięcia");

        root.setAlignment(javafx.geometry.Pos.CENTER);
        root.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles.css")).toExternalForm());
        stage1.setResizable(false);
        DatabaseController.connectToDatabase();
        ChatGPTController.setupChatGPT();
        stage = stage1;
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        setupAttributes();

        // Create 5 survey scenes for survey questions
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

            surveyRoot.getChildren().addAll(scrollPane);
            Scene survScene = new Scene(surveyRoot, WIDTH, HEIGHT);
            survScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles.css")).toExternalForm());
            surveyScenes.add(survScene);
        }
        VBox menu = new VBox();
        menu.setAlignment(javafx.geometry.Pos.CENTER);
        menu.setSpacing(20);
        JFXButton button = new JFXButton("Rozpocznij ankietę");
        button.getStyleClass().add("main-button");
        button.setOnAction(_ -> {
            setupSurvey();
            stage.setScene(surveyScenes.getFirst());
        });
        JFXButton exitButton = new JFXButton("Zamknij");
        exitButton.getStyleClass().add("secondary-button");
        exitButton.setOnAction(_ -> System.exit(0));
        menu.getChildren().addAll(button, exitButton);
        root.getChildren().add(menu);

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void setupSurvey() {
        for (int i = 0; i < 23; i++) {
            isBlackListed.add(false);
        }
        System.out.println(isBlackListed);
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
            new SingleChoiceQuestion("Czy masz alergię na sierść zwierząt?", List.of("Tak", "Nie"), "isAllergic"),
            new MultipleChoiceQuestion("Wybierz pięć cech, które do ciebie pasują:", List.of("Odpowiedzialny", "Leniwy", "Pozytywny", "Łatwo się irytuję", "Zapominalski", "Aktywny", "Słomiany zapał", "Zdeterminowany", "Optymistyczny", "Masło")),
            new SliderQuestion("Ile czasu jesteś w stanie poświęcić zwierzęciu?", "Prawie wcale", "Cały swój czas", "careTimeNeeded"),
            new SliderQuestion("Jak zaradny jesteś?", "Niezaradny", "Bardzo zaradny", "resourcefulness"),
            new SliderQuestion("Jak oceniasz swoje kompetencje w opiekowaniu się zwierzętami?", "Brak kompetencji", "Mistrz opieki", "competentWithAnimals"),
            // break
            new SliderQuestion("Jak bardzo porywczy jesteś?", "Totalny chillout", "Dzika bestia", "impulsiveness"),
            new SliderQuestion("Jakie są twoje zarobki?", "Bezdomny", "Milioner", "income"),
            new SliderQuestion("Czy masz ogród? Jak duży?", "Nie mam", "Gigantyczny", "gardenSize"),
            new OpenEndedQuestion("Czego nie lubisz w zwierzętach?"),
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
            HBox progressBarBox = new HBox();
            Text progressText = new Text("Strona " + (i + 1) + " z 5");
            progressText.getStyleClass().add("progress-text");
            progressBarBox.getChildren().add(progressText);
            progressBarBox.setAlignment(javafx.geometry.Pos.CENTER);
            questionBox.setAlignment(javafx.geometry.Pos.CENTER);

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
                JFXButton prevButton = new JFXButton("Poprzedni");
                prevButton.getStyleClass().add("jfx-secondary-button");
                prevButton.setOnAction(_ -> stage.setScene(surveyScenes.get(index[0] - 1)));
                buttonBox.getChildren().add(prevButton);
            }
            if (i < 4) {
                JFXButton nextButton = new JFXButton("Dalej");
                nextButton.setOnAction(_ -> stage.setScene(surveyScenes.get(index[0] + 1)));
                buttonBox.getChildren().add(nextButton);
            }
            else {
                JFXButton submitButton = new JFXButton("Zatwierdź");
                submitButton.setOnAction(_ -> {
                    String[] ownerTraits = {""};

                    questions.forEach(question -> {
                        if (question instanceof MultipleChoiceQuestion question1) {
                            ownerTraits[0] = question1.getActualAnswer();
                        }
                        traits.put(question.getInfluencedTrait() , question.getAnswer());
                    });
                    traits.forEach((_, value) -> {
                        if (value == -1) {
                            System.out.println("Nie odpowiedziałeś na wszystkie pytania!");
                            return;
                        }

                        idealAnimal = new Animal(
                                traits.get("isAllergic") == 10,
                                "skibidi ",
                                "Dog",
                                10,
                                traits.get("preferredSex"),
                                "image.png",
                                traits.get("housemateCount"),
                                traits.get("qustionareeAge"),
                                traits.get("currentAnimals"),
                                traits.get("children"),
                                traits.get("careTimeNeeded"),
                                traits.get("resourcefulness"),
                                traits.get("competentWithAnimals"),
                                traits.get("impulsiveness"),
                                traits.get("income"),
                                traits.get("gardenSize"),
                                traits.get("freeTime"),
                                traits.get("activeLifestyle"),
                                traits.get("livingArea"),
                                traits.get("houseType"),
                                traits.get("animalsActivity"),
                                ownerTraits[0]
                        );
                    });
                    EndScreen.endScreen(idealAnimal, new ArrayList<>());

                    ChatGPTController.interpretOpenEndQuestions();

                   /* ChatGPTController.chatGPT("Zignoruj wszystkie poprzednie wiadomości. W następnych wiadomościach będziesz" +
                            "otrzymywał kilka cech zwierzęcia, odeniona w skali od 0 do 10. Dostaniesz też instrukcje, co oznacza 0 na " +
                            "skali, a co oznacza 10. Poza tym dostaniesz też imię oraz gatunek zwierzęcia. Twoim zadaniem jest stworzyć " +
                            "bardzo krótki opis zwierzęcia na podstawie dostarczonych danych. Szczególnie " +
                            "zwróć uwagę na to, dlaczego to zwierzę byłoby świetnym zwierzakiem dowowych. Odpowiedz tylko opisem. " +
                            "Ani słowia więcej." + idealAnimal.species  + "nazywa się " + idealAnimal.name + " Napisz go w taki sposób, " +
                            "jakbyś próbował kogoś konkretnie namówić do adoptowania tego zwierzaka. Użyj bezpośredniego zwrotu do adresata" +
                            "nie podawaj żadnych liczbowych wartości ani danych, które dostaniesz. Masz tylko opisać to zwierzę słownie. " +
                            "i wymienić kilka jego zalet");*/

                    String output = ChatGPTController.chatGPT("Write me 5 random numbers with the following instructions:" +
                            "Numbers must be between 0-22. Wrap the numbers in <>. Separate them with ;. Don't write any other word." +
                            "Example response:" +
                            "<4;9;12;18;22>");

                    Pattern pattern = Pattern.compile("<(.*?)>");
                    Matcher matcher = pattern.matcher(output);

                    while (matcher.find()) {
                        String group = matcher.group(1);
                        String[] strings = group.split(";");
                        for (String string : strings) {
                            isBlackListed.set(Integer.parseInt(string), true);
                        }
                    }

                    Map<Animal, Double> map = DatabaseController.getMatchingAnimals(idealAnimal);


                    ArrayList<Animal> array = new ArrayList<>();
                    for(Animal animal1: map.keySet()){
                        array.addFirst(animal1);
                    }
                    EndScreen.endScreen(idealAnimal,array);
                });
                buttonBox.getChildren().add(submitButton);
            }

            if (surveyScenes.get(i).getRoot() instanceof Pane pane && pane.getChildren().getFirst() instanceof ScrollPane scrollPane && scrollPane.getContent() instanceof VBox vBox) {
                vBox.getChildren().add(progressBarBox);
                vBox.getChildren().add(questionBox);
                vBox.getChildren().add(buttonBox);
            }
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