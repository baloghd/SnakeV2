import java.awt.*;
import java.util.Random;

abstract public class Cell {
    int row, col;
    int x, y;
    Random random;
    Color cellColor;
    CellType ct;

    public Cell() {
        random = new Random();
        next();
    }

    public void next() {
        this.row = random.nextInt(Config.cellSquareLength);
        this.col = random.nextInt(Config.cellSquareLength);
        x = Config.x + col * Config.cellSize;
        y = Config.y + row * Config.cellSize;
    }

    public void draw(Graphics2D g) {
        Color c = g.getColor();
        g.setColor(cellColor);
        setUpRender(g);
        g.setColor(c);
    }

    public abstract void setUpRender(Graphics2D g);
}
