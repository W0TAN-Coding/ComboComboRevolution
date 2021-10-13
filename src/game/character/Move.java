package game.character;

import game.config.Keys;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Move {
    private final Set<Keys> input;
    private final int startup;
    private final int active;
    private final int hitstun;

    public Move(int startup, int active, int hitstun, Keys... input) {
        // Calculate True Startup and use this as Startup value
        this.startup = startup + 1;
        this.active = active;
        this.hitstun = hitstun;
        this.input = new HashSet<>(Arrays.asList(input));
    }

    public Set<Keys> getInput() {
        return input;
    }

    public int getStartup() {
        return startup;
    }

    public int getHitstun() {
        return hitstun;
    }

    public int getActive() {
        return active;
    }
}
