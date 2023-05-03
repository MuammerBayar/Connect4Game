package byr.gui;

import javax.swing.*;
import java.awt.event.*;

import static byr.gui.Player.PLAYER1;
import static byr.gui.Player.PLAYER2;

public class EntrancePanel extends JFrame implements ActionListener {
    static JFrame frame;

    static GamePanel gamePanel;
    private JTextField pName1, pName2;
    private JButton newGame, contGame, exitGame;

    public EntrancePanel() {


        setTitle("Connect 4 Game");
        setSize(500, 500);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel gtitle = new JLabel("WELCOME TO CONNECT 4 GAME");
        gtitle.setBounds(150,130,200,50);
        add(gtitle);

        JLabel pLabel1 = new JLabel("1. oyuncunun ismi:");
        pLabel1.setBounds(130, 180, 120, 25);
        add(pLabel1);

        pName1 = new JTextField();
        pName1.setBounds(240, 180, 120, 25);
        add(pName1);

        JLabel pLabel2 = new JLabel("2. oyuncunun ismi:");
        pLabel2.setBounds(130, 210, 120, 25);
        add(pLabel2);

        pName2 = new JTextField();
        pName2.setBounds(240, 210, 120, 25);
        add(pName2);

        newGame = new JButton("Yeni Oyun");
        newGame.setBounds(190, 250, 125, 35);
        newGame.addActionListener(this);
        add(newGame);

        contGame = new JButton("Oyuna Devam Et");
        contGame.setBounds(190, 290, 125, 35);
        contGame.addActionListener(this);
        add(contGame);

        exitGame = new JButton("Çıkış");
        exitGame.setBounds(190, 330, 125, 35);
        exitGame.addActionListener(this);
        add(exitGame);

        frame = this;
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newGame) {
            setPlayer();
            gamePanel = new GamePanel();
            gamePanel.setVisible(true);
            this.setVisible(false);

        } else if (e.getSource() == contGame) {
            ContinueGamePanel cgp = new ContinueGamePanel();
            cgp.setVisible(true);
            this.setVisible(false);
        } else if (e.getSource() == exitGame) {
            System.exit(0);
        }
    }

    private void setPlayer()
    {
        PLAYER1.setPlayerName(pName1.getText());
        PLAYER2.setPlayerName(pName2.getText());
    }

}
