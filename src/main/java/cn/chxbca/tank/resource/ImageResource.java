package cn.chxbca.tank.resource;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

/**
 * @author chxbca
 */
public final class ImageResource {

    public static Image TANK_UP;
    public static Image TANK_LEFT;
    public static Image TANK_DOWN;
    public static Image TANK_RIGHT;

    static {
        try {
            TANK_UP = ImageIO.read(ImageResource.class.getResourceAsStream("/images/tankU.gif"));
            TANK_LEFT = ImageIO.read(ImageResource.class.getResourceAsStream("/images/tankL.gif"));
            TANK_DOWN = ImageIO.read(ImageResource.class.getResourceAsStream("/images/tankD.gif"));
            TANK_RIGHT = ImageIO.read(ImageResource.class.getResourceAsStream("/images/tankR.gif"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ImageResource() {
    }
}
