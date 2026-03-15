package com.example.library_management_system;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class LibraryController {

    public LibraryController(Stage primaryStage, LibraryManager libraryManager) {
        primaryStage.setTitle("Library Management System");

        // Load the custom font for the heading
        Font headingFont = Font.loadFont(getClass().getResourceAsStream("/Font/ConcertOne-Regular.ttf"), 24);
        Font font = Font.loadFont(getClass().getResourceAsStream("/Font/Manjari-Bold.ttf"), 16);

        // Main layout styling
        VBox mainLayout = new VBox(15);
        mainLayout.setPadding(new Insets(15));
        mainLayout.setStyle("-fx-background-color: rgba(173, 136, 1, 0.75); -fx-background-radius: 10;");

        // Search box with heading and search components
        HBox searchBox = new HBox(10);
        searchBox.setAlignment(Pos.CENTER_LEFT);
        searchBox.setPadding(new Insets(10));
        searchBox.setStyle("-fx-background-color: rgba(89, 70, 1, 0.94); -fx-background-radius: 10;");

        // LIBRARY Heading label aligned to the left
        Label headingLabel = new Label("LIBRARY");
        headingLabel.setFont(headingFont);
        headingLabel.setStyle("-fx-text-fill: #ffffff;");

        // Right-aligned search components
        HBox searchComponents = new HBox(10);
        searchComponents.setAlignment(Pos.CENTER_RIGHT);

        Label searchLabel = new Label("Search:");
        searchLabel.setFont(font);

        TextField searchField = new TextField();
        searchField.setPromptText("Search by Title or Author...");
        searchField.setFont(font);
        searchField.setPrefWidth(250);
        searchField.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 15; -fx-padding: 5;");

        Button searchButton = new Button("Search");
        searchButton.setFont(font);
        searchButton.setStyle("-fx-background-color: #4caf50; -fx-text-fill: white; -fx-background-radius: 15;");

        searchComponents.getChildren().addAll(searchLabel, searchField, searchButton);

        // Add LIBRARY label and search components to search box
        searchBox.getChildren().addAll(headingLabel, new Region(), searchComponents);
        HBox.setHgrow(searchComponents, Priority.ALWAYS);

        // Table setup
        TableView<Book> bookTable = new TableView<>();

        // Book Table Columns
        TableColumn<Book, ImageView> imageColumn = new TableColumn<>("Cover");
        imageColumn.setCellValueFactory(data -> {
            ImageView imageView = new ImageView(data.getValue().getImage());
            imageView.setFitHeight(80);
            imageView.setFitWidth(80);
            return new SimpleObjectProperty<>(imageView);
        });

        TableColumn<Book, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTitle()));
        titleColumn.setPrefWidth(200);

        TableColumn<Book, String> authorColumn = new TableColumn<>("Author");
        authorColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getAuthor()));
        authorColumn.setPrefWidth(150);

        TableColumn<Book, String> isbnColumn = new TableColumn<>("ISBN");
        isbnColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getIsbn()));
        isbnColumn.setPrefWidth(150);

        TableColumn<Book, String> availabilityColumn = new TableColumn<>("Available");
        availabilityColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().isAvailable() ? "Yes" : "No"));
        availabilityColumn.setPrefWidth(100);

        // Add columns to the book table
        bookTable.getColumns().addAll(imageColumn, titleColumn, authorColumn, isbnColumn, availabilityColumn);
        bookTable.getItems().addAll(libraryManager.getBookInventory());

        // Borrow Button
        Button borrowButton = new Button("Borrow");
        borrowButton.setFont(font);
        borrowButton.setStyle("-fx-background-color: #2196f3; -fx-text-fill: white; -fx-background-radius: 15;");
        borrowButton.setOnAction(e -> {
            Book selectedBook = bookTable.getSelectionModel().getSelectedItem();
            if (selectedBook != null && selectedBook.isAvailable()) {
                selectedBook.setAvailable(false);
                bookTable.refresh();
                showAlert("Success", "Book borrowed successfully!");
            } else {
                showAlert("Error", "Book not available for borrowing.");
            }
        });

        // Delete Button
        Button deleteButton = new Button("Delete Book");
        deleteButton.setFont(font);
        deleteButton.setStyle("-fx-background-color: #f44336; -fx-text-fill: white; -fx-background-radius: 15;");
        deleteButton.setOnAction(e -> {
            Book selectedBook = bookTable.getSelectionModel().getSelectedItem();
            if (selectedBook != null) {
                libraryManager.deleteBook(selectedBook);
                bookTable.getItems().remove(selectedBook);
                showAlert("Success", "Book deleted successfully!");
            } else {
                showAlert("Error", "Please select a book to delete.");
            }
        });

        // Edit Status Button
        Button editStatusButton = new Button("Edit Status");
        editStatusButton.setFont(font);
        editStatusButton.setStyle("-fx-background-color: #ff9800; -fx-text-fill: white; -fx-background-radius: 15;");
        editStatusButton.setOnAction(e -> {
            Book selectedBook = bookTable.getSelectionModel().getSelectedItem();
            if (selectedBook != null) {
                selectedBook.setAvailable(!selectedBook.isAvailable());
                bookTable.refresh();
                showAlert("Success", "Book status updated successfully!");
            } else {
                showAlert("Error", "Please select a book to edit its status.");
            }
        });

        // Add Book Pane
        GridPane addBookPane = new GridPane();
        addBookPane.setVgap(10);
        addBookPane.setHgap(10);
        TextField titleField = new TextField();
        TextField authorField = new TextField();
        TextField isbnField = new TextField();
        TextField imagePathField = new TextField();
        imagePathField.setPromptText("Optional: Image path (e.g., file:images/book.png)");
        Button addButton = new Button("Add Book");
        addButton.setFont(font);
        addButton.setStyle("-fx-background-color: #ff5722; -fx-text-fill: white; -fx-background-radius: 15;");

        addBookPane.add(new Label("Title:"), 0, 0);
        addBookPane.add(titleField, 1, 0);
        addBookPane.add(new Label("Author:"), 0, 1);
        addBookPane.add(authorField, 1, 1);
        addBookPane.add(new Label("ISBN:"), 0, 2);
        addBookPane.add(isbnField, 1, 2);
        addBookPane.add(new Label("Image Path:"), 0, 3);
        addBookPane.add(imagePathField, 1, 3);
        addBookPane.add(addButton, 1, 4);

        addButton.setOnAction(e -> {
            Book newBook = new Book(titleField.getText(), authorField.getText(), isbnField.getText(), true, imagePathField.getText());
            libraryManager.addBook(newBook);
            bookTable.getItems().add(newBook);
            titleField.clear();
            authorField.clear();
            isbnField.clear();
            imagePathField.clear();
            showAlert("Success", "Book added successfully!");
        });

        // Main layout setup
        VBox.setMargin(borrowButton, new Insets(10));
        VBox.setMargin(deleteButton, new Insets(10));
        VBox.setMargin(editStatusButton, new Insets(10));
        HBox buttonBox = new HBox(10, borrowButton, deleteButton, editStatusButton);
        buttonBox.setAlignment(Pos.CENTER);

        mainLayout.getChildren().addAll(searchBox, bookTable, buttonBox, new Label("Add a new book:"), addBookPane);

        primaryStage.setScene(new Scene(mainLayout, 900, 800));
        primaryStage.show();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
