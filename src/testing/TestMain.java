package testing;

import game.character.Combo;
import game.character.Move;
import game.config.Keys;

import java.io.IOException;

public class TestMain {
    public static void main(String[] args) {
        Move m2A = new Move(4, 6, Keys.DOWN, Keys.A);
        Move m5B = new Move(7, 8, Keys.B);
        Move m2B = new Move(6, 8, Keys.DOWN, Keys.B);
        Move m2C = new Move(8, 10, Keys.DOWN, Keys.C);
        Move m3C = new Move(9, 10, Keys.DOWN, Keys.RIGHT, Keys.C);
        Move mj9 = new Move(0, 5, Keys.UP, Keys.RIGHT);
        Move maB = new Move(4, 8, Keys.B);
        Move maC = new Move(8, 10, Keys.C);
        Move mAT = new Move(1, 0, Keys.RIGHT, Keys.E);

        Combo c2A5B2B2C = new Combo(m2A, m5B, m2B, m2C);
        Combo full = new Combo(m2A, m5B, m2B, m2C, m3C, mj9, maB, maC, mj9, maB, maC, mAT);

        Generator gen = new Generator("Test 2");
        try {
            gen.createFile(full);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
