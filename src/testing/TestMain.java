package testing;

import game.character.Combo;
import game.character.Move;
import game.config.Keys;

import java.io.IOException;

public class TestMain {
    public static void main(String[] args) {
        // F-AOKO
        Move m2A = new Move(3, 4, 11, Keys.DOWN, Keys.A);
        Move m5B = new Move(7, 5, 11, Keys.B);
        Move m2B = new Move(6, 4, 14, Keys.DOWN, Keys.B);
        Move m2C = new Move(8, 5, 14, Keys.DOWN, Keys.C);
        Move m3C = new Move(9, 3, 17, Keys.DOWN, Keys.RIGHT, Keys.C);
        Move mj8 = new Move(4, 0, 0, Keys.UP);
        Move mj9 = new Move(4, 0, 0, Keys.UP, Keys.RIGHT);
        Move maB = new Move(6 - 4, 4, 14, Keys.B);
        Move maC = new Move(8 - 4, 4, 17, Keys.C);
        Move mAT = new Move(1, 1, 0, Keys.RIGHT, Keys.E);

        Combo c2A5B2B2C = new Combo(m2A, m5B, m2B, m2C);
        Combo full = new Combo(m2A, m5B, m2B, m2C, m3C, mj9, maB, maC, mj9, maB, maC, mAT);

        // C-WARC
        Move w2A = new Move(4, 3, 11, Keys.DOWN, Keys.A);
        Move w5Bl = new Move(7, 7, 4, 14, Keys.B);
        Move w2B = new Move(6, 5, 14, Keys.DOWN, Keys.B);
        Move w2C = new Move(7, 4, 17, Keys.DOWN, Keys.C);
        Move w5Cl = new Move(4, 4, 4, 17, Keys.C);
        Move wj8 = new Move(4, 0, 0, Keys.UP);
        Move wjA = new Move(5, 4, 11, Keys.A);
        Move wjB = new Move(4, 10, 14, Keys.B);
        Move wjC = new Move(5, 2, 11, Keys.C);
        Move wsdj9 = new Move(4, -4, 0, Keys.UP, Keys.RIGHT);
        Combo warc = new Combo(w2A, w5Bl, w2B, w2C, w5Cl, wj8, wjA, wjB, wjC);

        Generator gen = new Generator("Test 2");
        try {
            gen.createFile(warc);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
