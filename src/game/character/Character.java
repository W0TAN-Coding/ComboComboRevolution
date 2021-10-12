package game.character;

import java.util.Set;

public class Character {
    private final String name;
    private final Set<Move> moveList;

    public Character(String name, Set<Move> moveList) {
        this.name = name;
        this.moveList = moveList;
    }

    public String getName() {
        return name;
    }

    public Set<Move> getMoveList() {
        return moveList;
    }
}
