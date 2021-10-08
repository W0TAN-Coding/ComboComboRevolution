package game.io;

public class Key {
    private final String keyName;
    private final int keyCode;

    public Key(String keyName, int keyCode) {
        this.keyName = keyName;
        this.keyCode = keyCode;
    }

    public int getKeyCode() {
        return this.keyCode;
    }

    public String getKeyName() {
        return this.keyName;
    }
}
