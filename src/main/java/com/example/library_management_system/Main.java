package com.example.library_management_system;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.text.Font;

public class Main extends Application {

    private LibraryManager libraryManager = new LibraryManager();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Library Management System - Login");

        // Load the custom font
        Font font = Font.loadFont(getClass().getResourceAsStream("/Font/Manjari-Bold.ttf"), 40); // Use the correct path

        // Load the background image
        Image backgroundImage = new Image(getClass().getResourceAsStream("/images/Library.jpg")); // Change to your image path
        ImageView imageView = new ImageView(backgroundImage);
        imageView.setFitWidth(600); // Set the width of the image
        imageView.setFitHeight(400); // Set the height of the image
        imageView.setPreserveRatio(false); // Preserve aspect ratioma

        // Login screen elements
        GridPane loginPane = new GridPane();
        loginPane.setAlignment(Pos.CENTER);
        loginPane.setPadding(new Insets(20));
        loginPane.setHgap(10);
        loginPane.setVgap(10);
        loginPane.setStyle("-fx-background-color: rgba(240, 244, 248, 0.2);"); // Semi-transparent background color

        // Username label
        Label usernameLabel = new Label("Username:");
        usernameLabel.setFont(font); // Use custom font
        usernameLabel.setStyle("-fx-text-fill: #ffffff;");

        // Username text field
        TextField usernameField = new TextField();
        usernameField.setPrefWidth(300); // Increase preferred width for the text field
        usernameField.setFont(font); // Use custom font
        usernameField.setStyle("-fx-background-color: #ffffff; " + // Background color
                "-fx-background-radius: 17; " + // Rounded corners
                "-fx-padding: 10; " + // Padding inside the text field
                "-fx-border-color: #873e23; " + // Border color
                "-fx-border-radius: 15; " + // Rounded border
                "-fx-border-width: 4;"); // Border width

        // Password label
        Label passwordLabel = new Label("Password:");
        passwordLabel.setFont(font); // Use custom font
        passwordLabel.setStyle("-fx-text-fill: #ffffff;");

        // Password field
        PasswordField passwordField = new PasswordField();
        passwordField.setPrefWidth(300); // Increase preferred width for the password field
        passwordField.setFont(font); // Use custom font
        passwordField.setStyle("-fx-background-color: #ffffff; " + // Background color
                "-fx-background-radius: 17; " + // Rounded corners
                "-fx-padding: 10; " + // Padding inside the password field
                "-fx-border-color: #873e23; " + // Border color
                "-fx-border-radius: 15; " + // Rounded border
                "-fx-border-width: 4;"); // Border width

        // Login button
        Button loginButton = new Button("Login");
        loginButton.setStyle("-fx-background-color: #873e23; " + // Button background color
                "-fx-text-fill: white; " + // Button text color
                "-fx-font-size: 20px; " + // Font size
                "-fx-background-radius: 15;"); // Rounded corners
        loginButton.setFont(font); // Use custom font

        // Button action
        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            if (libraryManager.authenticateUser(username, password)) {
                new LibraryController(primaryStage, libraryManager);
            } else {
                showAlert("Login Failed", "Invalid credentials. Please try again.");
            }
        });

        // Adding elements to the grid
        loginPane.add(usernameLabel, 0, 0);
        loginPane.add(usernameField, 1, 0);
        loginPane.add(passwordLabel, 0, 1);
        loginPane.add(passwordField, 1, 1);
        loginPane.add(loginButton, 1, 2);

        // Create a StackPane to hold the background image and the login pane
        StackPane root = new StackPane();
        root.getChildren().addAll(imageView, loginPane);

        // Center the scene and set the size
        Scene loginScene = new Scene(root, 600, 400);
        primaryStage.setScene(loginScene);
        primaryStage.setResizable(false); // Optional: Disable resizing
        primaryStage.centerOnScreen(); // Center the stage on the screen
        primaryStage.show();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
