package com.example.hackaton.form;

import com.jfoenix.controls.JFXCheckBox;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class MultipleChoiceQuestion extends VBox implements FormQuestion {
    Label questionLabel;
    List<JFXCheckBox> checkBoxes = new ArrayList<>();
    public MultipleChoiceQuestion(String question, List<String> options) {
        this.getStyleClass().add("question");
        questionLabel = new Label(question);
        questionLabel.getStyleClass().add("question-label");
        this.getChildren().addAll(questionLabel);

        for (String option : options) {
            JFXCheckBox checkBox = new JFXCheckBox(option);
            checkBox.getStyleClass().add("our-checkbox");
            checkBoxes.add(checkBox);
            this.getChildren().add(checkBox);
        }
    }
}
