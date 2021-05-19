import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Garden extends JPanel {

    private Snake snake;
    private Food food;
    private ArrayList<Stone> stones;

    public Garden(Snake snake, Food food, ArrayList<Stone> stones) {
        this.snake = snake;
        this.food = food;
        this.stones = stones;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        Toolkit.getDefaultToolkit().sync();
        Color c = g2.getColor();
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, this.getWidth(), this.getHeight());
        g2.setColor(Color.BLACK);

        for (int i = 0; i <= Config.cellSquareLength; i++) {
            g2.drawLine(Config.x, Config.y + Config.cellSize * i, Config.x + Config.GameArea, Config.y + Config.cellSize * i);
            g2.drawLine(Config.x + Config.cellSize * i, Config.y, Config.x + Config.cellSize * i, Config.y + Config.GameArea);
        }
        g2.setColor(c);

        food.draw(g2);
        snake.draw(g2);
        boolean stillalive;
        stillalive = snake.tryEating(food);

        for (Stone s :
                stones) {
            s.draw(g2);
            //stillalive &= snake.tryEating(s);
        }
        /*

        for (Stone s :
                stones) {
            s.render(g);
            stillalive &= snake.tryEating(s);
        }

        if (!stillalive) {
            this.endGame();
            //this.getParent().endGame();
        }*/
    }


}
