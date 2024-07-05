package org.example.demo.Admin;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import org.example.demo.DarkLightMode;
import org.example.demo.Database.Student;
import org.example.demo.Database.User;

import static org.example.demo.LoginAdmin.bgAll;

public class ListStudent extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create the AnchorPane
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

        // Create the Label
        Label titleLabel = new Label("List Student");
        titleLabel.setFont(new Font("System Bold", 36));
        titleLabel.setLayoutX(249);
        titleLabel.setLayoutY(33);

        // Create the Back Button
        Button backButton = new Button("Back");
        backButton.setFont(new Font("System Bold", 14));
        backButton.setLayoutX(14);
        backButton.setLayoutY(446);
        backButton.setPrefSize(119, 40);

        backButton.setOnAction(actionEvent -> {
            MenuAdmin menuAdmin = new MenuAdmin();
            menuAdmin.start(primaryStage);
        });

        // Create the TableView and its columns
        TableView<Student> tableView = new TableView<>();
        tableView.setPrefSize(638, 322);

        TableColumn<Student, String> column1 = new TableColumn<>("Name");
        column1.setPrefWidth(140);
        column1.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Student, String> column2 = new TableColumn<>("NIM");
        column2.setPrefWidth(80);
        column2.setCellValueFactory(new PropertyValueFactory<>("nim"));
        TableColumn<Student, String> column3 = new TableColumn<>("Faculty");
        column3.setPrefWidth(80);
        column3.setCellValueFactory(new PropertyValueFactory<>("faculty"));
        TableColumn<Student, String> column4 = new TableColumn<>("Program");
        column4.setPrefWidth(80);
        column4.setCellValueFactory(new PropertyValueFactory<>("program"));

        tableView.getColumns().addAll(column1, column2, column3, column4);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        ObservableList<Student> studentData = FXCollections.observableArrayList(User.students);
        tableView.setItems(studentData);

        // Create the ScrollPane
        ScrollPane scrollPane = new ScrollPane(tableView);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setLayoutX(31);
        scrollPane.setLayoutY(111);
        scrollPane.setPrefSize(638, 322);

        // Add all components to the AnchorPane
        root.getChildren().addAll(titleLabel, backButton, scrollPane);

        // Create the Scene and set it on the Stage
        Scene scene = new Scene(root);
        primaryStage.setTitle("List Student");
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitHint("");
        primaryStage.show();

        // Apply theme
        DarkLightMode.applyTheme(root);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
