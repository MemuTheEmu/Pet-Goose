package main;

import entity.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import static java.lang.Thread.sleep;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class GamePanel extends JPanel implements Runnable {
    final int originalTileSize = 16;
    final int scale = 3;

    public final int tileSize = originalTileSize * scale;
   // public final int maxScreenCol = 16;
   // public final int maxScreenRow = 12;
    //public final int screenWidth = tileSize * maxScreenCol;
   // public final int screenHeight = tileSize * maxScreenRow;

    //WORLD SETTINGS

    int FPS = 30;

    Thread gameThread;
    KeyHandler keyH = new KeyHandler();
    public Player player;

    public GamePanel(JFrame window){
        this.setPreferredSize(new Dimension(48,48));
        //this.setBackground(Color.BLACK);
        this.setOpaque(false);
        //this.setBackground(new Color(0,0,0,0));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

        player = new Player(this, keyH, window);

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
       player.update();
    }
    public void paintComponent(Graphics g){

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        player.draw(g2); // second so is on top of tile
    }
}
