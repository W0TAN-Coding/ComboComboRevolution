package game.config;

import java.util.*;

public enum Keys {
    UP('w'), DOWN('s'), LEFT('a'), RIGHT('d'), A('u'), B('i'), C('o'), D(' '), E('j');

    int key;
    Keys(char key) {
        this.key = key;
    }

    public int getASCII() {
        return key == ' ' ? 32 : (int) key;
    }

    public static List<Keys> getAllKeybinds() {
        return new ArrayList<>(Arrays.asList(UP, DOWN, LEFT, RIGHT, A, B, C, D, E));
    }
}
