package byr.gamepanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;

import static byr.gamepanel.Player.PLAYER1;
import static byr.gamepanel.Player.PLAYER2;

public class NewGame extends JFrame {
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
    private Player nextPlayer;
    private Player[][] gameBoard;

    private void setPlayer()
    {
        PLAYER1.setPlayerColor(Color.RED);
        PLAYER2.setPlayerColor(Color.YELLOW);

        Random r = new Random();
        currentPlayer = r.nextBoolean() ? PLAYER1 : PLAYER2;
        nextPlayer = currentPlayer.equals(PLAYER1) ? PLAYER2 : PLAYER1;

        playerLabel.setText(currentPlayer.getPlayerName());
        playerLabel.setForeground(currentPlayer.getPlayerColor());
    }
    private void setFrame()
    {
        setTitle("Connect 4 Game");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    private void addComponent()
    {
        topPanel = new JPanel();
        playerLabel = new JLabel();
        topPanel.add(playerLabel);

        boardPanel = new JPanel(new GridLayout(ROWS, COLUMNS));
        board = new JButton[ROWS][COLUMNS];

        bottomPanel = new JPanel();
        exitButton = new JButton("ÇIKIŞ");
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RecordGame.run();
                EntrancePanel.frame.setVisible(true);
                setVisible(false);
            }
        });
        bottomPanel.add(exitButton);

        // Add panels to frame
        add(topPanel, BorderLayout.NORTH);
        add(boardPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }
    private void  fillBoard()
    {
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

                        // Check for win or tie
                        checkStatus();
                    }
                });
                boardPanel.add(board[i][j]);
            }
        }
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

        updateCurrentPlayer(rowToPlace, column);
    }

    private void updateCurrentPlayer(int row, int column)
    {
        board[row][column].setBackground(currentPlayer.getPlayerColor());
        currentPlayer.numberOfMoveLeftdec();
        currentPlayer = nextPlayer;
        nextPlayer = currentPlayer.equals(PLAYER1) ? PLAYER2 : PLAYER1;
        playerLabel.setText(currentPlayer.getPlayerName());
        playerLabel.setForeground(currentPlayer.getPlayerColor());
    }

    private void checkStatus()
    {
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

        return null;
    }
    private boolean checkTie()
    {
        for (int i = 0; i < ROWS; i++)
            for (int j = 0; j < COLUMNS; j++)
                if (gameBoard[i][j] != null ||
                        PLAYER1.getNumOfMoveLeft() != 0 && PLAYER2.getNumOfMoveLeft() != 0)
                    return false;
        return true;
    }
    Player getCurrentPlayer()
    {
        return currentPlayer;
    }

    void setCurrentPlayer(Player player)
    {
        currentPlayer = player;
    }

    Player getNextPlayer()
    {
        return nextPlayer;
    }

    void setNextPlayer(Player player)
    {
        nextPlayer = player;
    }
    Player [][] getGameBoard()
    {
        return gameBoard;
    }

    static int getRow()
    {
        return ROWS;
    }
    static int getCol()
    {
        return COLUMNS;
    }
    static Color getEmptyCellColor()
    {
        return EMPTY_CELL_COLOR;
    }
    JButton [][] getBoard()
    {
        return board;
    }
    JLabel getPlayerLabel()
    {
        return playerLabel;
    }
    NewGame() {

        setFrame();
        addComponent();
        setPlayer();
        fillBoard();

        gameBoard = new Player[ROWS][COLUMNS];
    }
}
