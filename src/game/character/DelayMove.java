package game.character;

public class DelayMove extends Move {
    private final int minDelay;
    private final int maxDelay;

    public DelayMove(int minDelay, int maxDelay) {
        super(minDelay, maxDelay);
        this.minDelay = minDelay;
        this.maxDelay = maxDelay;
    }
}
