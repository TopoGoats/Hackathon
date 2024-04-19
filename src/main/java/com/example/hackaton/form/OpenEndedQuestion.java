package com.example.hackaton.form;

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

}
