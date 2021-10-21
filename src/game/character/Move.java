package game.character;

import game.config.Keys;

import java.util.*;

public class Move {
    private final List<Keys> input;
    private final int startup;
    private final int active;
    private final int hitstun;
    private int heldFrames;

    public Move(int startup, int active, int hitstun, Keys... input) {
        // Calculate True Startup and use this as Startup value
        this.startup = startup;
        this.active = active;
        this.hitstun = hitstun;
        this.input = new ArrayList<>(Arrays.asList(input));
        this.heldFrames = this.startup;
    }

    public Move(int heldFrames, int startup, int active, int hitstun, Keys... input) {
        this(startup, active, hitstun, input);
        this.heldFrames = heldFrames;
    }

    public List<Keys> getInput() {
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

    public int getHeldFrames() {
        return heldFrames;
    }

    public boolean isCommandInput() {
        return commandInput;
    }
}
