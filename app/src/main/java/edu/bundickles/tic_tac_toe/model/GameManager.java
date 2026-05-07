package edu.bundickles.tic_tac_toe.model;

public class GameManager {
    private GameBoard gameBoard;
    private char currentPlayer;
    private Player player1;
    private Player player2;
    private char player1Symbol;
    private char player2Symbol;

    public Player getPlayer1() {
        return player1;
    }
    public Player getPlayer2() {
        return player2;
    }
    public char getPlayer1Symbol() {
        return player1Symbol;
    }
    public char getPlayer2Symbol() {
        return player2Symbol;
    }

    public GameManager(Player p1, Player p2, char symb1, char symb2) {
        this.gameBoard = new GameBoard();
        this.player1 = p1;
        this.player2 = p2;
        this.player1Symbol = symb1;
        this.player2Symbol = symb2;
        this.currentPlayer = player1Symbol;
    }

    public boolean canPlayMove(int row, int col) {
        boolean valid = gameBoard.makeMove(row, col, currentPlayer);
        if(valid) switchPlayer();
        return valid;
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == player1Symbol)
            ? player2Symbol
            : player1Symbol;
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

    public boolean isDraw() {
        char[][] b = gameBoard.getGameBoard();

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(b[i][j] == ' ') {
                    return false;
                }
            }
        }
        return checkWinner() == ' ';
    }

    public Player getPlayerBySymbol(char symbol) {
        if(symbol == player1Symbol) return player1;
        return player2;
    }

    public void resetGame() {
        gameBoard.resetGameBoard();
        currentPlayer = player1Symbol;
    }
}
