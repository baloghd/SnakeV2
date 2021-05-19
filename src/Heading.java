public enum Heading {
    UP,
    DOWN,
    LEFT,
    RIGHT;

    public Heading opposite(Heading A) {
        return switch (A) {
            case UP -> DOWN;
            case DOWN -> UP;
            case LEFT -> RIGHT;
            case RIGHT -> LEFT;
        };
    }
}