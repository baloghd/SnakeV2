import java.util.Iterator;

class SnakeIterator implements Iterator<SnakePart>  {
    private SnakePart cursor = null;
    SnakeIterator(Snake snake) {
        if (snake.head.next != null) {
            cursor = snake.head.next;
        }
    }
    public boolean hasNext() {
        return cursor.next != null;
    }

    public SnakePart next() {
        cursor = cursor.next;
        return cursor.previous;
    }

}