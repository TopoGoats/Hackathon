package com.example.hackaton.form;

import com.jfoenix.controls.JFXRadioButton;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class SingleChoiceQuestion extends VBox implements FormQuestion {
    Label questionLabel;
    ToggleGroup group;
    List<RadioButton> radioButtons = new ArrayList<>();
    public SingleChoiceQuestion(String question, List<String> options) {
        this.getStyleClass().add("question");
        questionLabel = new Label(question);
        questionLabel.getStyleClass().add("question-label");
        this.getChildren().addAll(questionLabel);

        group = new ToggleGroup();

        for (String option : options) {
            JFXRadioButton radioButton = new JFXRadioButton(option);
            radioButton.getStyleClass().add("answer");
            radioButton.setToggleGroup(group);
            radioButtons.add(radioButton);
            this.getChildren().add(radioButton);
        }
    }
}
