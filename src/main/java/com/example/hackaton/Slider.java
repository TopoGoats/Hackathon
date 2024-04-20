package com.example.hackaton;

import com.jfoenix.controls.JFXSlider;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.StringConverter;

import java.util.HashMap;
import java.util.Map;

public class Slider extends VBox {
    JFXSlider slider;
    Map<Double, String> labels;

    public Slider(String text, double ideal, double current, boolean isMoreBad) {
        this.setAlignment(javafx.geometry.Pos.CENTER);
        this.setSpacing(5);
        Text label = new Text(text);
        label.getStyleClass().add("sub-heading");
        this.getChildren().add(label);

        slider = new JFXSlider(0, 100, 0);

        labels = new HashMap<>();
        labels.put(0.0, "0");
        labels.put(100.0, "100");

        slider.setShowTickLabels(true);
        slider.setShowTickMarks(false);
        slider.setSnapToTicks(true);
        slider.setPrefWidth(AnimalStats.panel_Width-40);
        slider.valueProperty().addListener((_, _, newVal) ->
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

        double normalizedDifference = Math.abs(ideal - current) * 10;
        if (normalizedDifference < 10) {
            normalizedDifference *= 10;
        }
        slider.setValue(normalizedDifference);
        slider.setMouseTransparent(true);
        this.getChildren().add(slider);

    }
}
