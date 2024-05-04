package com.unasat.workshop;

import java.util.Scanner;

public class tic_tac_toe {
    private char[][] board;
    private int size;

    public tic_tac_toe(int size) {
        this.size = size;
        this.board = new char[size][size];
        // Initialize the board with empty spaces
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = ' ';
            }
        }
    }
    public boolean makeMove(int row, int col, char player) {
        if (row >= 0 && row < size && col >= 0 && col < size && board[row][col] == ' ') {
            board[row][col] = player;
            return true;
        }
        return false;
    }
    public boolean checkWin() {
        // Check rows
        for (int i = 0; i < size; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0]!= ' ') {
                return true;
            }
        }
        // Check columns
        for (int i = 0; i < size; i++) {
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i]!= ' ') {
                return true;
            }
        }
        // Check diagonals
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0]!= ' ') {
            return true;
        }
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2]!= ' ') {
            return true;
        }
        return false;
    }
    public boolean isDraw() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
    public static void main(String[] args) {
        tic_tac_toe game = new tic_tac_toe(3);
        Scanner scanner = new Scanner(System.in);
        char currentPlayer = 'X';

        while (true) {
            game.printBoard();
            System.out.println("Player " + currentPlayer + ", enter your move (row and column):");
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            if (game.makeMove(row, col, currentPlayer)) {
                if (game.checkWin()) {
                    game.printBoard();
                    System.out.println("Player " + currentPlayer + " wins!");
                    break;
                }
                currentPlayer = (currentPlayer == 'X')? 'O' : 'X';
            } else {
                System.out.println("Invalid move, try again.");
            }
            if (game.isDraw()) {
                game.printBoard();
                System.out.println("It's a draw!");
                break;
            }
        }}

    public void printBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == 'X') {
                    System.out.print("\033[31m" + board[i][j] + "\033[0m "); // Red color for X
                } else if (board[i][j] == 'O') {
                    System.out.print("\033[34m" + board[i][j] + "\033[0m "); // Blue color for O
                } else {
                    System.out.print(board[i][j] + " ");
                }
            }
            System.out.println();
        }}
}

