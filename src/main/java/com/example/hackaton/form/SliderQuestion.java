package com.example.hackaton.form;

import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;

import java.util.HashMap;
import java.util.Map;

public class SliderQuestion extends VBox implements FormQuestion {
    Label questionLabel;
    Slider slider;
    Map<Double, String> labels;

    public SliderQuestion(String question, String minLabel, String maxLabel) {
        questionLabel = new Label(question);

        slider = new Slider(0, 10, 0);

        labels = new HashMap<>();
        labels.put(0.0, minLabel);
        labels.put(10.0, maxLabel);

        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(1);
        slider.setMinorTickCount(0);
        slider.setSnapToTicks(true);

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
}
