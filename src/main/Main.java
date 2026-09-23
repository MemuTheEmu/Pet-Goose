package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLOutput;

public class Main {

    public static void main(String[] args){
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setExtendedState(Frame.MAXIMIZED_BOTH);
        window.setAlwaysOnTop(true);
        window.setResizable(false);
        window.setUndecorated(true);
        window.setBackground(new Color(0,0,0,0));
        //window.setTitle("GAME... IN JAVA!!?!?!?!?");

        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem menuItem1 = new JMenuItem("Option 1");
        menuItem1.addActionListener(e -> { System.out.println("Option 1 selected"); });
        popupMenu.add(menuItem1);

        JMenuItem menuItem2 = new JMenuItem("Option 2");
        menuItem2.addActionListener(e -> { System.out.println("Option 2 selected"); });
        popupMenu.add(menuItem2);

        GamePanel gamePanel = new GamePanel();

        window.add(gamePanel);
        window.pack();//makes everything fit

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();
        gamePanel.setVisible(true);
        gamePanel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //if(e.isPopupTrigger()){
                    popupMenu.show(e.getComponent(),e.getX(),e.getY()-100);
                //}
                System.out.println("Click");
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }
}
