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
    String influencedTrait;

    public SingleChoiceQuestion(String question, List<String> options, String influencedTrait) {
        this.getStyleClass().add("question");
        questionLabel = new Label(question);
        questionLabel.getStyleClass().add("question-label");
        this.getChildren().addAll(questionLabel);
        this.influencedTrait = influencedTrait;

        group = new ToggleGroup();

        VBox answersBox = new VBox();
        for (String option : options) {
            JFXRadioButton radioButton = new JFXRadioButton(option);
            radioButton.getStyleClass().add("answer");
            radioButton.setToggleGroup(group);
            radioButtons.add(radioButton);
            answersBox.getChildren().add(radioButton);
        }
        answersBox.getStyleClass().add("our-answers-box");
        this.getChildren().add(answersBox);
    }

    @Override
    public int getAnswer() {
        RadioButton selectedRadioButton = (RadioButton) group.getSelectedToggle();
        if (radioButtons.size() == 2) {
            if (radioButtons.indexOf(selectedRadioButton) == 0) return 0;
            if (radioButtons.indexOf(selectedRadioButton) == 1) return 10;
        }
        else {
            if (radioButtons.indexOf(selectedRadioButton) == 0) return 0;
            if (radioButtons.indexOf(selectedRadioButton) == 1) return 3;
            if (radioButtons.indexOf(selectedRadioButton) == 2) return 7;
            if (radioButtons.indexOf(selectedRadioButton) == 3) return 10;
        }
        return -1;
    }

    @Override
    public String getInfluencedTrait() {
        return influencedTrait;
    }
}
