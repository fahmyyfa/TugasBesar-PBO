package org.example.demo.Main;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.demo.Dashboard;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Dashboard dashboardObj = new Dashboard();
        dashboardObj.start(new Stage());
    }
}