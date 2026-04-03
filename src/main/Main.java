package main;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args){
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setExtendedState(Frame.MAXIMIZED_BOTH);
        window.setAlwaysOnTop(true);
        window.setResizable(true);
        window.setUndecorated(true);
        window.setBackground(new Color(0,0,0,0));
        //window.setTitle("GAME... IN JAVA!!?!?!?!?");

        GamePanel gamePanel = new GamePanel();

        window.add(gamePanel);
        window.pack();//makes everything fit

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();
    }
}
