package cn.chxbca.tank.model;

import cn.chxbca.tank.enums.Dir;
import cn.chxbca.tank.frame.TankFrame;
import cn.chxbca.tank.resource.ImageResource;

import java.awt.*;

/**
 * @author chxbca
 */
public class Tank extends AbstractWarModel {

    private static final int TANK_SPEED = 1;
    private final TankFrame tankFrame;
    private boolean isMove = false;

    public Tank(int x, int y, TankFrame tankFrame) {
        super(x, y, TANK_SPEED, Dir.RIGHT, tankFrame);
        this.x = x;
        this.y = y;
        this.tankFrame = tankFrame;
    }

    @Override
    public void paint(Graphics graphics) {
        Image tankImage = getTankImage();
        if (isMove) {
            move();
            int width = tankImage.getWidth(tankFrame);
            int height = tankImage.getHeight(tankFrame);
            if (isOutBound(width, height)) {
                resetModel(width, height);
            }
        }
        graphics.drawImage(tankImage, x, y, null);
    }

    private Image getTankImage() {
        Image tankImage;
        switch (dir) {
            case UP:
                tankImage = ImageResource.TANK_UP;
                break;
            case LEFT:
                tankImage = ImageResource.TANK_LEFT;
                break;
            case DOWN:
                tankImage = ImageResource.TANK_DOWN;
                break;
            case RIGHT:
                tankImage = ImageResource.TANK_RIGHT;
                break;
            default:
                throw new Error();
        }
        return tankImage;
    }

    public void setDir(Dir dir) {
        isMove = true;
        this.dir = dir;
    }

    public void stopTank() {
        isMove = false;
    }

    public void fire() {
        Image tankImage = getTankImage();
        Bullet bullet = new Bullet(x, y, tankImage.getWidth(tankFrame), tankImage.getHeight(tankFrame), dir, tankFrame);
        tankFrame.addBullet(bullet);
    }
}
