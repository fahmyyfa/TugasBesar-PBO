package org.example.demo.Admin;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
import javafx.geometry.Insets;
import org.example.demo.DarkLightMode;
import org.example.demo.Database.Book;
import org.example.demo.Database.Database;
import org.example.demo.Database.User;

import java.util.UUID;

import static org.example.demo.LoginAdmin.bgAll;

public class BookAdd extends Application {

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

        // Label List Books
        Label listBooksLabel = new Label("Add Book");
        listBooksLabel.setFont(new Font("System Bold", 36));
        AnchorPane.setLeftAnchor(listBooksLabel, 264.0);
        AnchorPane.setTopAnchor(listBooksLabel, 34.0);

        // Back Button
        Button backButton = new Button("Back");
        backButton.setFont(new Font("System Bold", 14));
        backButton.setPrefSize(119, 40);
        AnchorPane.setLeftAnchor(backButton, 14.0);
        AnchorPane.setTopAnchor(backButton, 446.0);

        backButton.setOnAction(actionEvent -> {
            MenuAdmin menuAdmin = new MenuAdmin();
            menuAdmin.start(primaryStage);
        });

        // Title Label
        Label titleLabel = new Label("Title");
        titleLabel.setFont(new Font(18));
        AnchorPane.setLeftAnchor(titleLabel, 74.0);
        AnchorPane.setTopAnchor(titleLabel, 113.0);

        // Author Label
        Label authorLabel = new Label("Author");
        authorLabel.setFont(new Font(18));
        AnchorPane.setLeftAnchor(authorLabel, 74.0);
        AnchorPane.setTopAnchor(authorLabel, 192.0);

        // Category Label
        Label categoryLabel = new Label("Category");
        categoryLabel.setFont(new Font(18));
        AnchorPane.setLeftAnchor(categoryLabel, 419.0);
        AnchorPane.setTopAnchor(categoryLabel, 113.0);

        // Stock Label
        Label stockLabel = new Label("Stock");
        stockLabel.setFont(new Font(18));
        AnchorPane.setLeftAnchor(stockLabel, 421.0);
        AnchorPane.setTopAnchor(stockLabel, 192.0);

        // Title TextField
        TextField titleTextField = new TextField();
        titleTextField.setPrefSize(257, 35);
        AnchorPane.setLeftAnchor(titleTextField, 82.0);
        AnchorPane.setTopAnchor(titleTextField, 140.0);

        // Author TextField
        TextField authorTextField = new TextField();
        authorTextField.setPrefSize(257, 35);
        AnchorPane.setLeftAnchor(authorTextField, 82.0);
        AnchorPane.setTopAnchor(authorTextField, 219.0);

        // Category ComboBox
        ComboBox<String> categoryComboBox = new ComboBox<>();
        categoryComboBox.setPrefSize(201, 35);
        AnchorPane.setLeftAnchor(categoryComboBox, 430.0);
        AnchorPane.setTopAnchor(categoryComboBox, 140.0);
        categoryComboBox.getItems().addAll("History", "Story", "Text");
        categoryComboBox.getSelectionModel().selectFirst();

        // Stock TextField
        TextField stockTextField = new TextField();
        stockTextField.setPrefSize(201, 35);
        AnchorPane.setLeftAnchor(stockTextField, 430.0);
        AnchorPane.setTopAnchor(stockTextField, 219.0);

        // Error Label
        Label errorLabel = new Label();
        errorLabel.setFont(new Font(14));
        errorLabel.setTextFill(Color.RED);
        errorLabel.setPrefSize(537, 20);
        AnchorPane.setLeftAnchor(errorLabel, 88.0);
        AnchorPane.setTopAnchor(errorLabel, 384.0);

        // Submit Button
        Button submitButton = new Button("Submit");
        submitButton.setFont(new Font("System Bold", 18));
        submitButton.setPrefSize(165, 53);
        AnchorPane.setLeftAnchor(submitButton, 274.0);
        AnchorPane.setTopAnchor(submitButton, 302.0);

        submitButton.setOnAction(actionEvent -> {
            errorLabel.setText("");
            String title = titleTextField.getText();
            String author = authorTextField.getText();
            String category = categoryComboBox.getSelectionModel().getSelectedItem();
            if(title.isEmpty()){
                errorLabel.setText("Title empty");
                showPopupNotification(primaryStage, "Title empty");
                return;
            }
            if(author.isEmpty()) {
                errorLabel.setText("Author empty");
                showPopupNotification(primaryStage, "Author empty");
                return;
            }
            if(stockTextField.getText().isEmpty()) {
                errorLabel.setText("Stock empty");
                showPopupNotification(primaryStage, "Stock empty");
                return;
            }
            int stock = 0;
            try {
                stock = Integer.parseInt(stockTextField.getText());
            }catch (Exception e) {
                errorLabel.setText("Stock must be digits");
                showPopupNotification(primaryStage, "Stock must be digits");
                return;
            }

            String id = generateId();
            User.books.add(new Book(id, title, author, category, stock));
            Database.book_addBook(id, titleTextField.getText(), authorTextField.getText(), category, stock);

            showPopupNotification(primaryStage, "Book added successfully!");

            MenuAdmin menuAdmin = new MenuAdmin();
            menuAdmin.start(primaryStage);
        });

        // Adding all components to the root pane
        root.getChildren().addAll(
                listBooksLabel, backButton, titleLabel, authorLabel,
                categoryLabel, stockLabel, titleTextField, authorTextField,
                categoryComboBox, stockTextField, errorLabel, submitButton
        );

        Scene scene = new Scene(root);
        primaryStage.setTitle("Book List");
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitHint("");
        primaryStage.show();

        // Apply theme
        DarkLightMode.applyTheme(root);
    }

    private static String generateId() {
        UUID uuid = UUID.randomUUID();
        String uuidString = uuid.toString();

        String formattedID = uuidString.substring(0, 4) + "-" +
                uuidString.substring(9, 13) + "-" +
                uuidString.substring(14, 18);

        return formattedID;
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
