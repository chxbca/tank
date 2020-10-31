package cn.chxbca.tank.model;

import cn.chxbca.tank.enums.Dir;

import java.awt.*;

/**
 * @author chxbca
 */
public class Tank {

    private static final int SPEED = 1;
    private static final int WIDTH = 20;
    private static final int HEIGHT = 20;
    private final int boundWidth;
    private final int boundHeight;
    private int x, y;
    private Dir dir = Dir.RIGHT;
    private boolean isMove = false;

    public Tank(int x, int y, int boundWidth, int boundHeight) {
        this.x = x;
        this.y = y;
        this.boundWidth = boundWidth;
        this.boundHeight = boundHeight;
    }

    public void paint(Graphics graphics) {
        if (this.isMove) {
            setTankStatus();
        }
        graphics.setColor(Color.BLACK);
        graphics.drawRect(this.x, this.y, Tank.WIDTH, Tank.HEIGHT);
    }

    private void setTankStatus() {
        switch (this.dir) {
            case UP:
                this.y -= SPEED;
                break;
            case DOWN:
                this.y += SPEED;
                break;
            case LEFT:
                this.x -= SPEED;
                break;
            case RIGHT:
                this.x += SPEED;
                break;
            default:
                throw new Error();
        }
        checkBound();
    }

    private void checkBound() {
        if (x < 0) {
            x = 0;
        }
        if (y < HEIGHT) {
            y = HEIGHT;
        }
        if (x > (boundWidth - WIDTH)) {
            x = boundWidth - WIDTH;
        }
        if (y > (boundHeight - HEIGHT)) {
            y = boundHeight - HEIGHT;
        }
    }

    public void setDir(Dir dir) {
        this.isMove = true;
        this.dir = dir;
    }

    public void stopTank() {
        this.isMove = false;
    }

}
