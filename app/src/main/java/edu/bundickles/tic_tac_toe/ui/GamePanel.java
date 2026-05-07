package edu.bundickles.tic_tac_toe.ui;

import javax.swing.*;
import java.awt.*;

import edu.bundickles.tic_tac_toe.model.GameManager;
import edu.bundickles.tic_tac_toe.model.Player;

public class GamePanel extends JPanel {
    private JButton[][] buttons = new JButton[3][3];
    private GameManager game;
    private JLabel turnLabel;
    private Image backgroundImage;
    
    public GamePanel(GameManager game) {
        this.game = game;

        backgroundImage = new ImageIcon(
            getClass().getResource("/images/Background.png")
        ).getImage();

        setLayout(new BorderLayout(5, 5));

        turnLabel = new JLabel(
            "Turn: " + game.getPlayerBySymbol(game.getCurrentPlayer()).getPlayerName(),
            SwingConstants.CENTER
        );

        turnLabel.setFont(new Font("Arial", Font.BOLD, 18));
        turnLabel.setForeground(Color.WHITE);
        add(turnLabel, BorderLayout.NORTH);

        JPanel gridPanel = new JPanel(new GridLayout(3,3));
        gridPanel.setOpaque(false);

        JButton resetBtn = new JButton("Reset");
        resetBtn.addActionListener(e -> resetGame());
        JPanel bottomPanel = new JPanel();
        bottomPanel.setOpaque(false);
        bottomPanel.add(resetBtn);
        
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                JButton btn = new JButton();

                btn.setFocusPainted(false);
                btn.setContentAreaFilled(false);
                btn.setBorderPainted(true);

                int row = i, col = j;

                btn.addActionListener(e -> handleMove(row, col, btn));

                buttons[i][j] = btn;
                gridPanel.add(btn);
            }
        }

        add(gridPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void handleMove(int row, int col, JButton btn) {
        if(Boolean.TRUE.equals(btn.getClientProperty("played"))) {
            return;
        }
        char current = game.getCurrentPlayer();
        
        if(game.canPlayMove(row, col)) {
            btn.setIcon(getSymbolIcon(current));
            btn.putClientProperty("played", true);
        
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
                    JOptionPane.showMessageDialog(
                        this,
                        "Error saving scores: " + ex.getMessage()
                    );
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
                buttons[i][j].setIcon(null);
                buttons[i][j].putClientProperty("played", false);
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

    private ImageIcon getSymbolIcon(char symbol){
        String path = switch (symbol) {

            case 'X' -> "/images/Aliencat.png";
            case 'O' -> "/images/Aliendog.png";
            case 'A' -> "/images/Star.png";
            case 'B' -> "/images/Moon.png";

            default -> null;
        };

        if(path == null) {
            return null;
        }

        ImageIcon icon = new ImageIcon(
            getClass().getResource(path)
        );

        Image scaled = icon.getImage().getScaledInstance(
            80,
            80,
            Image.SCALE_SMOOTH
        );

        return new ImageIcon(scaled);
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.drawImage(
            backgroundImage,
            0,
            0,
            getWidth(),
            getHeight(),
            this
        );
    }
}
