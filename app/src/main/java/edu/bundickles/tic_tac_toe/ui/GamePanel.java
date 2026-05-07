package edu.bundickles.tic_tac_toe.ui;

import javax.swing.*;
import java.awt.*;

import edu.bundickles.tic_tac_toe.model.GameManager;
import edu.bundickles.tic_tac_toe.model.Player;

public class GamePanel extends JPanel {
    private JButton[][] buttons = new JButton[3][3];
    private GameManager game;
    private JLabel turnLabel;
    
    public GamePanel(GameManager game) {
        this.game = game;

        setLayout(new BorderLayout(3, 3));

        turnLabel = new JLabel("Turn: " + game.getPlayerBySymbol(game.getCurrentPlayer()).getPlayerName());
        add(turnLabel, BorderLayout.NORTH);

        JPanel gridPanel = new JPanel(new GridLayout(3,3));

        JButton resetBtn = new JButton("Reset");
        resetBtn.addActionListener(e -> resetGame());
        
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                JButton btn = new JButton();
                int row = i, col = j;

                btn.addActionListener(e -> handleMove(row, col, btn));

                buttons[i][j] = btn;
                gridPanel.add(btn);
            }
        }

        add(gridPanel, BorderLayout.CENTER);
        add(resetBtn, BorderLayout.SOUTH);
    }

    private void handleMove(int row, int col, JButton btn) {
        char current = game.getCurrentPlayer();
        
        if(game.canPlayMove(row, col)) {
            btn.setText(String.valueOf(current));
            btn.setEnabled(false);
        
            char winner = game.checkWinner();

            if(winner != ' ') {
                Player winningPlayer = game.getPlayerBySymbol(winner);
                Player losingPlayer = (winningPlayer == game.getPlayer1())
                    ? game.getPlayer2()
                    : game.getPlayer1();

                winningPlayer.addWin();
                losingPlayer.addLoss();

                JOptionPane.showMessageDialog(
                    this, 
                    winningPlayer.getPlayerName() + " (" + winner + ") wins!");

                try {
                    new edu.bundickles.tic_tac_toe.service.ScoreManager().savePlayer(winningPlayer);
                    new edu.bundickles.tic_tac_toe.service.ScoreManager().savePlayer(losingPlayer);
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
                disableAllButtons();
                return;
            }

            if(game.isDraw()) {
                JOptionPane.showMessageDialog(this, "Draw!");
                disableAllButtons();
                return;
            }

            turnLabel.setText(
                "Turn: " + game.getPlayerBySymbol(game.getCurrentPlayer()).getPlayerName()
            );
        }
    }

    private void resetGame() {
        game.resetGame();

        turnLabel.setText(
            "Turn: " + game.getPlayerBySymbol(game.getCurrentPlayer()).getPlayerName()
        );

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setEnabled(true);
            }
        }
    }

    private void disableAllButtons() {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }
}
