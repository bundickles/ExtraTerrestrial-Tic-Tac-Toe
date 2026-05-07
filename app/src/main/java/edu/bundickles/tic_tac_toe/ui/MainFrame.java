package edu.bundickles.tic_tac_toe.ui;

import java.awt.CardLayout;

import javax.swing.*;

import edu.bundickles.tic_tac_toe.model.GameManager;
import edu.bundickles.tic_tac_toe.model.Player;

public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public MainFrame() {
        setTitle("ET's Tic Tac Toe");
        setSize(400,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        MainMenuPanel menuPanel = new MainMenuPanel(this::startGame);

        mainPanel.add(menuPanel, "MENU");
        add(mainPanel);
        setVisible(true);
    }
        
    private void startGame() {
        String name1 = JOptionPane.showInputDialog(this, "Player 1 - Enter name: ");
        String name2 = JOptionPane.showInputDialog(this, "Player 2 - Enter name: ");

        if(name1 == null || name1.isBlank()) name1 = "Player1";
        if(name2 == null || name2.isBlank()) name2 = "Player2";

        Player p1 = new Player(name1);
        Player p2 = new Player(name2);

        String[] options = {"X", "O", "A", "B"};

        String rawChoice1 = (String) JOptionPane.showInputDialog(
            this,
            name1 + ", choose your symbol (X-AlienCat, O-AlienDog, A-Star, B-Moon):",
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
            name2 + ", choose your symbol (X-AlienCat, O-AlienDog, A-Star, B-Moon):",
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

        GamePanel gamePanel = new GamePanel(game);

        mainPanel.add(gamePanel, "GAME");

        cardLayout.show(mainPanel, "GAME");
    }
}
