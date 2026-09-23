package entity;
import main.GamePanel;
import main.KeyHandler;
import main.Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;


public class Player extends Entity{
    JFrame window;
    GamePanel gp;
    KeyHandler keyH;
    boolean moving, lastCanadian = Main.canadian;
    int pixelCounter;
    Random random = new Random();
    int gooseController = -1, gooseMovement;
    GraphicsDevice screen = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

    Rectangle screenSize = screen.getDefaultConfiguration().getBounds();
    int screenWidth, screenHeight;

    public Player(GamePanel gp, KeyHandler keyH, JFrame window) {

        this.gp = gp;
        this.keyH = keyH;
        this.window = window;
        setDefaultValues();
        getGooseSprite();
    }

    public void setDefaultValues() {
        screenWidth = (int) screenSize.getWidth();
        screenHeight = (int) screenSize.getHeight();//Gets screen width
        gooseX = screenWidth - 960; //The location of the player on the x-axis
        gooseY = screenHeight - 200; //The location of the player on the y-axis
        speed = 4; //Speed of the players movements
        //Speed of the players movements while sprinting
        direction = "down"; //Direction the player is facing, is down on start
        ; //Whether the player is moving
        pixelCounter = 0;
    }


    public void getGooseSprite() {
        try {
            gooseDown1 = ImageIO.read(getClass().getResourceAsStream("/Goose/Classic/Goose_Down_1.png"));
            gooseDown2 = ImageIO.read(getClass().getResourceAsStream("/Goose/Classic/Goose_Down_2.png"));
            gooseStationaryUp = ImageIO.read(getClass().getResourceAsStream("/Goose/Classic/Goose_Up.png"));
            gooseStationaryRight = ImageIO.read(getClass().getResourceAsStream(Main.gooseRightPath));
            gooseStationaryDown = ImageIO.read(getClass().getResourceAsStream("/Goose/Classic/Goose_Down.png"));
            gooseStationaryLeft = ImageIO.read(getClass().getResourceAsStream(Main.gooseLeftPath));
            gooseSleepDown1 = ImageIO.read(getClass().getResourceAsStream("/Goose/Classic/Goose_Sleep_Down_1.png"));
            gooseSleepDown2 = ImageIO.read(getClass().getResourceAsStream("/Goose/Classic/Goose_Sleep_Down_2.png"));
            gooseSleepDown3 = ImageIO.read(getClass().getResourceAsStream("/Goose/Classic/Goose_Sleep_Down_3.png"));
            gooseUp1 = ImageIO.read(getClass().getResourceAsStream("/Goose/Classic/Goose_Up_1.png"));
            gooseUp2 = ImageIO.read(getClass().getResourceAsStream("/Goose/Classic/Goose_Up_2.png"));
            gooseRight1 = ImageIO.read(getClass().getResourceAsStream(Main.gooseRight1Path));
            gooseRight2 = ImageIO.read(getClass().getResourceAsStream(Main.gooseRight2Path));
            gooseLeft1 = ImageIO.read(getClass().getResourceAsStream(Main.gooseLeft1Path));
            gooseLeft2 = ImageIO.read(getClass().getResourceAsStream(Main.gooseLeft2Path));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void update() {
        if(Main.canadian != lastCanadian){
            Main.setGoose(Main.canadian);
            lastCanadian = Main.canadian;
            getGooseSprite();
        }
        if (!moving) {
            gooseController = random.nextInt(101) - 100;
            gooseMovement = random.nextInt(101) + 100;
        }
        //System.out.println("Control: " + gooseController);
        //System.out.println(gooseController);
        /*if (keyH.upPressed || gooseController <= 20) {
           speedDirectionY = -1;
           speedDirectionX = 0;
           direction = "up";
        }
        if (keyH.downPressed) {
           speedDirectionY = 1;
           speedDirectionX = 0;
           direction = "down";
        }*/
        if (gooseController >= 50) { // Move left
            moving = true;
            //System.out.println("Move: " + gooseMovement);
            speedDirectionX = -1;
            speedDirectionY = 0;
            direction = "left";
        }
        if (gooseController < 50) { // Move right
            moving = true;
            //System.out.println("Move: " + gooseMovement);
            speedDirectionX = 1;
            speedDirectionY = 0;
            direction = "right";
        }
        // while moving the player position and sprite is updated
        //System.out.println("Sprite Counter: " + gooseSpriteNum);
        gooseSpriteCounter++;
        if ((gooseX + speed + gp.tileSize) <= (screenSize.x + screenWidth) && (gooseX - speed) >= screenSize.x) {
            gooseX += speedDirectionX * speed;
            window.setLocation(gooseX,gooseY);
            //System.out.println("Goose X: " + gooseX);
        }
        else {
            direction = "stationary " + direction;
        }
        //gooseY += speedDirectionY * speed;
        pixelCounter += speed;
        if (gooseSpriteCounter > 10) {
            gooseSpriteNum = (gooseSpriteNum == 1) ? 2 : 1;
            gooseSpriteCounter = 0;
        }
        if (pixelCounter >= gooseMovement) {
            //System.out.println("Pixel: " + pixelCounter);
            moving = false;
            pixelCounter = 0;

        }

    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        switch (direction) { //Switch case changes the player sprite depending on the direction, if's change player sprite to make it animated
            case "up":
                image = gooseSpriteNum == 1 ? gooseUp1 : gooseUp2;
                break;
            case "down":
                image = gooseSpriteNum == 1 ? gooseDown1 : gooseDown2;
                break;
            case "left":
                image = gooseSpriteNum == 1 ? gooseLeft1 : gooseLeft2;
                break;
            case "right":
                image = gooseSpriteNum == 1 ? gooseRight1 : gooseRight2;
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


                if (gooseSpriteNum == 1) {
                    image = gooseSleepDown2;
                }
                if (gooseSpriteNum == 2) {
                    image = gooseSleepDown3;
                }
                break;
        }
        g2.drawImage(image, 0/*gooseX*/, /*gooseY*/ 0, gp.tileSize, gp.tileSize, null); //draws the player sprite at the location and size denoted by playerX/Y and gp.tileSize
       // window.setLocation(gooseX,gooseY);
    }
}