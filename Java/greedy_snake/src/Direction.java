public enum Direction {
    UP(0),
    DOWN(1),
    LEFT(2),
    RIGHT(3);

    private int code;

    private Direction(int code) {
        this.code = code;
    }

    public static boolean isOpposite(Direction dir1, Direction dir2) {
        return (((dir1 == Direction.LEFT) && (dir2 == Direction.RIGHT)) ||
                ((dir1 == Direction.RIGHT) && (dir2 == Direction.LEFT)) ||
                ((dir1 == Direction.UP) && (dir2 == Direction.DOWN)) ||
                ((dir1 == Direction.DOWN) && (dir2 == Direction.UP)));
    }
}
