package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static java.lang.Thread.sleep;

public class Player extends Entity implements MouseListener {
    GamePanel gp;
    KeyHandler keyH;
    boolean moving;
    int pixelCounter;
    double random;

    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
        getPlayerSprite();
    }
    public void setDefaultValues(){
        gooseX = gp.tileSize * 12; //The location of the player on the x-axis
        gooseY = gp.tileSize * 15; //The location of the player on the y-axis
        speed = 3; //Speed of the players movements
        //Speed of the players movements while sprinting
        direction = "down"; //Direction the player is facing, is down on start
        moving = false; //Whether the player is moving
        pixelCounter = 0; //The amount of pixels moved, one tile is 48 pixels
    }

    public void getPlayerSprite(){
        try{
            gooseDown1 = ImageIO.read(getClass().getResourceAsStream("/Goose/Goose_Down_1.png"));
            gooseDown2 = ImageIO.read(getClass().getResourceAsStream("/Goose/Goose_Down_2.png"));
            gooseStationaryUp = ImageIO.read(getClass().getResourceAsStream("/Goose/Goose_Up.png"));
            gooseStationaryRight = ImageIO.read(getClass().getResourceAsStream("/Goose/Goose_Right.png"));
            gooseStationaryDown = ImageIO.read(getClass().getResourceAsStream("/Goose/Goose_Down.png"));
            gooseStationaryLeft = ImageIO.read(getClass().getResourceAsStream("/Goose/Goose_Left.png"));
            gooseSleepDown1 = ImageIO.read(getClass().getResourceAsStream("/Goose/Goose_Sleep_Down_1.png"));
            gooseSleepDown2 = ImageIO.read(getClass().getResourceAsStream("/Goose/Goose_Sleep_Down_2.png"));
            gooseSleepDown3 = ImageIO.read(getClass().getResourceAsStream("/Goose/Goose_Sleep_Down_3.png"));
            gooseUp1 = ImageIO.read(getClass().getResourceAsStream("/Goose/Goose_Up_1.png"));
            gooseUp2 = ImageIO.read(getClass().getResourceAsStream("/Goose/Goose_Up_2.png"));
            gooseRight1 = ImageIO.read(getClass().getResourceAsStream("/Goose/Goose_Right_1.png"));
            gooseRight2 = ImageIO.read(getClass().getResourceAsStream("/Goose/Goose_Right_2.png"));
            gooseLeft1 = ImageIO.read(getClass().getResourceAsStream("/Goose/Goose_Left_1.png"));
            gooseLeft2 = ImageIO.read(getClass().getResourceAsStream("/Goose/Goose_Left_2.png"));

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void update() {
        if (!moving) {
            /*moving = true;//Only accepts inputs when not moving to make sure player is locked to tile grid
            random = Math.random();
            if(random <= 0.5){
                speedDirectionY = -1;
                speedDirectionX = 0;
                direction = "up";
            }else{
                speedDirectionY = 1;
                speedDirectionX = 0;
                direction = "down";

            }*/

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
                }
                if(gooseSpriteCounter > 10) { //Counter to switch sprite to make an animation playerSpriteCounter is the speed the animation has
                    if (gooseSpriteNum == 1) { //playerSpriteNumber is used to determine which sprite is shown
                        gooseSpriteNum = 2;
                        System.out.println(gooseSpriteNum);
                    } else if (gooseSpriteNum == 2) {
                        gooseSpriteNum = 1;
                        System.out.println(gooseSpriteNum);
                    }
                    gooseSpriteCounter = 0;
                }
            }else if(direction.equals("up")){
                direction = "stationary up";
            }else if(direction.equals("right")){
                direction = "stationary right";
            }else if(direction.equals("down")){
                direction = "stationary down";
            }else if(direction.equals("left")){
                direction = "stationary left";
            }
        }
        if (moving) {// while moving the player position and sprite is updated
            gooseSpriteCounter++;
            gooseX += speedDirectionX * speed;
            gooseY += speedDirectionY * speed;
            pixelCounter += speed;
            gooseSpriteCounter++;
            if (pixelCounter == 48) {
                moving = false;
                pixelCounter = 0;
            }
        }
    }
    public void draw(Graphics2D g2){
        BufferedImage image = null;
        switch (direction){ //Switch case changes the player sprite depending on the direction, if's change player sprite to make it animated
            case "up":
                if(gooseSpriteNum == 1){
                    image = gooseUp1;
                }
                if(gooseSpriteNum == 2){
                 image = gooseUp2;
                }
                break;
            case "down":
                if(gooseSpriteNum == 1){
                    image = gooseDown1;
                }
                if(gooseSpriteNum == 2){
                    image = gooseDown2;
                }
                break;
            case "left":
                if(gooseSpriteNum == 1){
                    image = gooseLeft1;
                }
                if(gooseSpriteNum == 2){
                    image = gooseLeft2;
                }
                break;
            case "right":
                if(gooseSpriteNum == 1){
                    image = gooseRight1;
                }
                if(gooseSpriteNum == 2){
                    image = gooseRight2;
                }
                break;
            case "stationary up":
                image = gooseStationaryUp;
                break;
            case "stationary right":
                image = gooseStationaryRight;
                break;
            case "stationary down":
                image = gooseStationaryDown;
                break;
            case "stationary left":
                image = gooseStationaryLeft;
                break;
            case "sleep down":
                image = gooseSleepDown1;

                if(gooseSpriteNum == 1){
                    image = gooseSleepDown2;
                }
                if(gooseSpriteNum == 2){
                    image = gooseSleepDown3;
                }
                break;
        }
        g2.drawImage(image, gooseX, gooseY, gp.tileSize, gp.tileSize, null); //draws the player sprite at the location and size denoted by playerX/Y and gp.tileSize
    }

    @Override
    public void mouseClicked(MouseEvent e) {
            if(e.getX() == gooseX && e.getY() == gooseY){
                // TODO add meno
            }

        System.out.println(e.getPoint() + " ");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println(e.getPoint() + " ");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println(e.getPoint() + " ");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println(e.getPoint() + " ");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println(e.getPoint() + " ");
    }
}
