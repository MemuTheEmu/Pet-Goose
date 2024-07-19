package entity;

import java.awt.image.BufferedImage;

public class Entity {

    public int x, y, speed, sprintSpeed;

    public BufferedImage playerUp1, playerUp2, playerStationary, playerDown1, playerDown2, playerLeft1, playerLeft2, playerRight1, playerRight2;
    public String direction;
    public int playerSpriteCounter = 0;
    public int playerSpriteNum = 1;
}
