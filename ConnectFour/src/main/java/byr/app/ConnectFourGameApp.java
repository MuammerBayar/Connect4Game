package byr.app;

import byr.gamepanel.EntrancePanel;

import javax.swing.*;
import java.io.*;

public class ConnectFourGameApp {
    private static  EntrancePanel ep;
    private static void checkFile()
    {
        String filePath = System.getProperty("user.dir")  + File.separator;
        String fullFilePath = filePath + "tahta.txt";

        File file = new File(fullFilePath);
        String message = "";
        if (!file.exists()) {
            message = "tahta.txt";
        }

        fullFilePath = filePath + "hamle.txt";
        file = new File(fullFilePath);
        if (!file.exists()) {
            if (!message.isEmpty())
                message += " and ";

            message += "hamle.txt";

        }
        if (!message.isEmpty()){
            message += " is not found";
            JOptionPane.showMessageDialog(ep, message);
            System.exit(0);
        }

    }
    public static void run()
    {
        checkFile();
        ep = new EntrancePanel();
        ep.setVisible(true);
    }
}
