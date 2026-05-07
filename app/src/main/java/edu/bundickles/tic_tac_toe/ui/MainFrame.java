package edu.bundickles.tic_tac_toe.ui;

import javax.swing.*;

import edu.bundickles.tic_tac_toe.model.GameManager;
import edu.bundickles.tic_tac_toe.model.Player;

public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("Tic Tac Toe");
        setSize(400,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        String name1 = JOptionPane.showInputDialog(this, "Player 1 - Enter name: ");
        String name2 = JOptionPane.showInputDialog(this, "Player 2 - Enter name: ");

        Player p1 = new Player(name1);
        Player p2 = new Player(name2);

        String[] options = {"X", "O", "A", "B",};
        String rawChoice1 = (String) JOptionPane.showInputDialog(
            this,
            name1 + ", choose your symbol: ",
            "Symbol Selection",
            JOptionPane.PLAIN_MESSAGE,
            null,
            options,
            options[0]
        );
        String choice1 = (rawChoice1 == null) ? "X" : rawChoice1;

        String[] remaining = java.util.Arrays.stream(options)
                .filter(s -> !s.equals(choice1))
                .toArray(String[]::new);

        String rawChoice2 = (String) JOptionPane.showInputDialog(
            this,
            name2 + ", choose your symbol: ",
            "Symbol Selection",
            JOptionPane.PLAIN_MESSAGE,
            null,
            remaining,
            remaining[0]
        );

        String choice2 = (rawChoice2 == null) ? remaining[0] : rawChoice2;

        char symb1 = choice1.charAt(0);
        char symb2 = choice2.charAt(0);
        

        GameManager game = new GameManager(p1, p2, symb1, symb2);

        add(new GamePanel(game));

        setVisible(true);
    }
}
