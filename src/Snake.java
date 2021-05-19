import java.awt.*;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.*;
import java.util.List;

public class Snake {
    private Heading heading;
    public SnakePart head, tail;
    int length;

    public Snake() {
        heading = getRandomHeading();
        System.out.printf("first heading: %s%n", heading);
        head = new SnakePart(10,10);
        tail = head;
        moveHead();
        length = 2;
    }

    public static Heading getRandomHeading() {
        List<Heading> Headings = Arrays.asList(Heading.class.getEnumConstants());
        Random rand = new Random();
        return Headings.get(rand.nextInt(4));
    }

    public void moveHead() {
        SnakePart newNode = null;
        switch (heading) {
            case UP -> {
                newNode = new SnakePart(head.row-1, head.col);
            }

            case DOWN -> {
                newNode = new SnakePart(head.row+1, head.col);
            }

            case LEFT -> {
                newNode = new SnakePart(head.row, head.col-1);
            }

            case RIGHT -> {
                newNode = new SnakePart(head.row, head.col+1);
            }
        }
        newNode.next = head;
        head.previous = newNode;
        head = newNode;
    }

    public void removeTail() {
        if (tail != null) {
            tail = tail.previous;
            tail.next.previous = null;
            tail.next = null;
        }
    }

    public void move() {
        moveHead();
        removeTail();
    }

    public void draw(Graphics g2) {
        SnakePart sp = head;
        while(sp != null) {
            sp.draw(g2);
            sp = sp.next;
        }
        move();
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_LEFT:
                heading = Heading.LEFT;
                break;
            case KeyEvent.VK_UP:
                heading = Heading.UP;
                break;
            case KeyEvent.VK_RIGHT:
                heading = Heading.RIGHT;
                break;
            case KeyEvent.VK_DOWN:
                heading = Heading.DOWN;
                break;
        }
        System.out.println(heading);
    }

    public boolean tryEating(Cell c) {
        if (hasCollided()) return false;
        if(head.row == c.row && head.col == c.col) {
            if (c.ct == CellType.FOOD) {
                System.out.println("EVÉS!");
                length++;
                moveHead();
                c.next();
                return true;
            }
            else if (c.ct == CellType.STONE) {
                System.out.println("VÉGE, ÜTKÖZÉS!");
                return false;
            }
        }
        return true;
    }

    private boolean hasCollided() {
        return head.row < 0 || head.col < 0 || head.row > Config.cellSquareLength - 1 || head.col > Config.cellSquareLength - 1;
    }

    /*public Heading getHeading() {
        return this.heading;
    }

    public void setHeading(Heading newHeading) {
        this.heading = newHeading;
    }*/
}
