package main;

import entity.Goose;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GamePanel extends JPanel implements Runnable {
    final int originalTileSize = 16;
    final int scale = 3;

    public final int tileSize = originalTileSize * scale;

    //WORLD SETTINGS

    int FPS = 30;

    Thread gameThread;
    KeyHandler keyH = new KeyHandler();
    public Goose goose;

    public GamePanel(JFrame window){
        this.setPreferredSize(new Dimension(48,48));
        this.setOpaque(false);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

        goose = new Goose(this, keyH, window);

        JPopupMenu popupMenu= new JPopupMenu();
        JMenuItem menuItem1 = new JMenuItem("Item 1");
        popupMenu.add(menuItem1);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e){
                if(e.isPopupTrigger()){
                    popupMenu.show(e.getComponent(),e.getX(),e.getY());
                }
            }
        });

    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run(){

        double drawInterval = 1000000000.0/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;


        while(gameThread!=null){
            currentTime = System.nanoTime();

            delta+=(currentTime-lastTime) / drawInterval;
            timer+= (currentTime-lastTime);
            lastTime = currentTime;

            if(delta >=1){
                update();
                repaint();
                delta --;
                drawCount ++;
            }
            if(timer >= 1000000000) {
                // System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
            try {
                Thread.sleep(15);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void update(){
       goose.update();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        goose.draw(g2);
    }
}
