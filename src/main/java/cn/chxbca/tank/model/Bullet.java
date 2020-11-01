package cn.chxbca.tank.model;

import cn.chxbca.tank.enums.Dir;
import cn.chxbca.tank.frame.TankFrame;

import java.awt.*;

/**
 * @author chxbca
 */

public class Bullet extends AbstractWarModel {

    private static final int BULLET_SPEED = 1;
    private static final int BULLET_WIDTH = 10;
    private static final int BULLET_HEIGHT = 10;

    public Bullet(int x, int y, Dir dir, TankFrame tankFrame) {
        super(x, y, BULLET_SPEED, dir, BULLET_HEIGHT, BULLET_WIDTH, tankFrame);
    }

    @Override
    public void paint(Graphics graphics) {
        move();
        if (isOutBound()) {
            tankFrame.deleteBullet(this);
        }
        graphics.setColor(Color.BLACK);
        graphics.drawArc(x, y, BULLET_WIDTH, BULLET_HEIGHT, 0, 360);
    }

}
