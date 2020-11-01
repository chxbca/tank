package cn.chxbca.tank.enums;

/**
 * @author chxbca
 */
public enum Dir {
    /**
     * up
     */
    UP(0),
    /**
     * left
     */
    LEFT(90),
    /**
     * down
     */
    DOWN(180),
    /**
     * right
     */
    RIGHT(270);

    private final int angel;

    Dir(int angel) {
        this.angel = angel;
    }

    public int getAngel() {
        return angel;
    }
}
