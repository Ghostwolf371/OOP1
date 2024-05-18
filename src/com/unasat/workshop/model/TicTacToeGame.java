package com.unasat.workshop.model;

// TicTacToeGame.java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class TicTacToeGame {
    // Database connection variables
    private static final String DB_URL = "jdbc:mysql://localhost:3306/tic_tac_toe";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    // Game variables
    private static final int BOARD_SIZE = 3;
    private static final char PLAYER_X = 'X';
    private static final char PLAYER_O = 'O';
    private static final char EMPTY_CELL = ' ';

    // User variables
    private String userName;
    private String userCode;
    private int userId;

    // Game board
    private char[][] board;

    // Constructor
    public TicTacToeGame() {
        // Initialize game board
        board = new char[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = EMPTY_CELL;
            }
        }
    }

    // Method to create a new user profile
    public void createUserProfile(String userName, String userCode, String birthDate) {
        // Connect to database
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            // Create user profile query
            String query = "INSERT INTO users (name, code, birth_date) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, userName);
            pstmt.setString(2, userCode);
            pstmt.setString(3, birthDate);
            pstmt.executeUpdate();
            // Get user ID
            query = "SELECT id FROM users WHERE name = ? AND code = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, userName);
            pstmt.setString(2, userCode);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                userId = rs.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println("Error creating user profile: " + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.out.println("Error closing database connection: " + e.getMessage());
                }
            }
        }
    }

    // Method to login with an existing user profile
    public void login(String userName, String userCode) {
        // Connect to database
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            // Login query
            String query = "SELECT id FROM users WHERE name = ? AND code = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, userName);
            pstmt.setString(2, userCode);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                userId = rs.getInt("id");
                this.userName = userName;
                this.userCode = userCode;
            } else {
                System.out.println("Invalid username or code");
            }
        } catch (SQLException e) {
            System.out.println("Error logging in: " + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.out.println("Error closing database connection: " + e.getMessage());
                }
            }
        }
    }

    // Method to start a new game
    public void startGame() {
        // Initialize game board
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = EMPTY_CELL;
            }
        }
        // Set current player to X
        char currentPlayer = PLAYER_X;
        // Game loop
        while (true) {
            // Print game board
            printBoard();
            // Get user input
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter row (0-2): ");
            int row = scanner.nextInt();
            System.out.print("Enter column (0-2): ");
            int col = scanner.nextInt();
            // Validate user input
            if (row < 0 || row >= BOARD_SIZE || col < 0 || col >= BOARD_SIZE) {
                System.out.println("Invalid input. Please try again.");
                continue;
            }
            // Check if cell is empty
            if (board[row][col] != EMPTY_CELL) {
                System.out.println("Cell is already occupied. Please try again.");
                continue;
            }
            // Update game board
            board[row][col]= currentPlayer;
            // Check for win
            if (checkWin(currentPlayer)) {
                // Print game board
                printBoard();
                // Print win message
                System.out.println(currentPlayer + " wins!");
                // Save score to database
                saveScore(currentPlayer);
                // Exit game loop
                break;
            }
            // Check for draw
            if (isBoardFull()) {
                // Print game board
                printBoard();
                // Print draw message
                System.out.println("It's a draw!");
                // Exit game loop
                break;
            }
            // Switch to other player
            if (currentPlayer == PLAYER_X) {
                currentPlayer = PLAYER_O;
            } else {
                currentPlayer = PLAYER_X;
            }
        }
    }

    // Method to print game board
    public void printBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(board[i][j] + "|");
            }
            System.out.println();
        }
    }

    // Method to check for win
    public boolean checkWin(char player) {
        // Check rows
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true;
            }
        }
        // Check columns
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) {
                return true;
            }
        }
        // Check diagonals
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true;
        }
        return false;
    }

    // Method to check if board is full
    public boolean isBoardFull() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j] == EMPTY_CELL) {
                    return false;
                }
            }
        }
        return true;
    }

    // Method to save score to database
    public void saveScore(char player) {
        // Connect to database
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            // Save score query
            String query = "INSERT INTO scores (user_id, score, player) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, userId);
            if (player == PLAYER_X) {
                pstmt.setInt(2, 1);
            } else {
                pstmt.setInt(2, -1);
            }
            pstmt.setString(3, String.valueOf(player));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error saving score: " + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.out.println("Error closing database connection: " + e.getMessage());
                }
            }
        }
    }

    // Method to get top 10 scores
    public void getTopScores() {
        // Connect to database
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            // Gettop scores query
            String query = "SELECT u.name, s.score FROM scores s JOIN users u ON s.user_id = u.id ORDER BY s.score DESC LIMIT 10";
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            System.out.println("Top 10 scores:");
            while (rs.next()) {
                System.out.println(rs.getString("name") + ": " + rs.getInt("score"));
            }
        } catch (SQLException e) {
            System.out.println("Error getting top scores: " + e.getMessage());
        } finally {
            if (conn!= null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.out.println("Error closing database connection: " + e.getMessage());
                }
            }
        }
    }

    public static void main(String[] args) {
        TicTacToeGame game = new TicTacToeGame();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        String userName = scanner.nextLine();
        System.out.print("Enter code: ");
        String userCode = scanner.nextLine();
        game.login(userName, userCode);
        if (game.userId > 0) {
            System.out.println("Welcome, " + userName + "!");
            while (true) {
                System.out.println("1. Start new game");
                System.out.println("2. Get top scores");
                System.out.println("3. Exit");
                System.out.print("Enter choice: ");
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        game.startGame();
                        break;
                    case 2:
                        game.getTopScores();
                        break;
                    case 3:
                        System.out.println("Goodbye!");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } else {
            System.out.println("Invalid username or code. Please try again.");
        }
    }
}