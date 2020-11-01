package cn.chxbca.tank.model;

import cn.chxbca.tank.enums.Dir;
import cn.chxbca.tank.frame.TankFrame;

import java.awt.*;

/**
 * @author chxbca
 */
public class Tank extends AbstractWarModel {

    private static final int TANK_SPEED = 1;
    private static final int TANK_WIDTH = 20;
    private static final int TANK_HEIGHT = 20;
    private final TankFrame tankFrame;
    private boolean isMove = false;

    public Tank(int x, int y, TankFrame tankFrame) {
        super(x, y, TANK_SPEED, Dir.RIGHT, TANK_HEIGHT, TANK_WIDTH, tankFrame);
        this.x = x;
        this.y = y;
        this.tankFrame = tankFrame;
    }

    @Override
    public void paint(Graphics graphics) {
        if (isMove) {
            move();
            if (isOutBound()) {
                resetModel();
            }
        }
        graphics.setColor(Color.BLACK);
        graphics.drawRect(x, y, Tank.TANK_WIDTH, Tank.TANK_HEIGHT);
    }

    public void setDir(Dir dir) {
        isMove = true;
        this.dir = dir;
    }

    public void stopTank() {
        isMove = false;
    }

    public void fire() {
        Bullet bullet = new Bullet(x, y, dir, tankFrame);
        tankFrame.addBullet(bullet);
    }
}
