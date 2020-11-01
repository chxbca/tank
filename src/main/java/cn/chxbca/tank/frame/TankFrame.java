package cn.chxbca.tank.frame;

import cn.chxbca.tank.enums.Dir;
import cn.chxbca.tank.model.Bullet;
import cn.chxbca.tank.model.Tank;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author chxbca
 */
public class TankFrame extends Frame {

    public static final int GAME_WIDTH = 800;

    public static final int GAME_HEIGHT = 600;

    private final int maximumPoolSize = 1;

    private final int corePoolSize = 1;

    private final ThreadFactoryBuilder threadFactoryBuilder = new ThreadFactoryBuilder();

    private final Tank mainTank = new Tank(200, 300, this);
    private final ExecutorService executorService = new ThreadPoolExecutor(
            corePoolSize, maximumPoolSize, 0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(),
            threadFactoryBuilder.setNameFormat("Thread-Pool-%d").build()
    );
    private final List<Bullet> bulletCollection = new ArrayList<>();

    public TankFrame() {
        setVisible(true);
        setSize(GAME_WIDTH, GAME_HEIGHT);
        addKeyListener(new TankKeyListener());
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    public void addBullet(Bullet bullet) {
        bulletCollection.add(bullet);
    }

    @Override
    public void paint(Graphics g) {
        this.mainTank.paint(g);
        int i = 0;
        while (i < bulletCollection.size()) {
            Bullet bullet = bulletCollection.get(i);
            bullet.paint(g);
            i++;
        }
    }

    public void gameStart() {
        Runnable runnable = () -> {
            try {
                //noinspection InfiniteLoopStatement
                for (; ; ) {
                    TimeUnit.MICROSECONDS.sleep(500);
                    repaint();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        executorService.execute(runnable);
    }

    public void deleteBullet(Bullet bullet) {
        bulletCollection.remove(bullet);
    }

    class TankKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_W:
                    mainTank.setDir(Dir.UP);
                    break;
                case KeyEvent.VK_S:
                    mainTank.setDir(Dir.DOWN);
                    break;
                case KeyEvent.VK_A:
                    mainTank.setDir(Dir.LEFT);
                    break;
                case KeyEvent.VK_D:
                    mainTank.setDir(Dir.RIGHT);
                    break;
                default:
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_W:
                case KeyEvent.VK_S:
                case KeyEvent.VK_A:
                case KeyEvent.VK_D:
                    mainTank.stopTank();
                    break;
                case KeyEvent.VK_SPACE:
                    mainTank.fire();
                default:
            }
        }
    }
}


