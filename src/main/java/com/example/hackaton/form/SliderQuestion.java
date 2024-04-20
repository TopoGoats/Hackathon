package com.example.hackaton.form;

import com.jfoenix.controls.JFXSlider;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;

import java.util.HashMap;
import java.util.Map;

public class SliderQuestion extends VBox implements FormQuestion {
    Label questionLabel;
    JFXSlider slider;
    Map<Double, String> labels;
    String influencedTrait;

    public SliderQuestion(String question, String minLabel, String maxLabel, String influencedTrait) {
        this.getStyleClass().add("question");
        this.setAlignment(javafx.geometry.Pos.CENTER);
        questionLabel = new Label(question);
        this.influencedTrait = influencedTrait;
        questionLabel.getStyleClass().add("question-label");

        slider = new JFXSlider(0, 10, 0);

        labels = new HashMap<>();
        labels.put(0.0, minLabel);
        labels.put(10.0, maxLabel);

        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(1);
        slider.setMinorTickCount(0);
        slider.setSnapToTicks(true);
        slider.valueProperty().addListener((obs, oldval, newVal) ->
                slider.setValue(newVal.intValue()));

        slider.setLabelFormatter(new StringConverter<>() {
            @Override
            public String toString(Double object) {
                return labels.getOrDefault(object, "");
            }

            @Override
            public Double fromString(String string) {
                return labels.entrySet().stream()
                        .filter(entry -> entry.getValue().equals(string))
                        .findFirst()
                        .map(Map.Entry::getKey)
                        .orElse(null);
            }
        });


        this.getChildren().addAll(questionLabel, slider);
    }

    @Override
    public int getAnswer() {
        return (int) slider.getValue();
    }

    @Override
    public String getInfluencedTrait() {
        return influencedTrait;
    }
}
