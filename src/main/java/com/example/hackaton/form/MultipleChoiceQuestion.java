package com.example.hackaton.form;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class MultipleChoiceQuestion extends VBox implements FormQuestion {
    Label questionLabel;
    List<CheckBox> checkBoxes = new ArrayList<>();
    public MultipleChoiceQuestion(String question, List<String> options) {
        questionLabel = new Label(question);
        this.getChildren().addAll(questionLabel);

        for (String option : options) {
            CheckBox checkBox = new CheckBox(option);
            checkBoxes.add(checkBox);
            this.getChildren().add(checkBox);
        }
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
