package game.character;

import game.config.Keys;

public class CommandMove extends Move {
    private final CommandType type;

    public CommandMove(int startup, int active, int hitstun, CommandType type, Keys... input) {
        super(startup, active, hitstun, input);
        this.type = type;
    }

    public CommandMove(int heldFrames, int startup, int active, int hitstun, CommandType type, Keys... input) {
        super(heldFrames, startup, active, hitstun, input);
        this.type = type;
    }

    public CommandType getType() {
        return type;
    }
}

enum CommandType {
    JUMP, QCF, QCB, DP, HCF, HCB;
}
