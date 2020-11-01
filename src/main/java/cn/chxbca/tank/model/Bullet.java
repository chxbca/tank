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

    private final int middleX;
    private final int middleY;

    public Bullet(int x, int y, int tankWidth, int tankHeight, Dir dir, TankFrame tankFrame) {
        super(x, y, BULLET_SPEED, dir, BULLET_HEIGHT, BULLET_WIDTH, tankFrame);
        middleX = tankWidth / 2 - BULLET_WIDTH / 2;
        middleY = tankHeight / 2 - BULLET_HEIGHT / 2;
    }

    @Override
    public void paint(Graphics graphics) {
        move();
        if (isOutBound()) {
            tankFrame.deleteBullet(this);
        }
        graphics.setColor(Color.BLACK);
        graphics.drawArc(x + middleX, y + middleY, BULLET_WIDTH, BULLET_HEIGHT, dir.getAngel(), 180);
    }
}
