package game.ui;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
    public MyFrame() {
        initUI();
    }

    private void initUI() {
        add(new MyPanel());

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
