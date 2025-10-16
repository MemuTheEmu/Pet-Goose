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
    boolean moving;
    int pixelCounter;
    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
        getPlayerSprite();
    }
    public void setDefaultValues(){
        playerX = 96; //The location of the player on the x-axis
        playerY = 96; //The location of the player on the y-axis
        speed = 3; //Speed of the players movements
        sprintSpeed = 12; //Speed of the players movements while sprinting
        direction = "down"; //Direction the player is facing, is down on start
        moving = false; //Whether the player is moving
        pixelCounter = 0; //The amount of pixels moved, one tile is 48 pixels
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

    public void update() {
        if (!moving) { //Only accepts inputs when not moving to make sure player is locked to tile grid
            if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {//Sets direction of movement from keyboard inputs
                moving = true;
                if (keyH.upPressed) {
                    speedDirectionY = -1;
                    speedDirectionX = 0;
                    direction = "up";
                }
                if (keyH.downPressed) {
                    speedDirectionY = 1;
                    speedDirectionX = 0;
                    direction = "down";
                }
                if (keyH.leftPressed) {
                    speedDirectionX = -1;
                    speedDirectionY = 0;
                    direction = "left";
                }
                if (keyH.rightPressed) {
                    speedDirectionX = 1;
                    speedDirectionY = 0;
                    direction = "right";
                }//TODO fix animation to make steps happen when tapping a key
                if(playerSpriteCounter > 10) { //Counter to switch sprite to make an animation playerSpriteCounter is the speed the animation has
                    if (playerSpriteNum == 1) { //playerSpriteNumber is used to determine which sprite is shown
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
        if (moving) {// while moving the player position and sprite is updated
            playerSpriteCounter ++;
            playerX += speedDirectionX * speed;
            playerY += speedDirectionY * speed;
            pixelCounter += speed;
            playerSpriteCounter ++;
            if (pixelCounter == 48) {
                moving = false;
                pixelCounter = 0;
            }
        }
        //System.out.println(pixelCounter);
        //System.out.println("X:" + playerX);
        //System.out.println("Y: " + playerY);

    }
    public void draw(Graphics2D g2){
        BufferedImage image = null;
        switch (direction){ //Switch case changes the player sprite depending on the direction, if's change player sprite to make it animated
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
        g2.drawImage(image, playerX, playerY, gp.tileSize, gp.tileSize, null); //draws the player sprite at the location and size denoted by playerX/Y and gp.tileSize
    }
}
