package org.example.demo;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class DarkLightMode {

    private static boolean isDarkMode = false;

    private static final String LIGHT_THEME = "-fx-background-color: #ffffff; -fx-text-fill: #000000;";
    private static final String DARK_THEME = "-fx-background-color: #2b2b2b; -fx-text-fill: #ffffff;";

    private static final String BUTTON_LIGHT_THEME = "-fx-background-color: #e7e7e7; -fx-text-fill: #000000;";
    private static final String BUTTON_DARK_THEME = "-fx-background-color: #4a4a4a; -fx-text-fill: #ffffff;";

    private static final String LABEL_LIGHT_THEME = "-fx-text-fill: #000000;";
    private static final String LABEL_DARK_THEME = "-fx-text-fill: #ffffff;";

    public static void toggleTheme() {
        isDarkMode = !isDarkMode;
    }

    public static boolean isDarkMode() {
        return isDarkMode;
    }

    public static void applyTheme(Pane root) {
        String theme = isDarkMode ? DARK_THEME : LIGHT_THEME;
        String buttonTheme = isDarkMode ? BUTTON_DARK_THEME : BUTTON_LIGHT_THEME;
        String labelTheme = isDarkMode ? LABEL_DARK_THEME : LABEL_LIGHT_THEME;

        root.setStyle(theme);
        root.lookupAll(".button").forEach(node -> node.setStyle(buttonTheme));
        root.lookupAll(".label").forEach(node -> node.setStyle(labelTheme));
    }

    public static void applyTheme(Scene scene) {
        applyTheme((Pane) scene.getRoot());
    }
}
