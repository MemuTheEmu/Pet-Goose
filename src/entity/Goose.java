package entity;
import main.GamePanel;
import main.KeyHandler;
import main.Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;


public class Goose extends Entity{
    MouseEvent e;
    JFrame window;
    GamePanel gp;
    KeyHandler keyH;
    boolean moving, lastCanadian = Main.canadian, sitting, lastholding = Main.holding, holding, acting, sleeping, idling, grabbed;
    int pixelCounter,moveChance, sleepChance, sitChance, stealChance, idleChance;
    Random random = new Random();
    int gooseController = -1, gooseActionTime, pauseTime;
    GraphicsDevice screen = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

    Rectangle screenSize = screen.getDefaultConfiguration().getBounds();
    int screenWidth, screenHeight;

    public Goose(GamePanel gp, KeyHandler keyH, JFrame window) {
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
        window.setLocation(gooseX, gooseY);
        //window.setVisible(true);
        speed = 2; //Speed of the players movements
        //Speed of the players movements while sprinting
        direction = "down"; //Direction the player is facing, is down on start
        lastDirection  = "right";
        // Whether the player is moving
        pixelCounter = 0;
        sitting = false;
        moveChance = 50;
        idling  = false;
        sleepChance = moveChance + 50;
        sitChance = sleepChance + 50;
        grabbed = false;
    }
    public void getGooseSprite() {
        try {
            gooseDown1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GooseSprites/Classic/Goose_Down_1.png")));
            gooseDown2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GooseSprites/Classic/Goose_Down_2.png")));
            gooseStationaryUp = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GooseSprites/Classic/Goose_Up.png")));
            gooseStationaryRight = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(Main.gooseRightPath)));
            gooseStationaryDown = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GooseSprites/Classic/Goose_Down.png")));
            gooseStationaryLeft = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(Main.gooseLeftPath)));
            gooseSleepDown1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GooseSprites/Classic/Goose_Sleep_Down_1.png")));
            gooseSleepDown2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GooseSprites/Classic/Goose_Sleep_Down_2.png")));
            gooseSleepDown3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GooseSprites/Classic/Goose_Sleep_Down_3.png")));
            gooseUp1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GooseSprites/Classic/Goose_Up_1.png")));
            gooseUp2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GooseSprites/Classic/Goose_Up_2.png")));
            gooseRight = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(Main.gooseRightPath)));
            gooseRight1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(Main.gooseRight1Path)));
            gooseRight2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(Main.gooseRight2Path)));
            gooseLeft = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(Main.gooseLeftPath)));
            gooseLeft1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(Main.gooseLeft1Path)));
            gooseLeft2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(Main.gooseLeft2Path)));
            testSprite1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(Main.testSprite1Path)));
            testSprite2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(Main.testSprite2Path)));
            testSprite3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(Main.testSprite3Path)));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void update() {// TODO Create Work on AI, Steal Cursor
        //Steal cursor should only activate when cursor is within a certain distance from the goose, it should also only activate while moving.
        isGrabbed();
        if(targetTime > System.currentTimeMillis()){
            return;
        }
        if(Main.canadian != lastCanadian){
            Main.setGoose(Main.canadian);
            lastCanadian = Main.canadian;
            getGooseSprite();
        }
        gooseX = window.getX();
        gooseY = window.getY();
        if (!acting) {
            acting = true;
            moving = true;
            gooseController = random.nextInt(moveChance + sleepChance + sitChance + idleChance);
            gooseActionTime = random.nextInt(101) + 50;

        }
        if(gooseController <= moveChance){
            setDirection();
            System.out.println("Moving");
        }else if(gooseController <= sleepChance){
            sleeper();
            System.out.println("Sleeping");
        }else if(gooseController <= sitChance){
            sitter();
            System.out.println("Sitting");
        }else if(gooseController <= idleChance){
            idler();
            System.out.println("Idling");
        }
        if(moving){
            mover();
        }

        if(idling){
            idler();
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
                image = gooseRight;
                break;
            case "stationary down":
                image = gooseStationaryDown;
                break;
            case "stationary left":
                image = gooseLeft;
                break;
            case "sleep down":
                if(gooseSpriteNum == 1){
                    image = gooseSleepDown1;
                }else if(gooseSpriteNum == 2){
                    image = gooseSleepDown2;
                }else{
                    image = gooseSleepDown3;
                }
                break;
            case "right grabbed":
                image = testSprite1;
                break;
            case "left grabbed":
                image = testSprite2; //TODO add case for all the new sprites.
                break;
        }
        g2.drawImage(image, 0, 0, gp.tileSize, gp.tileSize, null); //draws the player sprite at the location and size denoted by playerX/Y and gp.tileSize
    }
    public void stealCursor(int gooseX, int gooseY){
        try{
            Robot robot = new Robot();
            robot.mouseMove(gooseX, gooseY);
        }catch(AWTException e) {
            e.printStackTrace();
        }
    }
    public void mover(){
        idling = false;
        moving = true;
        if ((gooseX + speed + gp.tileSize) <= (screenSize.x + screenWidth) && (gooseX - speed) >= screenSize.x) { //If the movement speed + position of goose is between screen size
            gooseX += speedDirectionX * speed;
        }else {
            if(direction.equals("right")){
                direction = "left";
                speedDirectionX = -1;
            }else if(direction.equals("left")){
                direction = "right";
                speedDirectionX = 1;
            }

            //gooseX += speedDirectionX * speed; // If not within screen limits move toward center for 1 tick and stop                //direction = "stationary " + direction;
            gooseActionTime += 100;
            //moving = false;
            //acting = false;
        }
        pixelCounter += speed;
        if (pixelCounter >= gooseActionTime) {// stops from moving if it has been moved the random amount
            moving = false;
            pixelCounter = 0;
            acting = false;
            pauseTime = random.nextInt(4)+1;
            if(direction.equals("right")){
                direction = "stationary right";
                pause(pauseTime);
            } else if (direction.equals("left")) {
                direction = "stationary left";
                pause(pauseTime);
            }
        }
        spriteRenderer();
        lastDirection = direction;
        window.setLocation(gooseX, gooseY);
    }
    public void sleeper(){
        gooseController = random.nextInt(100);
        if(gooseController <= 50 ){
            direction = "right grabbed"; //TODO make these sleeping animations
        }else if(gooseController <= 90){
            direction = "left grabbed";
        }else{
            direction = "left grabbed";
        }
        pauseTime = random.nextInt(10)+15;
        pause(pauseTime);
    }
    public void sitter(){
        gooseController = random.nextInt(100);
        if(gooseController <= 50 ){
            direction = "right grabbed"; //TODO make these sitting animations
        }else if(gooseController <= 90){
            direction = "left grabbed";
        }else{
            direction = "left grabbed";
        }
        pauseTime = random.nextInt(20)+ 15;
        pause(pauseTime);
    }
    public void isGrabbed(){
        idling = false;
        if(Main.holding != lastholding && Main.holding){//If mouse is holding goose, stop it from moving and generating new actions
            gooseActionTime = 0;
            acting = true;
            moving  = false;
            //grabbed = true;
            if(direction.equals("right")){ //TODO make sprite for grabbing left and right
                direction = "right grabbed";
            } else if (direction.equals("left")) {
                direction = "left grabbed";
            }
        }else if(Main.holding == lastholding && !Main.holding && Main.dropped){//Supposed to make the goose
            acting = false;
            moving = false;
            Main.dropped = false;
            //grabbed = false;
        }
        spriteRenderer();
    }
    public void spriteRenderer(){
        idling = false;
        if(moving){ // while moving the goose position and sprite is updated
            gooseSpriteCounter++;
            if (gooseSpriteCounter > 10) {
                gooseSpriteNum = (gooseSpriteNum == 1) ? 2 : 1;
                gooseSpriteCounter = 0;
            }
        }else{
            gooseSpriteCounter++;
            if(gooseSpriteCounter > 30){
                gooseSpriteNum = 1;
            }
            if(gooseSpriteCounter > 60){
                gooseSpriteNum = 2;
            }
            if(gooseSpriteCounter > 90){
                gooseSpriteNum = 3;
                gooseSpriteCounter = 0;
            }
        }
        //System.out.println("Goose sprite counter: "+ gooseSpriteCounter);
        //System.out.println("Goose sprite num: " + gooseSpriteNum);
    }
    public void setDirection(){
        if (gooseController >= 25) { // Move left
            speedDirectionX = -1;
            speedDirectionY = 0;
            direction = "left";
        }
        if (gooseController < 25) { // Move right
            speedDirectionX = 1;
            speedDirectionY = 0;
            direction = "right";
        }
    }
    public void idler(){
        gooseController = random.nextInt(100);
        if(gooseController <= 50 ){
            direction = "right grabbed"; //TODO make these idling animations
        }else if(gooseController <= 90){
            direction = "left grabbed";
        }else{
            direction = "left grabbed";
        }
        pauseTime = random.nextInt(20)+20;
        pause(pauseTime);
    }
    public void pause(long seconds){
        targetTime = System.currentTimeMillis() + (seconds * 1000);
    }
}