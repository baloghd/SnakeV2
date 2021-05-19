import java.awt.*;

public class SnakePart {
    int row, col;
    int x, y;
    SnakePart next, previous;
    Color color;

    public SnakePart(int row, int col) {
        this.row = row;
        this.col = col;
        x = Config.x + col * Config.cellSize;
        y = Config.y + row * Config.cellSize;
        color = Config.snakeColor;
    }

    public void draw(Graphics g) {
        Color c = g.getColor();
        g.setColor(color);
        g.fillOval(x, y, Config.cellSize, Config.cellSize);
        g.setColor(c);
    }
}
