import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class Garden extends JPanel {

    private Snake snake;
    private Food food;
    private ArrayList<Stone> stones;

    boolean gameHasEnded = false;

    public Garden(Snake snake, Food food, ArrayList<Stone> stones) {
        this.snake = snake;
        this.food = food;
        this.stones = stones;
        setKeyEvents();
    }

    public void setKeyEvents() {
        this.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "pressed left");
        this.getActionMap().put("pressed left", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println("LEFT");
                snake.setHeading(Heading.LEFT);
            }
        });

        this.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "pressed right");
        this.getActionMap().put("pressed right", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println("RIGHT");
                snake.setHeading(Heading.RIGHT);
            }
        });

        this.getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "pressed DOWN");
        this.getActionMap().put("pressed DOWN", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println("DOWN");
                snake.setHeading(Heading.DOWN);
            }
        });

        this.getInputMap().put(KeyStroke.getKeyStroke("UP"), "pressed UP");
        this.getActionMap().put("pressed UP", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println("Down");
                snake.setHeading(Heading.UP);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        Toolkit.getDefaultToolkit().sync();
        Color c = g2.getColor();
        g2.setColor(Config.gardenColor);
        g2.fillRect(0, 0, this.getWidth(), this.getHeight());
        g2.setColor(Color.BLACK);

        for (int i = 0; i <= Config.cellSquareLength; i++) {
            g2.drawLine(Config.x, Config.y + Config.cellSize * i, Config.x + Config.GameArea, Config.y + Config.cellSize * i);
            g2.drawLine(Config.x + Config.cellSize * i, Config.y, Config.x + Config.cellSize * i, Config.y + Config.GameArea);
        }
        g2.setColor(c);
        System.out.println("food before draw: ");
        System.out.printf("%d %d\n", food.col, food.row);
        food.draw(g2);
        snake.draw(g2);
        boolean stillalive;
        stillalive = snake.tryEating(food);

        for (Stone s : stones) {
            s.draw(g2);
            stillalive &= snake.tryEating(s);
        }

        for (SnakePart snakePart : snake) {
            if (snakePart.col == snake.head.col && snakePart.row == snake.head.row) {
                stillalive = false;
            }
        }

        if (!stillalive) {
            System.out.println("VÉGE!!!!");
            gameHasEnded = true;
        } else {

            if (snake.justAte) {
                // check if the new food is on a stone
                food.next();

                for (Stone s : stones) {
                    if (s.col == food.col && s.row == food.row) {
                        food.next();
                    }
                    else {
                        break;
                    }
                }
                snake.justAte = false;
            }

        }
    }

    public void setStones(ArrayList<Stone> stones) {
        this.stones = stones;
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }


}
