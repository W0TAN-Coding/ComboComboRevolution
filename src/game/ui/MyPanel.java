package game.ui;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class MyPanel extends JPanel {
    private final int INITIAL_DELAY = 100;
    private final int PERIOD_INTERVAL = 1000/60;
    private Timer timer;

    private int x, y;

    public MyPanel() {
        init();
    }

    private Image LArrow;
    private Image RArrow;
    private Image UArrow;
    private Image DArrow;

    private void init() {

        loadImages();

        setBackground(Color.BLACK);

        setPreferredSize(new Dimension(1500, 1000));

        timer = new Timer();
        timer.scheduleAtFixedRate(new ScheduleTask(),
                INITIAL_DELAY, PERIOD_INTERVAL);
    }

    private void loadImages() {
        LArrow = new ImageIcon("src/resources/LeftArrow.png").getImage();
        RArrow = new ImageIcon("src/resources/RightArrow.png").getImage();
        UArrow = new ImageIcon("src/resources/UpArrow.png").getImage();
        DArrow = new ImageIcon("src/resources/DownArrow.png").getImage();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(LArrow, 0, 0, null);
        g.drawImage(RArrow, 100, 0, null);
        g.drawImage(UArrow, 200, 0, null);
        g.drawImage(DArrow, 300, 0, null);
        g.drawImage(UArrow, x, y, null);
    }

    private void drawStar(Graphics g) {
        g.drawImage(LArrow, x, y, this);
        Toolkit.getDefaultToolkit().sync();
    }

    private class ScheduleTask extends TimerTask {

        @Override
        public void run() {

            x += 1;
            y += 1;

            if (y > 1000) {
                y = 0;
                x = 0;
            }

            repaint();
        }
    }
}
