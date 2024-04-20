package com.example.hackaton.form;

import com.jfoenix.controls.JFXTextArea;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class OpenEndedQuestion extends VBox implements FormQuestion {
    Label questionLabel;
    JFXTextArea textArea;

    public OpenEndedQuestion(String question) {
        VBox wrapper = new VBox();
        textArea = new JFXTextArea();
        wrapper.getChildren().add(textArea);
        wrapper.getStyleClass().add("our-answers-box");
        this.getStyleClass().add("question");
        questionLabel = new Label(question);
        questionLabel.getStyleClass().add("question-label");

        this.getChildren().addAll(questionLabel, wrapper);
    }

    @Override
    public int getAnswer() {
        return -2137;
    }

    public String getQuestion() {
        return questionLabel.getText();
    }
    public String getActualAnswer() {
        return textArea.getText();
    }

    @Override
    public String getInfluencedTrait() {
        return "\033[0;31m" + "WARNING WARNING THIS SHOULDN'T BE VISIBLE" + "\033[0m";
    }
}
