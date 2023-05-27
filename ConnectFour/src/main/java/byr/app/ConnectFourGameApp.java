package byr.app;

import byr.gamepanel.EntrancePanel;

public final class ConnectFourGameApp {
    private ConnectFourGameApp()
    {
    }
    private static  EntrancePanel ep;
    public static void run()
    {
        ep = new EntrancePanel();
        ep.setVisible(true);
    }
}
