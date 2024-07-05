package org.example.demo.Time;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CurrentDateTimeApp extends Application {

    @Override
    public void start(Stage primaryStage) {

        Label dateTimeLabel = new Label();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            LocalDateTime currentDateTime = LocalDateTime.now();
            String formattedDateTime = currentDateTime.format(formatter);
            dateTimeLabel.setText(formattedDateTime);
        }));

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        StackPane root = new StackPane(dateTimeLabel);
        Scene scene = new Scene(root, 300, 200);

        primaryStage.setTitle("Current Date and Time");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static Label getCurrentDateTimeLabel() {
        Label dateTimeLabel = new Label();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            LocalDateTime currentDateTime = LocalDateTime.now();
            String formattedDateTime = currentDateTime.format(formatter);
            dateTimeLabel.setText(formattedDateTime);
        }));

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        return dateTimeLabel;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
