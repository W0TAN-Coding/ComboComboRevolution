package game.ui.objects;

import game.config.Keys;

import java.awt.*;

public class DynamicSymbol extends Symbol{
    private boolean pressed = false;

    public DynamicSymbol(Image image, Keys key, int x, int y, float size) {
        super(image, key, x, y, size);
    }

    public void moveUp() {
        y--;
    }

    public boolean wasPressed() {
        return pressed;
    }

    public void press() {
        pressed = true;
    }
}
