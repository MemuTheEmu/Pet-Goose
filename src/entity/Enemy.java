package entity;

import main.GamePanel;
import main.KeyHandler;

import java.awt.*;

import static java.lang.Thread.sleep;

public class Enemy extends Entity{

    GamePanel gp;
    KeyHandler keyH;
    int distanceX, distanceY;

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

        /*if(x > playerX){
            x -= speed;
        }else if(y > playerY){
            y -= speed;
        }else if(x < playerX){
            x += speed;
        }else if(y < playerX){
            y += speed;
        }*/
        distanceX = Math.abs(Math.abs(x) - Math.abs(playerX));
        distanceY = Math.abs(Math.abs(y) - Math.abs(playerY));
        System.out.println("X: " + distanceX + "\n Y:" + distanceY );

        if(distanceX > distanceY){
            if(x > playerX){
                x -=speed;
            }else if(x < playerX){
                x += speed;
            }
        }else if(distanceX == distanceY && distanceX/2 != x){
            if(x > playerX){
                x -=speed;
            }else if(x < playerX){
                x += speed;
            }
        } else {
            if (y > playerY) {
                y -= speed;
            } else if (y < playerY) {
                y += speed;
            }
        }// TODO fix this movement so no diagonal;
    }
    public void draw(Graphics2D g2){
        g2.setColor(Color.white);

        g2.fillRect(x, y, gp.tileSize, gp.tileSize);
    }
}
