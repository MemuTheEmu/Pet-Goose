package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static java.lang.Thread.sleep;

public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyH;
    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
        getPlayerSprite();
    }
    public void setDefaultValues(){
        playerX = 100;
        playerY = 100;
        speed = 4;
        sprintSpeed = 12;
        direction = "down";
    }

    public void getPlayerSprite(){
        try{
            playerDown1 = ImageIO.read(getClass().getResourceAsStream("/Player/Character_walk_1.png"));
            playerDown2 = ImageIO.read(getClass().getResourceAsStream("/Player/Character_walk_2.png"));
            playerStationary = ImageIO.read(getClass().getResourceAsStream("/Player/Character.png"));
            playerUp1 = ImageIO.read(getClass().getResourceAsStream("/Player/Character_walk_up_1.png"));
            playerUp2 = ImageIO.read(getClass().getResourceAsStream("/Player/Character_walk_up_2.png"));
            playerRight1 = ImageIO.read(getClass().getResourceAsStream("/Player/Character_walk_right_1.png"));
            playerRight2 = ImageIO.read(getClass().getResourceAsStream("/Player/Character_walk_right_2.png"));
            playerLeft1 = ImageIO.read(getClass().getResourceAsStream("/Player/Character_walk_left_1.png"));
            playerLeft2 = ImageIO.read(getClass().getResourceAsStream("/Player/Character_walk_left_2.png"));

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void update(){

        if(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed){
            if(keyH.upPressed) {
                playerY -= speed;
                direction = "up";
            }
            if(keyH.downPressed){
                playerY += speed;
                direction = "down";
            }
            if(keyH.leftPressed){
                playerX -= speed;
                direction = "left";
            }
            if(keyH.rightPressed){
                playerX += speed;
                direction = "right";
            }

            playerSpriteCounter ++;
            if(playerSpriteCounter > 10) {
                if (playerSpriteNum == 1) {
                    playerSpriteNum = 2;
                    System.out.println(playerSpriteNum);
                } else if (playerSpriteNum == 2) {
                    playerSpriteNum = 1;
                    System.out.println(playerSpriteNum);
                }
                playerSpriteCounter = 0;
            }
        }else{
            direction = "stationary";
        }


    }
    public void draw(Graphics2D g2){
       // g2.setColor(Color.white);
       // g2.fillRect(x, y, gp.tileSize, gp.tileSize);
        BufferedImage image = null;
        switch (direction){
            case "up":
                if(playerSpriteNum == 1){
                    image = playerUp1;
                }
                if(playerSpriteNum == 2){
                 image = playerUp2;
                }
                break;
            case "down":
                if(playerSpriteNum == 1){
                    image = playerDown1;
                }
                if(playerSpriteNum == 2){
                    image = playerDown2;
                }
                break;
            case "left":
                if(playerSpriteNum == 1){
                    image = playerLeft1;
                }
                if(playerSpriteNum == 2){
                    image = playerLeft2;
                }
                break;
            case "right":
                if(playerSpriteNum == 1){
                    image = playerRight1;
                }
                if(playerSpriteNum == 2){
                    image = playerRight2;
                }
                break;
            case "stationary":
                image = playerStationary;
                break;
        }
        g2.drawImage(image, playerX, playerY, gp.tileSize, gp.tileSize, null);
    }
}
