package main;

import entity.Entity;
import entity.Goose;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;

import static java.lang.System.exit;

public class Main {
    public static String gooseLeftPath, gooseLeft1Path,
            gooseLeft2Path, gooseRightPath, gooseRight1Path,
            gooseRight2Path, menu1Text1, menu1Text2,
            testSprite1Path,testSprite2Path,testSprite3Path,
            menu2Text, menu3Text, menu4Text;
    public static JMenuItem menuItem1, menuItem2, menuItem3, menuItem4;
    public static boolean canadian = true, holding, dropped;
    public static Point initialClick;
    public static int xMoved, yMoved;
    public static void main(String[] args){
        gooseLeftPath = "/GooseSprites/Canadian/Canadian_Goose_Left.png";
        gooseLeft1Path = "/GooseSprites/Canadian/Canadian_Goose_Left_1.png";
        gooseLeft2Path = "/GooseSprites/Canadian/Canadian_Goose_Left_2.png";
        gooseRightPath = "/GooseSprites/Canadian/Canadian_Goose_Right.png";
        gooseRight1Path = "/GooseSprites/Canadian/Canadian_Goose_Right_1.png";
        gooseRight2Path = "/GooseSprites/Canadian/Canadian_Goose_Right_2.png";
        testSprite1Path = "/GooseSprites/Tests/TestSprite_1.png";
        testSprite2Path = "/GooseSprites/Tests/TestSprite_2.png";
        testSprite3Path = "/GooseSprites/Tests/TestSprite_3.png";

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //window.setExtendedState(Frame.MAXIMIZED_BOTH);
        window.setAlwaysOnTop(true);
        window.setResizable(false);
        window.setUndecorated(true);
        window.setBackground(new Color(0,0,0,0));
        //window.setTitle("GAME... IN JAVA!!?!?!?!?");
        menu1Text1 = "White Goose";
        menu1Text2 = "Canadian Goose";
        menu2Text = "sleep";
        menu3Text = "sit";
        menu4Text = "move";
        JPopupMenu popupMenu = new JPopupMenu();
        menuItem1 = new JMenuItem(menu1Text1);
        menuItem1.addActionListener(e -> { System.out.println("White Goose");
            if(menuItem1.getText().equals(menu1Text1)){
                menuItem1.setText(menu1Text2);
                canadian = false;
            }else if(menuItem1.getText().equals(menu1Text2)){
                menuItem1.setText(menu1Text1);
                canadian = true;
            }});
        menuItem2 = new JMenuItem(menu2Text);
        menuItem2.addActionListener(e ->{
        });
        menuItem3 = new JMenuItem(menu3Text);
        menuItem3.addActionListener(e ->{

        });
        menuItem4 = new JMenuItem(menu4Text);
        menuItem4.addActionListener(e ->{

        });

        popupMenu.add(menuItem1);
        popupMenu.add(menuItem2);
        popupMenu.add(menuItem3);
        popupMenu.add(menuItem4);

        JMenuItem menuItem2 = new JMenuItem("Exit");
        menuItem2.addActionListener(e -> { System.out.println("Option 2 selected");
            exit(0);
        });
        popupMenu.add(menuItem2);

        GamePanel gamePanel = new GamePanel(window);

        window.add(gamePanel);
        window.pack();//makes everything fit
        window.setVisible(true);

        gamePanel.startGameThread();
        gamePanel.setVisible(true);
        gamePanel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) { //TODO fix this

                System.out.println("Click");
            }
            @Override
            public void mousePressed(MouseEvent e) {
                if(e.isPopupTrigger()){
                    popupMenu.show(e.getComponent(),e.getX(),e.getY()-100);
                    return;
                }
                if(SwingUtilities.isLeftMouseButton(e)){
                    holding = true;
                    dropped = false;
                    initialClick = e.getPoint();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                    return;
                }
                holding = false;
                dropped = true;
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        gamePanel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int thisX = window.getX();
                int thisY = window.getY();
                if(holding && initialClick != null){
                    xMoved = e.getX() - initialClick.x;
                    yMoved = e.getY() - initialClick.y;
                    window.setLocation(thisX + xMoved, thisY + yMoved);
                }

                //dropped = false;
            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });
    }
    public static void setGoose(boolean canadian){
        if(canadian){
            gooseLeftPath = "/GooseSprites/Canadian/Canadian_Goose_Left.png";
            gooseLeft1Path = "/GooseSprites/Canadian/Canadian_Goose_Left_1.png";
            gooseLeft2Path = "/GooseSprites/Canadian/Canadian_Goose_Left_2.png";
            gooseRightPath = "/GooseSprites/Canadian/Canadian_Goose_Right.png";
            gooseRight1Path = "/GooseSprites/Canadian/Canadian_Goose_Right_1.png";
            gooseRight2Path = "/GooseSprites/Canadian/Canadian_Goose_Right_2.png";
        }else{
            gooseLeftPath = "/GooseSprites/White/White_Goose_Left.png";
            gooseLeft1Path = "/GooseSprites/White/White_Goose_Left_1.png";
            gooseLeft2Path = "/GooseSprites/White/White_Goose_Left_2.png";
            gooseRightPath = "/GooseSprites/White/White_Goose_Right.png";
            gooseRight1Path = "/GooseSprites/White/White_Goose_Right_1.png";
            gooseRight2Path = "/GooseSprites/White/White_Goose_Right_2.png";
        }
    }
}