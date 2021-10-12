package game.character;

import game.config.Keys;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Move {
    private final Set<Keys> input;
    private final int startup;
    private final int hitstop;

    public Move(int startup, int hitstop, Keys... input) {
        this.startup = startup;
        this.hitstop = hitstop;
        this.input = new HashSet<>(Arrays.asList(input));
    }

    public Set<Keys> getInput() {
        return input;
    }

    public int getStartup() {
        return startup;
    }

    public int getHitstop() {
        return hitstop;
    }
}
