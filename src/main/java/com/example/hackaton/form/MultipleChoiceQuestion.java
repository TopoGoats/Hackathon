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
}
