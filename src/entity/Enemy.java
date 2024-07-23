package entity;

import main.GamePanel;
import main.KeyHandler;

import java.awt.*;

public class Enemy extends Entity{

    GamePanel gp;
    KeyHandler keyH;

    public Enemy(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
    }
    public void setDefaultValues(){
        x = 100;
        y = 100;
        speed = 2;
    }
    public void update(){
        if(x > playerX){
            x -= speed;
        }else if(x < playerX){
            x += speed;
        }
        if(y > playerY){
            y -= speed;
        }else if(y < playerY){
            y += speed;
        }
    }
    public void draw(Graphics2D g2){
        g2.setColor(Color.white);

        g2.fillRect(x, y, gp.tileSize, gp.tileSize);
    }
}
