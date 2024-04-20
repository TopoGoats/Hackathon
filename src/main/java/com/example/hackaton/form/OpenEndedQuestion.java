package com.example.hackaton.form;

import com.jfoenix.controls.JFXTextArea;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class OpenEndedQuestion extends VBox implements FormQuestion {
    Label questionLabel;
    JFXTextArea textArea;

    public OpenEndedQuestion(String question) {
        this.getStyleClass().add("question");
        questionLabel = new Label(question);
        questionLabel.getStyleClass().add("question-label");
        textArea = new JFXTextArea();

        this.getChildren().addAll(questionLabel, textArea);
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
