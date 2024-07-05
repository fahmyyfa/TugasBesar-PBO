package org.example.demo;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.Screen;
import javafx.geometry.Rectangle2D;
import javafx.util.Duration;
import org.example.demo.Database.Book;
import org.example.demo.Database.Database;
import org.example.demo.Database.Student;
import org.example.demo.Database.User;
import org.example.demo.Time.CurrentDateTimeApp;

public class Dashboard extends Application {

    public static boolean sudahTambah = false;

    private boolean isDarkMode = false;

    // Background paths
    private final String lightModeBackground = "file:src/main/java/org/example/demo/Image/bgLight.png";
    private final String darkModeBackground = "file:src/main/java/org/example/demo/Image/bgDark.png";



    public void addTempBooks() {
        User.books.add(new Book("388c-e681-9152", "title", "author", "History", 4));
        User.books.add(new Book("ed90-be30-5cdb", "title", "author", "Story", 0));
        User.books.add(new Book("d95e-0c4a-9523", "title", "author", "Text", 1));
    }

    @Override
    public void start(Stage primaryStage) {

        Database.student_database();


        BorderPane root = new BorderPane();

        ImageView backgroundView = new ImageView(new Image(lightModeBackground));
        backgroundView.setPreserveRatio(false);

        root.getChildren().add(backgroundView);

        root.widthProperty().addListener((obs, oldVal, newVal) -> {
            backgroundView.setFitWidth(newVal.doubleValue());
        });
        root.heightProperty().addListener((obs, oldVal, newVal) -> {
            backgroundView.setFitHeight(newVal.doubleValue());
        });

        // Top section
        HBox topSection = new HBox();
        topSection.setPadding(new Insets(10));
        topSection.setSpacing(25);
        topSection.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

        // Logo
        ImageView logo = new ImageView("file:src/main/java/org/example/demo/Image/logo.png");
        logo.setFitHeight(50);
        logo.setFitWidth(50);

        // Application name
        Label appName = new Label("E-Library");
        appName.setFont(new Font("System Bold", 38));

        // Sign in button with popup
        Button signInButton = new Button("Sign In");
        signInButton.setFont(new Font(14));
        signInButton.setBackground(Background.EMPTY);

        Popup signInPopup = createPopup(new Button[]{new Button("Sign In to Student"), new Button("Sign In to Admin")}, primaryStage, signInButton);
        signInButton.setOnMouseEntered(e -> {
            signInPopup.show(primaryStage, signInButton.localToScreen(signInButton.getBoundsInLocal()).getMinX(), signInButton.localToScreen(signInButton.getBoundsInLocal()).getMaxY());
        });

        // Language button with popup
        Button languageButton = new Button("Language");
        languageButton.setFont(new Font(14));
        languageButton.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));

        Popup languagePopup = createPopup(new Button[]{new Button("Indonesia"), new Button("Arabic")}, primaryStage, languageButton);
        languageButton.setOnMouseEntered(e -> {
            languagePopup.show(primaryStage, languageButton.localToScreen(languageButton.getBoundsInLocal()).getMinX(), languageButton.localToScreen(languageButton.getBoundsInLocal()).getMaxY());
        });

        // Help button with popup
        Button helpButton = new Button("Help");
        helpButton.setFont(new Font(14));
        helpButton.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));

        Popup helpPopup = createPopup(new Label[]{new Label(" No! \n Maybe Next Time Bro")}, primaryStage, helpButton);
        helpButton.setOnMouseEntered(e -> {
            helpPopup.show(primaryStage, helpButton.localToScreen(helpButton.getBoundsInLocal()).getMinX(), helpButton.localToScreen(helpButton.getBoundsInLocal()).getMaxY());
        });

        // Button to switch themes
        CircleButton switchButton = new CircleButton();
        switchButton.setOnAction(event -> {
            DarkLightMode.toggleTheme();
            DarkLightMode.applyTheme(root);
            isDarkMode = !isDarkMode;
            topSection.setBackground(new Background(new BackgroundFill(isDarkMode ? Color.BLACK : Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
            logo.setImage(new Image("file:src/main/java/org/example/demo/Image/logo" + (isDarkMode ? "_dark" : "") + ".png"));
            animateTransition(root, isDarkMode ? Color.WHITE : Color.BLACK, isDarkMode ? Color.BLACK : Color.WHITE, appName, switchButton, "file:src/main/java/org/example/demo/Image/" + (isDarkMode ? "moon" : "sun") + ".png");

            changeBackgroundForDarkMode(isDarkMode, backgroundView);
        });
        switchButton.setImage("file:src/main/java/org/example/demo/Image/sun.png");

        root.getChildren().add(switchButton);

        // DarkLight Mode
        DarkLightMode.applyTheme(root);

        // Label for current date and time
        Label dateTimeLabel = CurrentDateTimeApp.getCurrentDateTimeLabel();

        // Right side buttons
        HBox rightButtons = new HBox(10, signInButton, languageButton, helpButton, switchButton, dateTimeLabel);
        rightButtons.setPadding(new Insets(10));
        rightButtons.setAlignment(Pos.TOP_RIGHT);
        HBox.setHgrow(rightButtons, Priority.ALWAYS);

        topSection.getChildren().addAll(logo, appName, rightButtons);

        // Center section
        VBox centerSection = new VBox(20);
        centerSection.setPadding(new Insets(20));
        centerSection.setAlignment(Pos.CENTER);

        // Slogan section
        VBox sloganSection = new VBox(20);
        sloganSection.setPadding(new Insets(30));
        sloganSection.setAlignment(Pos.CENTER);

        Label sloganLabel = new Label("Welcome to E-Library!");
        sloganLabel.setFont(new Font("System Bold", 48));

        Button centerSignInButton = new Button("Sign In");
        centerSignInButton.setFont(new Font(14));
        centerSignInButton.setBackground(Background.EMPTY);
        centerSignInButton.setOnAction(e -> {
            LoginStudent loginStudent = new LoginStudent();
            loginStudent.start(new Stage());
        });

        sloganSection.getChildren().addAll(sloganLabel, centerSignInButton);

        centerSection.getChildren().addAll(sloganSection);

        root.setTop(topSection);
        root.setCenter(centerSection);

        // Mengambil ukuran layar utama
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX(screenBounds.getMinX());
        primaryStage.setY(screenBounds.getMinY());
        primaryStage.setWidth(screenBounds.getWidth());
        primaryStage.setHeight(screenBounds.getHeight());

        Scene scene = new Scene(root, screenBounds.getWidth(), screenBounds.getHeight());
        primaryStage.setTitle("Perpustakaanku");
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitHint("");
        primaryStage.show();

        // Make the layout responsive
        scene.widthProperty().addListener((obs, oldVal, newVal) -> {
            double width = newVal.doubleValue();
            if (width > 600) {
                topSection.setSpacing(10);
                sloganSection.setPadding(new Insets(10));
            } else {
                topSection.setSpacing(20);
                sloganSection.setPadding(new Insets(50));
            }
        });
    }

    private void changeBackgroundForDarkMode(boolean isDarkMode, ImageView backgroundView) {
        backgroundView.setImage(new Image(isDarkMode ? darkModeBackground : lightModeBackground));
    }

    private void animateTransition(BorderPane root, Color fromColor, Color toColor, Label appName, CircleButton switchButton, String iconPath) {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(300), root);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);
        fadeTransition.setOnFinished(event -> {
            root.setBackground(new Background(new BackgroundFill(toColor, CornerRadii.EMPTY, Insets.EMPTY)));
            appName.setTextFill(fromColor);
            switchButton.setImage(iconPath);
            FadeTransition fadeInTransition = new FadeTransition(Duration.millis(300), root);
            fadeInTransition.setFromValue(0.0);
            fadeInTransition.setToValue(1.0);
            fadeInTransition.play();
        });
        fadeTransition.play();
    }

    private Popup createPopup(Object[] contents, Stage primaryStage, Button triggerButton) {
        Popup popup = new Popup();
        VBox popupContent = new VBox(10);
        popupContent.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(5), Insets.EMPTY)));
        popupContent.setPadding(new Insets(10));

        for (Object content : contents) {
            if (content instanceof Button) {
                Button button = (Button) content;
                button.setPrefWidth(150);
                button.setOnAction(e -> {
                    if (button.getText().contains("Student")) {
                        LoginStudent loginStudent = new LoginStudent();
                        loginStudent.start(new Stage());
                    } else if (button.getText().contains("Admin")) {
                        LoginAdmin loginAdmin = new LoginAdmin();
                        loginAdmin.start(new Stage());
                    }
                    popup.hide();
                });
                popupContent.getChildren().add(button);
            } else if (content instanceof Label) {
                popupContent.getChildren().add((Label) content);
            }
        }

        popup.getContent().add(popupContent);

        popupContent.setOnMouseExited(e -> {
            popup.hide();
        });


        return popup;
    }


    public static void main(String[] args) {
        launch(args);
    }

    class CircleButton extends Button {
        private ImageView imageView;

        public CircleButton() {
            imageView = new ImageView();
            setGraphic(imageView);
            setBackground(Background.EMPTY); // Menghilangkan latar belakang
            setBorder(Border.EMPTY); // Menghilangkan batas
            setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
            setMinSize(40, 40);
            setMaxSize(40, 40);
        }

        public void setImage(String imagePath) {
            Image image = new Image(imagePath);
            imageView.setImage(image);
            imageView.setFitWidth(40);
            imageView.setFitHeight(40);
        }
    }
}