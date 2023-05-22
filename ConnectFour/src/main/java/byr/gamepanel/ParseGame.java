package byr.gamepanel;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static byr.gamepanel.EntrancePanel.gamePanel;

import static byr.gamepanel.Player.PLAYER1;
import static byr.gamepanel.Player.PLAYER2;

public class ParseGame {
    private static Player [][] gameBoard;
    private static final String filePath = System.getProperty("user.dir");
    private ParseGame()
    {
    }

    private static void getBoardRecord()
    {
        String fileName = "tahta.txt";
        String fullFilePath = filePath + File.separator + fileName;

        gameBoard = gamePanel.getGameBoard();

        int player1RGB = PLAYER1.getPlayerColor().getRGB();

        try {
            FileReader fileReader = new FileReader(fullFilePath);
            BufferedReader bReader = new BufferedReader(fileReader);

            JButton[][] board = gamePanel.getBoard();
            String line;
            while ((line = bReader.readLine()) != null) {
                setBoard(line, player1RGB, board);
            }

            bReader.close();
            fileReader.close();

        } catch (IOException e) {
            System.out.println("Hata oluştu: " + e.getMessage());
        }
    }

    private static void setBoard(String boardInfoStr, int player1RGB, JButton [][] board)
    {
        String [] boardInfos = boardInfoStr.split("[|]+");
        int row = Integer.parseInt(boardInfos[0]);
        int col = Integer.parseInt(boardInfos[1]);
        int rgb = Integer.parseInt(boardInfos[2]);

        System.out.println(rgb);

        if (rgb == player1RGB) {
            gameBoard[row][col] = PLAYER1;
            board[row][col].setBackground(PLAYER1.getPlayerColor());
        }
        else {
            gameBoard[row][col] = PLAYER2;
            board[row][col].setBackground(PLAYER2.getPlayerColor());
        }
    }

    private static void getPlayerRecord()
    {
        String fileName = "hamle.txt";
        String fullFilePath = filePath + File.separator + fileName;

        try {
            FileReader fileReader = new FileReader(fullFilePath);
            BufferedReader bReader = new BufferedReader(fileReader);


            String line;
            while ((line = bReader.readLine()) != null) {
                setPlayer(line);
            }

            bReader.close();
            fileReader.close();

        } catch (IOException e) {
            System.out.println("Hata oluştu: " + e.getMessage());
        }
    }

    private static void setPlayerInfos(Player player, String [] playerInfos)
    {
        player.setPlayerName(playerInfos[1]);
        Color color = Color.RED.getRGB() == Integer.parseInt(playerInfos[2]) ? Color.RED : Color.YELLOW;
        player.setPlayerColor(color);
        player.setNumOfMoveLeft(Integer.parseInt(playerInfos[3]));
    }

    private static void setPlayer(String playerInfoStr)
    {
        String [] playerInfos = playerInfoStr.split("[|]+");
        int idx  = Integer.parseInt(playerInfos[0]);

        if (idx == 1) {
            JLabel playerLabel = gamePanel.getPlayerLabel();

            setPlayerInfos(PLAYER1,playerInfos);

            gamePanel.setCurrentPlayer(PLAYER1);
            playerLabel.setText(PLAYER1.getPlayerName());
            playerLabel.setForeground(PLAYER1.getPlayerColor());

        } else if (idx == 2) {
            setPlayerInfos(PLAYER2,playerInfos);
            gamePanel.setNextPlayer(PLAYER2);
        }
    }
    public static void parse()
    {
        gamePanel  = new NewGame();

        getPlayerRecord();
        getBoardRecord();

        gamePanel.setVisible(true);
    }
}
