# Library Management System

A JavaFX-based desktop application for managing a library's book inventory and user authentication system.

## Features

- **User Authentication**: Secure login system with predefined users
- **Book Inventory Management**:
  - View all books with cover images, titles, authors, ISBNs, and availability status
  - Add new books to the inventory
  - Delete books from the inventory
  - Borrow books (mark as unavailable)
  - Edit book availability status
- **Search Functionality**: Search books by title or author
- **Modern UI**: Custom fonts, background images, and styled components

## Prerequisites

- Java 21 or higher
- Maven 3.6 or higher

## Installation and Setup

1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd Library_Management_System
   ```

2. Build the project:
   ```bash
   mvn clean compile
   ```

3. Run the application:
   ```bash
   mvn clean javafx:run
   ```

## Usage

1. **Login**: Use one of the predefined credentials:
   - Username: `admin`, Password: `123`
   - Username: `test`, Password: `test`

2. **Main Interface**:
   - View the book inventory in the table
   - Use the search bar to filter books by title or author
   - Select a book and use the action buttons:
     - **Borrow**: Mark the book as unavailable
     - **Delete Book**: Remove the book from inventory
     - **Edit Status**: Toggle availability status
   - Add new books using the form at the bottom

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   ├── com/example/library_management_system/
│   │   │   ├── Book.java              # Book model class
│   │   │   ├── HelloApplication.java  # Basic JavaFX application (placeholder)
│   │   │   ├── HelloController.java   # Controller for hello-view.fxml
│   │   │   ├── LibraryController.java # Main library management UI controller
│   │   │   ├── LibraryManager.java    # Business logic for library operations
│   │   │   ├── Main.java              # Main application entry point with login
│   │   │   └── User.java              # User model class
│   │   ├── BookImages/                # Book cover images
│   │   └── module-info.java           # Java module configuration
│   └── resources/
│       ├── com/example/library_management_system/
│       │   └── hello-view.fxml        # FXML for basic hello view
│       ├── Font/                      # Custom fonts
│       └── images/                    # Background images
└── test/                              # Unit tests
```

## Technologies Used

- **Java 21**: Programming language
- **JavaFX 21**: UI framework
- **Maven**: Build tool and dependency management
- **JUnit 5**: Testing framework

## Dependencies

- JavaFX Controls
- JavaFX FXML
- JUnit Jupiter (for testing)

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Acknowledgments

- Book cover images sourced from various online resources
- Custom fonts: Concert One and Manjari Bold