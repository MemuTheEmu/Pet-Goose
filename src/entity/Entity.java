package entity;

import java.awt.image.BufferedImage;

public class Entity {

    public int speed, speedDirectionX, speedDirectionY;
    public static int gooseX, gooseY;

    public BufferedImage gooseUp1, gooseUp2, gooseDown1, gooseDown2, gooseLeft,
            gooseLeft1, gooseLeft2,gooseRight, gooseRight1, gooseRight2, gooseStationaryRight,
            gooseStationaryLeft, gooseStationaryUp, gooseStationaryDown,
            gooseSleepDown1, gooseSleepDown2, gooseSleepDown3, testSprite1, testSprite2, testSprite3;
    public String direction, lastDirection;
    public int gooseSpriteCounter = 0;
    public int gooseSpriteNum = 1;
    public long targetTime;
}
