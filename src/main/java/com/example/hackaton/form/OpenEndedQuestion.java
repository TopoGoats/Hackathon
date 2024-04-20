package com.example.hackaton.form;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
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

}
