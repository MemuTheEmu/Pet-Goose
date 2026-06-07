package entity;

import java.awt.image.BufferedImage;

public class Entity {

    public int speed, speedDirectionX, speedDirectionY;
    public static int gooseX, gooseY;

    public BufferedImage gooseUp1, gooseUp2, gooseDown1, gooseDown2,
            gooseLeft1, gooseLeft2, gooseRight1, gooseRight2, gooseStationaryRight, gooseStationaryLeft, gooseStationaryUp,
            gooseStationaryDown, gooseSleepDown1, gooseSleepDown2, gooseSleepDown3;
    public String direction;
    public int gooseSpriteCounter = 0;
    public int gooseSpriteNum = 1;
}
