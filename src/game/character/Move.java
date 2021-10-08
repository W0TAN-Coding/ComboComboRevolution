package game.character;

import game.io.Key;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Move {
    private final Set<Key> input;
    private final int cancelWindowStart;
    private final int cancelWindowEnd;

    public Move(int cancelWindowStart, int cancelWindowEnd, Key... input) {
        this.cancelWindowStart = cancelWindowStart;
        this.cancelWindowEnd = cancelWindowEnd;
        this.input = new HashSet<>(Arrays.asList(input));
    }

    public Set<Key> getInput() {
        return input;
    }

    public int getCancelWindowStart() {
        return cancelWindowStart;
    }

    public int getCancelWindowEnd() {
        return cancelWindowEnd;
    }
}
