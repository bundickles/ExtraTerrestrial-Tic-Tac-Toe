package edu.bundickles.tic_tac_toe.service;

import edu.bundickles.tic_tac_toe.model.Player;
import java.io.*;

public class ScoreManager {
    public void savePlayer(Player p) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("scores.txt", true));
        writer.write(p.getPlayerName() + "," + p.getWins() + ',' + p.getLosses());
        writer.newLine();
        writer.close();
    }
}
