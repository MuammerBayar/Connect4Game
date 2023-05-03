package byr.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ContinueGamePanel extends JFrame {
    static  JFrame frame;
    JList<String> gamesList;
    JScrollPane scrollPane;
    JButton continueButton;
    JButton backButton;
    ArrayList<String> savedGames;
    public ContinueGamePanel()
    {
        setTitle("Connect 4 Game");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Oyunları tutacak bir ArrayList oluşturun
        savedGames = new ArrayList<>();

        // Kaydedilmiş oyunları dosyadan okuyun ve savedGames ArrayList'ine ekleyin

        // Oyunları listeleyen bir JList oluşturun
        gamesList = new JList<>(savedGames.toArray(new String[0]));

        // JList bileşenini bir JScrollPane içine yerleştirin
        scrollPane = new JScrollPane(gamesList);

        // "Oyuna Devam Et" butonu
        continueButton = new JButton("Oyuna Devam Et");
        continueButton.setBounds(335,410,150,50);
        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Seçilen oyunun adını alın
                String selectedGame = gamesList.getSelectedValue();

                // Seçilen oyunu yükle
                loadGame(selectedGame);
            }
        });

        backButton = new JButton("Geri");
        backButton.setBounds(5,410,100,50);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EntrancePanel.frame.setVisible(true);
                setVisible(false);
            }
        });


        JFrame frame = this;
        add(scrollPane, BorderLayout.CENTER);
        add(continueButton);
        add(backButton);
        frame.setVisible(true);

    }

    public static void loadGame(String gameStr)
    {

    }
}
