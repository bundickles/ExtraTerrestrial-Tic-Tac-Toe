package edu.bundickles.tic_tac_toe.ui;

import javax.swing.*;

import edu.bundickles.tic_tac_toe.model.GameManager;

import java.awt.*;


public class GamePanel extends JPanel {
    private JButton[][] buttons = new JButton[3][3];
    private GameManager game = new GameManager();

    public GamePanel() {
        setLayout(new GridLayout(3, 3));

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                JButton btn = new JButton();
                int row = i, col = j;

                btn.addActionListener(e -> handleMove(row, col, btn));

                buttons[i][j] = btn;
                add(btn);
            }
        }
    }

    private void handleMove(int row, int col, JButton btn) {
        char current = game.getCurrentPlayer();

        if(game.canPlayMove(row, col)) {
            btn.setText(String.valueOf(current));
            
            char winner = game.checkWinner();
            if(winner != ' ') {
                JOptionPane.showMessageDialog(this, winner + " wins!");
            }
        }
    }
}
