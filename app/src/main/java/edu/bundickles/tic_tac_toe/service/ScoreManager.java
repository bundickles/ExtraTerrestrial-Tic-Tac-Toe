package edu.bundickles.tic_tac_toe.service;

import edu.bundickles.tic_tac_toe.model.Player;
import java.io.*;

public class ScoreManager {
    public void savePlayer(Player p) throws IOException {
        File file = new File("scores.txt");
        boolean isNewFile = file.length() == 0;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            if(isNewFile) {
                writer.write(String.format("%-15s %-10s %-10s", "Player", "Wins", "Losses"));
                writer.newLine();
                writer.write("----------------------------------------");
                writer.newLine();
            }
            String line = String.format("%-15s %-10s %-10s",
                p.getPlayerName(),
                p.getWins(),
                p.getLosses()
            );
            writer.write(line);
            writer.newLine();
        }
    }
}
