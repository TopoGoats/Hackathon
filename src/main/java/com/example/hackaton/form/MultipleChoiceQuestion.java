package com.example.hackaton.form;

import com.jfoenix.controls.JFXCheckBox;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class MultipleChoiceQuestion extends VBox implements FormQuestion {
    Label questionLabel;
    List<JFXCheckBox> checkBoxes = new ArrayList<>();
    public MultipleChoiceQuestion(String question, List<String> options) {
        this.getStyleClass().add("question");
        this.setAlignment(javafx.geometry.Pos.CENTER);
        questionLabel = new Label(question);
        questionLabel.getStyleClass().add("question-label");
        this.getChildren().addAll(questionLabel);
        VBox vBox1 = new VBox();
        VBox vBox2 = new VBox();

        for (String option : options) {
            if (vBox1.getChildren().size() < 5) {
                JFXCheckBox checkBox = new JFXCheckBox(option);
                checkBox.getStyleClass().add("our-checkbox");
                checkBoxes.add(checkBox);
                vBox1.getChildren().add(checkBox);
            } else {
                JFXCheckBox checkBox = new JFXCheckBox(option);
                checkBox.getStyleClass().add("our-checkbox");
                checkBoxes.add(checkBox);
                vBox2.getChildren().add(checkBox);
            }
        }
        HBox hBox = new HBox(vBox1, vBox2);
        hBox.setSpacing(10);
        hBox.setMaxSize(200, 200);
        this.getChildren().add(hBox);
    }

    public String getActualAnswer() {
        StringBuilder answer = new StringBuilder();
        for (CheckBox checkBox : checkBoxes) {
            if (checkBox.isSelected()) {
                answer.append(checkBox.getText()).append(", ");
            }
        }
        return answer.toString();
    }

    @Override
    public int getAnswer() {
        return -2137;
    }

    @Override
    public String getInfluencedTrait() {
        return "\033[0;31m" + "WARNING WARNING THIS SHOULDN'T BE VISIBLE" + "\033[0m";
    }
}
