package game.ui;

import game.character.Combo;
import game.character.Move;
import game.config.Keys;
import game.ui.objects.DynamicSymbol;
import game.ui.objects.StaticSymbol;
import game.ui.objects.Symbol;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;
import java.util.List;
import java.util.Timer;

public class MyPanel extends JPanel implements Runnable {
    private final int TICK_MODIFIER = 4;
    private final int HEIGHT = 1000;
    private final int WIDTH = 1500;
    private final int DELAY = 17;
    public final static int BASE_OFFSET = 50;
    private Thread animator;
    private Symbol sLEFT;
    private Symbol sRIGHT;
    private Symbol sUP;
    private Symbol sDOWN;
    private Symbol sA;
    private Symbol sB;
    private Symbol sC;
    private Symbol sD;
    private Symbol sE;
    private int x, y;
    private List<DynamicSymbol> sequence;
    private Map<Keys, Symbol> symbols;

    public MyPanel(Combo c) {
        init(c);
    }


    private void init(Combo c) {
        addKeyListener(new TAdapter());
        setFocusable(true);
        sequence = new ArrayList<>();
        symbols = new EnumMap<>(Keys.class);
        loadSymbols();
        loadCombo(c);
        setBackground(new Color(0xb00b69));

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    private void loadSymbols() {
        sLEFT = new StaticSymbol(new ImageIcon("src/resources/LeftArrow.png").getImage(), Keys.LEFT, 0);
        sRIGHT = new StaticSymbol(new ImageIcon("src/resources/RightArrow.png").getImage(), Keys.RIGHT, 100);
        sUP = new StaticSymbol(new ImageIcon("src/resources/UpArrow.png").getImage(), Keys.UP, 200);
        sDOWN = new StaticSymbol(new ImageIcon("src/resources/DownArrow.png").getImage(), Keys.DOWN, 300);
        sA = new StaticSymbol(new ImageIcon("src/resources/A.png").getImage(), Keys.A, 400);
        sB = new StaticSymbol(new ImageIcon("src/resources/B.png").getImage(), Keys.B, 500);
        sC = new StaticSymbol(new ImageIcon("src/resources/C.png").getImage(), Keys.C, 600);
        sE = new StaticSymbol(new ImageIcon("src/resources/E.png").getImage(), Keys.E, 700);
        symbols.put(Keys.LEFT, sLEFT);
        symbols.put(Keys.RIGHT, sRIGHT);
        symbols.put(Keys.UP, sUP);
        symbols.put(Keys.DOWN, sDOWN);
        symbols.put(Keys.A, sA);
        symbols.put(Keys.B, sB);
        symbols.put(Keys.C, sC);
        symbols.put(Keys.E, sE);
    }

    public void loadCombo(Combo combo) {
        int currentHeight = HEIGHT;
        boolean hasDown = false;
        boolean hadDown = false;

        for(Move move : combo.getMoves()) {
            // Subtract Half the Startup Frames
            if(currentHeight != HEIGHT) {
                currentHeight -= (move.getStartup() / 2) * TICK_MODIFIER;
                sequence.get(sequence.size() - 1).adjustSize(move.getStartup() * TICK_MODIFIER);
            }
            // Create new Symbol
            for(Keys key : move.getInput()) {
                Symbol base = symbols.get(key);
                DynamicSymbol input = new DynamicSymbol(base.getImage(), key, base.getX(), currentHeight, (move.getActive() + move.getHitstun()) * TICK_MODIFIER);
                sequence.add(input);
                if(key == Keys.DOWN) {
                    hasDown = true;
                }
            }
            // Add Startup, Active Frames, and Hitstun
            currentHeight += (move.getStartup() + 1 + (move.getActive() + move.getHitstun()) / 2) * TICK_MODIFIER;
            if(hasDown) {
                hasDown = false;
                hadDown = true;
            } else {
                hadDown = false;
            }
        }
    }

    @Override
    public void addNotify() {
        super.addNotify();

        animator = new Thread(this);
        animator.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawBackground(g);
        for(DynamicSymbol s : sequence) {
            if (!s.wasPressed() && s.getY() >= 0 && s.getY() <= HEIGHT) {
                g.drawImage(s.getImage(), s.getX(), s.getY(), null);
            }
        }

        Toolkit.getDefaultToolkit().sync();
    }

    private void drawBackground(Graphics g) {
        for(Symbol s : symbols.values()) {
            g.drawImage(s.getImage(), s.getX(), BASE_OFFSET, null);
        }
    }

    private void cycle() {
        for(DynamicSymbol s : sequence) {
            s.moveUp();
            if(s.getY() + (s.getSize()/2)*TICK_MODIFIER < BASE_OFFSET) {
                s.press();
            }
        }
    }

    @Override
    public void run() {
        long beforeTime, timeDiff, sleep;
        int offset = 0;
        beforeTime = System.currentTimeMillis();

        while(true) {
            cycle();
            repaint();

            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = DELAY - timeDiff;

            if(sleep < 0) {
                sleep = 2;
            }

            try {
                sleep++;
                if(offset >= 3) {
                    sleep--;
                    offset = 0;
                }
                sleep /= TICK_MODIFIER;
                Thread.sleep(sleep);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
                break;
            }

            beforeTime = System.currentTimeMillis();

            offset++;
        }
    }

    private class TAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            for(DynamicSymbol s : sequence) {
                if(!s.wasPressed() && s.getKey().getASCII() == e.getKeyCode() + 32
                        && (BASE_OFFSET >= s.getY() - (s.getSize()/2)*TICK_MODIFIER && BASE_OFFSET <= s.getY() + (s.getSize()/2)*TICK_MODIFIER)) {
                    s.press();
                    System.out.println("KEY: " + e.getKeyCode() + " OFFSET: " + (BASE_OFFSET - (s.getY() - s.getSize()/2))/TICK_MODIFIER);
                    break;
                }
            }
        }
    }
}

