package game.ui.objects;

import game.config.Keys;
import game.ui.MyPanel;

import java.awt.*;

public class StaticSymbol extends Symbol{

    public StaticSymbol(Image image, Keys key, int x) {
        super(image, key, x, MyPanel.BASE_OFFSET, 0);
    }
}
