package cn.chxbca.tank.model;

import cn.chxbca.tank.enums.Dir;
import cn.chxbca.tank.frame.TankFrame;

import java.awt.*;


/**
 * @author chxbca
 */
public abstract class AbstractWarModel {

    private final int height, width;
    protected int x, y;
    protected int speed;
    protected Dir dir;
    protected TankFrame tankFrame;

    public AbstractWarModel(int x, int y, int speed, Dir dir, int height, int width, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.speed = speed;
        this.width = width;
        this.height = height;
        this.tankFrame = tankFrame;
    }

    protected void move() {
        switch (this.dir) {
            case UP:
                this.y -= speed;
                break;
            case DOWN:
                this.y += speed;
                break;
            case LEFT:
                this.x -= speed;
                break;
            case RIGHT:
                this.x += speed;
                break;
            default:
                throw new Error();
        }
    }

    protected boolean isOutBound() {
        return x < 0 ||
                y < height ||
                x > (TankFrame.GAME_WIDTH - width) ||
                y > (TankFrame.GAME_HEIGHT - height);
    }

    protected void resetModel() {
        if (x < 0) {
            x = 0;
        }
        if (y < height) {
            y = height;
        }
        if (x > (TankFrame.GAME_WIDTH - width)) {
            x = TankFrame.GAME_WIDTH - width;
        }
        if (y > (TankFrame.GAME_HEIGHT - height)) {
            y = TankFrame.GAME_HEIGHT - height;
        }
    }

    /**
     * 画出当前模型的状态
     *
     * @param graphics 画笔
     */
    public abstract void paint(Graphics graphics);
}
