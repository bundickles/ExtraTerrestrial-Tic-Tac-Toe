package edu.bundickles.tic_tac_toe.model;

public class GameBoard {
    private char[][] gameBoard;

    public GameBoard() {
        gameBoard = new char[3][3];
        resetGameBoard();
    }

    public void resetGameBoard() {
        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 3; j++)
                gameBoard[i][j] = ' ';
    }

    public boolean makeMove(int row, int col, char symbol) {
        if(gameBoard[row][col] == ' ') {
            gameBoard[row][col] = symbol;
            return true;
        }
        return false;
    }

    public char[][] getGameBoard() {
        return gameBoard;
    }
}
