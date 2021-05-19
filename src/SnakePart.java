import java.awt.*;

public class SnakePart {
    public int row, col;
    public int x, y;
    public CellType ct;
    public SnakePart next, previous;
    public Color color;

    public SnakePart(int row, int col) {
        ct = CellType.SNAKE;
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
