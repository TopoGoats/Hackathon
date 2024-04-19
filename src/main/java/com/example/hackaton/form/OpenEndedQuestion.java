package com.example.hackaton.form;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class OpenEndedQuestion extends VBox implements FormQuestion {
    Label questionLabel;
    TextField textField;

    public OpenEndedQuestion(String question) {
        questionLabel = new Label(question);
        textField = new TextField();

        this.getChildren().addAll(questionLabel, textField);
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
