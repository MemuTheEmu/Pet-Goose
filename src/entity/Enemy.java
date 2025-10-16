package entity;

import main.GamePanel;
import main.KeyHandler;

import java.awt.*;
public class Enemy extends Entity {

    GamePanel gp;
    KeyHandler keyH;
    int distanceX, distanceY, pixelCounter;
    boolean moving;

    public Enemy(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
    }

    public void setDefaultValues() {
        x = 240;
        y = 240;
        speed = 2;
        moving = false;
    }

    public void update() {
        distanceX = Math.abs(Math.abs(Math.abs(x) - Math.abs(playerX))); // distance of enemy from player horizontally
        distanceY = Math.abs(Math.abs(Math.abs(y) - Math.abs(playerY))); // distance of enemy from player vertically
        if(!moving){
            if(playerX > x && distanceX > distanceY){ //checks which direction the player is from the enemy and which distance is greater (x or y distance)
                speedDirectionX = 1; // sets direction x of enemy
                speedDirectionY = 0; // sets direction y of enemy
            }else if(playerX < x && distanceX >= distanceY){
                speedDirectionX = -1;
                speedDirectionY = 0;
            }else if(playerY > y && distanceX <= distanceY){
                speedDirectionY = 1;
                speedDirectionX = 0;
            }else if(playerY < y && distanceX < distanceY){
                speedDirectionY = -1;
                speedDirectionX = 0;
            }else if(playerX == x && playerY == y){
                speedDirectionY = 0;
                speedDirectionX = 0;
            }
            moving = true;
        }
        if(moving){
            pixelCounter += speed;
                x += speed * speedDirectionX;
                y += speed * speedDirectionY;
            if(pixelCounter == 48){
                moving = false;
                pixelCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2){
        g2.setColor(Color.white);

        g2.fillRect(x, y, gp.tileSize, gp.tileSize);
    }
}
