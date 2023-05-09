package byr.gamepanel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static byr.gamepanel.EntrancePanel.gamePanel;

public class RecordGame {
    private static Player [][] gameBoard;
    private static final String filePath = System.getProperty("user.home");
    private RecordGame()
    {
    }

    private static void recordBoard()
    {
        String fileName = "tahta.txt";
        String fullFilePath = filePath + File.separator + fileName;

        int row = NewGame.getRow();
        int col = NewGame.getCol();

        try {
            File file = new File(fullFilePath);
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fileWriter = new FileWriter(file, false);
            BufferedWriter bWriter = new BufferedWriter(fileWriter);

            System.out.println("Record.recordBoard()");

            for (int i = 0; i < row; ++i) {
                for (int j = 0; j < col; ++j) {
                    if (gameBoard[i][j] != null && gameBoard[i][j].getPlayerColor() != NewGame.getEmptyCellColor()){
                        String fmt = String.format("%d|%d|%d",i,j,gameBoard[i][j].getPlayerColor().getRGB());
                        bWriter.write(fmt);
                        bWriter.newLine();
                    }
                }
            }
            bWriter.close();
            fileWriter.close();

            System.out.println("tahta başarıyla kaydedildi.");

        } catch (IOException e) {
            System.out.println("Hata oluştu: " + e.getMessage());
        }
    }
    private static void recordPlayer()
    {
        String fileName = "hamle.txt";
        String fullFilePath = filePath + File.separator + fileName;

        Player currentPlayer  =  gamePanel.getCurrentPlayer();
        Player nextPlayer = gamePanel.getNextPlayer();
        System.out.println("Record.recordPlayer()");

        try {
            File file = new File(fullFilePath);

            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fileWriter = new FileWriter(file, false);
            BufferedWriter bWriter = new BufferedWriter(fileWriter);
            bWriter.write("1|" + currentPlayer.toString());
            bWriter.newLine();
            bWriter.write("2|" + nextPlayer.toString());

            bWriter.close();
            fileWriter.close();

            System.out.println("hamle başarıyla kaydedildi.");

        } catch (IOException e) {
            System.out.println("Hata oluştu: " + e.getMessage());
        }
    }

    public static void run()
    {
        gameBoard = gamePanel.getGameBoard();
        recordBoard();
        recordPlayer();
    }
}
