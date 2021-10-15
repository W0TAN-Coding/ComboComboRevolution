package game.ui.objects;

import game.config.Keys;

import java.awt.*;
import java.awt.event.KeyEvent;

public abstract class Symbol {
    protected final Image image;
    protected final Keys key;
    protected int x;
    protected int y;
    protected float size;

    protected Symbol(Image image, Keys key, int x, int y, float size) {
        this.image = image;
        this.key = key;
        this.x = x;
        this.y = y;
        this.size = size;
    }

    public void draw(Graphics g) {
        g.drawImage(image, x, y, null);
    }

    public Image getImage() {
        return image;
    }

    public Keys getKey() {
        return key;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public float getSize() {
        return size;
    }

    public void adjustSize(float f) {
        this.size -= f;
    }
}
