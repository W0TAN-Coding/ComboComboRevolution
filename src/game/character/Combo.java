package game.character;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Combo {
    private final List<Move> moves;

    public Combo(List<Move> moves) {
        this.moves = moves;
    }

    public Combo(Move... moves) {
        this.moves = new ArrayList<>(Arrays.asList(moves));
    }

    public List<Move> getMoves() {
        return moves;
    }
}
