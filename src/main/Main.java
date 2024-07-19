package main;

import javax.swing.*;

public class Main {

    public static void main(String[] args){
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);
        window.setTitle("GAME... IN JAVA!!?!?!?!?");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();//makes everything fit

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();
    }
}
