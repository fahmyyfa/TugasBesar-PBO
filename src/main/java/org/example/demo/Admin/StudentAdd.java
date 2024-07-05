package org.example.demo.Admin;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import org.example.demo.DarkLightMode;
import org.example.demo.Database.Database;
import org.example.demo.Database.Student;
import org.example.demo.Database.User;

import static org.example.demo.LoginAdmin.bgAll;

public class StudentAdd extends Application {

    @Override
    public void start(Stage primaryStage) {

        AnchorPane root = new AnchorPane();

        ImageView backgroundViews = new ImageView(new Image(bgAll));
        backgroundViews.setPreserveRatio(false);

        root.getChildren().add(backgroundViews);

        root.widthProperty().addListener((obs, oldVal, newVal) -> {
            backgroundViews.setFitWidth(newVal.doubleValue());
        });
        root.heightProperty().addListener((obs, oldVal, newVal) -> {
            backgroundViews.setFitHeight(newVal.doubleValue());
        });

        Label titleLabel = new Label("Add Student");
        titleLabel.setLayoutX(264.0);
        titleLabel.setLayoutY(34.0);
        titleLabel.setFont(new Font("System Bold", 36.0));

        Button backButton = new Button("Back");
        backButton.setLayoutX(14.0);
        backButton.setLayoutY(446.0);
        backButton.setPrefSize(119.0, 40.0);
        backButton.setFont(new Font("System Bold", 14.0));

        backButton.setOnAction(actionEvent -> {
            MenuAdmin menuAdmin = new MenuAdmin();
            menuAdmin.start(primaryStage);
        });

        Label nameLabel = new Label("Name");
        nameLabel.setLayoutX(74.0);
        nameLabel.setLayoutY(113.0);
        nameLabel.setFont(new Font(18.0));

        Label nimLabel = new Label("NIM");
        nimLabel.setLayoutX(74.0);
        nimLabel.setLayoutY(192.0);
        nimLabel.setFont(new Font(18.0));

        Label facultyLabel = new Label("Faculty");
        facultyLabel.setLayoutX(376.0);
        facultyLabel.setLayoutY(113.0);
        facultyLabel.setFont(new Font(18.0));

        Label programLabel = new Label("Program");
        programLabel.setLayoutX(378.0);
        programLabel.setLayoutY(192.0);
        programLabel.setFont(new Font(18.0));

        TextField nameField = new TextField();
        nameField.setLayoutX(82.0);
        nameField.setLayoutY(140.0);
        nameField.setPrefSize(257.0, 35.0);

        TextField nimField = new TextField();
        nimField.setLayoutX(82.0);
        nimField.setLayoutY(219.0);
        nimField.setPrefSize(257.0, 35.0);

        TextField facultyField = new TextField();
        facultyField.setLayoutX(387.0);
        facultyField.setLayoutY(140.0);
        facultyField.setPrefSize(257.0, 35.0);

        TextField programField = new TextField();
        programField.setLayoutX(387.0);
        programField.setLayoutY(219.0);
        programField.setPrefSize(257.0, 35.0);

        Label errorLabel = new Label();
        errorLabel.setLayoutX(88.0);
        errorLabel.setLayoutY(384.0);
        errorLabel.setPrefSize(537.0, 20.0);
        errorLabel.setTextFill(Color.RED);
        errorLabel.setFont(new Font(14.0));

        Button submitButton = new Button("Submit");
        submitButton.setLayoutX(274.0);
        submitButton.setLayoutY(302.0);
        submitButton.setPrefSize(165.0, 53.0);
        submitButton.setFont(new Font("System Bold", 18.0));

        submitButton.setOnAction(actionEvent -> {
            String name = nameField.getText();
            String nim = nimField.getText();
            String faculty = facultyField.getText();
            String program = programField.getText();

            if(name.isEmpty()) {
                errorLabel.setText("Name empty");
                showPopupNotification(primaryStage, "Name empty");
                return;
            }
            if(nim.isEmpty()) {
                errorLabel.setText("NIM empty");
                showPopupNotification(primaryStage, "NIM empty");
                return;
            }
            if(!nim.matches("\\d+")) {
                errorLabel.setText("NIM must be digits");
                showPopupNotification(primaryStage, "NIM must be digits");
                return;
            }
            if(nim.length() != 15) {
                errorLabel.setText("NIM must be 15 digits");
                showPopupNotification(primaryStage, "NIM must be 15 digits");
                return;
            }
            if(faculty.isEmpty()) {
                errorLabel.setText("Faculty empty");
                showPopupNotification(primaryStage, "Faculty empty");
                return;
            }
            if(program.isEmpty()) {
                errorLabel.setText("Program empty");
                showPopupNotification(primaryStage, "Program empty");
                return;
            }

            User.students.add(new Student(name, nim, faculty, program));
            Database.student_addStudent(nameField.getText(), nimField.getText(), facultyField.getText(), programField.getText());

            showPopupNotification(primaryStage, "Student added successfully!");
            MenuAdmin menuAdmin = new MenuAdmin();
            menuAdmin.start(primaryStage);
        });

        root.getChildren().addAll(
                titleLabel, backButton, nameLabel, nimLabel, facultyLabel, programLabel,
                nameField, nimField, facultyField, programField, errorLabel, submitButton
        );

        Scene scene = new Scene(root);
        primaryStage.setTitle("Add Student");
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitHint("");
        primaryStage.show();

        // Apply theme
        DarkLightMode.applyTheme(root);
    }

    private void showPopupNotification(Stage ownerStage, String message) {
        Popup popup = new Popup();
        popup.setAutoHide(true);

        VBox popupContent = new VBox();
        popupContent.setPadding(new Insets(10));
        popupContent.setStyle("-fx-background-color: #000000; -fx-background-radius: 10;");

        Text messageText = new Text(message);
        messageText.setFill(Color.WHITE);
        popupContent.getChildren().add(messageText);

        popup.getContent().add(popupContent);

        // Calculate position to show popup at the bottom center of the screen
        double xPos = ownerStage.getX() + (ownerStage.getWidth() / 2) - (popupContent.getWidth() / 2);
        double yPos = ownerStage.getY() + ownerStage.getHeight() - popupContent.getHeight() - 10;

        popup.show(ownerStage, xPos, yPos);

        // Hide popup after 3 seconds
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), event -> popup.hide()));
        timeline.setCycleCount(1);
        timeline.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}