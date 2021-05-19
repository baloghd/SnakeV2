import static org.junit.jupiter.api.Assertions.*;

class SnakeTest {
    Snake testsnake;

    @org.junit.jupiter.api.Test
    void move() {
        testsnake = new Snake(Heading.RIGHT);
        testsnake.move();
        testsnake.printSnake();
        assertEquals(10, testsnake.head.row);
        assertEquals(11, testsnake.head.col);

        testsnake.setHeading(Heading.UP);
        testsnake.move();
        testsnake.printSnake();
        assertEquals(9, testsnake.head.row);
        assertEquals(11, testsnake.head.col);

        testsnake.setHeading(Heading.LEFT);
        testsnake.move();
        testsnake.printSnake();
        assertEquals(9, testsnake.head.row);
        assertEquals(10, testsnake.head.col);

        testsnake.setHeading(Heading.DOWN);
        testsnake.move();
        testsnake.printSnake();
        assertEquals(10, testsnake.head.row);
        assertEquals(10, testsnake.head.col);

    }

    @org.junit.jupiter.api.Test
    void tryEatingFood() {
        Food food = new Food(10,10);
        testsnake = new Snake();
        assertTrue(testsnake.tryEating(food));
    }

    @org.junit.jupiter.api.Test
    void tryEatingStone() {
        Stone stone = new Stone(10,11);
        testsnake = new Snake(Heading.RIGHT);
        testsnake.move();
        assertFalse(testsnake.tryEating(stone));
    }

}