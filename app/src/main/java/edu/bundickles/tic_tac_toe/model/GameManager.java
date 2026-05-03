package edu.bundickles.tic_tac_toe.model;

public class GameManager {
    private GameBoard gameBoard;
    private char currentPlayer;

    public GameManager() {
        gameBoard = new GameBoard();
        currentPlayer = 'X';
    }

    public boolean canPlayMove(int row, int col) {
        boolean valid = gameBoard.makeMove(row, col, currentPlayer);
        if(valid) switchPlayer();
        return valid;
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    public char checkWinner(){
        char[][] b = gameBoard.getGameBoard();

        for(int i = 0; i < 3; i++) {
            if(b[i][0] != ' ' && b[i][0] == b[i][1] && b[i][1] == b[i][2])
                return b[i][0];
            if(b[0][i] != ' ' && b[0][i] == b[1][i] && b[1][i] == b[2][i])
                return b[0][i];
        }

        if(b[0][0] != ' ' && b[0][0] == b[1][1] && b[1][1] == b[2][2])
                return b[0][0];
        
        if(b[0][2] != ' ' && b[0][2] == b[1][1] && b[1][1] == b[2][0])
                return b[0][2];

        return ' ';
    }

    public char getCurrentPlayer() {
        return currentPlayer;
    }
}
