package edu.bundickles.tic_tac_toe.ui;

import javax.swing.*;
import java.awt.*;

public class MainMenuPanel extends JPanel {
    public MainMenuPanel(Runnable startGame) {
        setLayout(new BorderLayout());

        ImageIcon original = new ImageIcon(
            getClass().getResource("/images/Title.png")
        );

        Image scaled = original.getImage().getScaledInstance(
            200,
            -1,
            Image.SCALE_SMOOTH
        );

        JLabel titleLabel = new JLabel(new ImageIcon(scaled));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JButton startButton = new JButton("Start Game!");

        startButton.addActionListener(e -> startGame.run());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startButton);

        add(titleLabel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}
    
