package edu.bundickles.tic_tac_toe.model;

public class Player {
    private String playerName;
    private int wins;
    private int losses;

    public Player(String playerName){
        this.playerName = playerName;
        this.wins = 0;
        this.losses = 0;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getWins() {
        return wins;
    }

    public void addWin() {
        wins++;
    }

    public int getLosses() {
        return losses;
    }

    public void addLoss() {
        losses++;
    }
}
