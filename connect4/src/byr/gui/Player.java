package byr.gui;

import java.awt.*;

public enum Player {
    PLAYER1, PLAYER2,;

    private String playerName;
    private Color playerColor;

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Color getPlayerColor() {
        return playerColor;
    }

    public void setPlayerColor(Color playerColor) {
        this.playerColor = playerColor;
    }
}
