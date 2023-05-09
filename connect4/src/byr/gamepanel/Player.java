package byr.gamepanel;

import java.awt.*;

public enum Player {
    PLAYER1, PLAYER2,;

    private String playerName;
    private Color playerColor;
    private int numOfMoveLeft = 40;

    public String getPlayerName()
    {
        return playerName;
    }

    public void setPlayerName(String playerName)
    {
        this.playerName = playerName;
    }

    public Color getPlayerColor()
    {
        return playerColor;
    }

    public void setPlayerColor(Color playerColor)
    {
        this.playerColor = playerColor;
    }

    public int getNumOfMoveLeft()
    {
        return numOfMoveLeft;
    }

    public void setNumOfMoveLeft(int numOfMoveLeft)
    {
        this.numOfMoveLeft = numOfMoveLeft;
    }
    
    public void numberOfMoveLeftdec()
    {
        --numOfMoveLeft;
    }

    public String toString()
    {
        return String.format("%s|%d|%d",playerName,playerColor.getRGB(),numOfMoveLeft);
    }
}
