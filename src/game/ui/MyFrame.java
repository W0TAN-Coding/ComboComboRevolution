package game.ui;

import game.character.Combo;
import game.character.Move;
import game.config.Keys;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
    public MyFrame() {
        initUI();
    }

    private void initUI() {
        Move m2A = new Move(3, 4, 11, Keys.DOWN, Keys.A);
        Move m5B = new Move(7, 5, 11, Keys.B);
        Move m2B = new Move(6, 4, 14, Keys.DOWN, Keys.B);
        Move m2C = new Move(8, 5, 14, Keys.DOWN, Keys.C);
        Move m3C = new Move(9, 3, 17, Keys.DOWN, Keys.RIGHT, Keys.C);
        Move mj9 = new Move(4, 0, 0, Keys.UP, Keys.RIGHT);
        Move maB = new Move(6, 4, 14, Keys.B);
        Move maC = new Move(8, 4, 17, Keys.C);
        Move mAT = new Move(1, 1, 0, Keys.RIGHT, Keys.E);

        Combo combo = new Combo(m2A, m5B, m2B, m2C);
        Combo full = new Combo(m2A, m5B, m2B, m2C, m3C, mj9, maB, maC, mj9, maB, maC, mAT);

        add(new MyPanel(full));

        setSize(1500, 1000);
        setResizable(false);
        pack();

        setTitle("Title");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame ex = new MyFrame();
            ex.setVisible(true);
        });
    }
}
