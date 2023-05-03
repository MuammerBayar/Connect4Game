package byr.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;

import static byr.gui.Player.PLAYER1;
import static byr.gui.Player.PLAYER2;

public class GamePanel extends JFrame {
    private JLabel playerLabel;
    private JButton[][] board;
    private JButton exitButton;
    private JPanel topPanel;
    private JPanel boardPanel;
    private JPanel bottomPanel;

    private static final int ROWS = 9;
    private static final int COLUMNS = 9;
    private static final Color EMPTY_CELL_COLOR = Color.WHITE;


    private Player currentPlayer;
    private Player[][] gameBoard;

    private void setPlayer()
    {
        PLAYER1.setPlayerColor(Color.RED);
        PLAYER2.setPlayerColor(Color.YELLOW);

        Random r = new Random();
        currentPlayer = r.nextBoolean() ? PLAYER1 : PLAYER2;
    }

    public GamePanel() {
        setTitle("Connect 4 Game");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setPlayer();
        // Create top panel with player label and new game button
        topPanel = new JPanel();
        playerLabel = new JLabel(currentPlayer.getPlayerName());

        topPanel.add(playerLabel);

        // Create board panel with buttons for each cell
        boardPanel = new JPanel(new GridLayout(ROWS, COLUMNS));
        board = new JButton[ROWS][COLUMNS];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                board[i][j] = new JButton();
                board[i][j].setBackground(EMPTY_CELL_COLOR);
                board[i][j].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        JButton button = (JButton) e.getSource();
                        int row = -1, column = -1;
                        for (int i = 0; i < ROWS; i++) {
                            for (int j = 0; j < COLUMNS; j++) {
                                if (board[i][j] == button) {
                                    row = i;
                                    column = j;
                                    break;
                                }
                            }
                        }
                        makeMove(row, column);
                    }
                });
                boardPanel.add(board[i][j]);
            }
        }

        // Create bottom panel with exit button
        bottomPanel = new JPanel();
        exitButton = new JButton("ÇIKIŞ");
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EntrancePanel.frame.setVisible(true);
                setVisible(false);
            }
        });
        bottomPanel.add(exitButton);

        // Add panels to frame
        add(topPanel, BorderLayout.NORTH);
        add(boardPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // Initialize game variables and start new game

        gameBoard = new Player[ROWS][COLUMNS];
        //newgame()
    }

    private void makeMove(int row, int column) {
        // Check if column is full
        if (gameBoard[0][column] != null) {
            return;
        }
        // Find empty row in column and update game board
        int rowToPlace = -1;
        for (int i = ROWS - 1; i >= 0; i--) {
            if (gameBoard[i][column] == null) {
                gameBoard[i][column] = currentPlayer;
                rowToPlace = i;
                break;
            }
        }
        if (rowToPlace == -1) {
            return;
        }

        // Update button color and switch players
        if (currentPlayer == PLAYER1) {
            board[rowToPlace][column].setBackground(PLAYER1.getPlayerColor());
            currentPlayer = PLAYER2;
            playerLabel.setText(PLAYER2.getPlayerName());
        } else {
            board[rowToPlace][column].setBackground(PLAYER2.getPlayerColor());
            currentPlayer = PLAYER1;
            playerLabel.setText(PLAYER1.getPlayerName());
        }

        // Check for win or tie

        Player winner = checkWin();
        if (winner != null) {
            String message = winner.getPlayerName().toUpperCase() + " KAZANDI!";
            JOptionPane.showMessageDialog(this, message);
            EntrancePanel.frame.setVisible(true);
            setVisible(false);
        } else if (checkTie()) {
            JOptionPane.showMessageDialog(this, "OYUN BERABERE!");
            EntrancePanel.frame.setVisible(true);
            setVisible(false);
        }

    }

    private Player checkWin() {
        // Check rows
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS - 3; j++) {
                Player player = gameBoard[i][j];
                if (player != null && player == gameBoard[i][j+1] &&
                        player == gameBoard[i][j+2] && player == gameBoard[i][j+3]) {
                    return player;
                }
            }
        }

        // Check columns
        for (int i = 0; i < ROWS - 3; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                Player player = gameBoard[i][j];
                if (player != null && player == gameBoard[i+1][j] &&
                        player == gameBoard[i+2][j] && player == gameBoard[i+3][j]) {
                    return player;
                }
            }
        }

        // Check diagonals
        for (int i = 0; i < ROWS - 3; i++) {
            for (int j = 0; j < COLUMNS - 3; j++) {
                Player player = gameBoard[i][j];
                if (player != null && player == gameBoard[i+1][j+1] &&
                        player == gameBoard[i+2][j+2] && player == gameBoard[i+3][j+3]) {
                    return player;
                }
            }
        }
        for (int i = 3; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS - 3; j++) {
                Player player = gameBoard[i][j];
                if (player != null && player == gameBoard[i-1][j+1] &&
                        player == gameBoard[i-2][j+2] && player == gameBoard[i-3][j+3]) {
                    return player;
                }
            }
        }

        // No winner
        return null;
    }
    private boolean checkTie()
    {
        for (int i = 0; i < ROWS; i++)
            for (int j = 0; j < COLUMNS; j++)
                if (gameBoard[i][j] == null)
                    return false;
        return true;
    }

    private void record()
    {
        
    }



}
