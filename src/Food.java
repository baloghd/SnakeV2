import java.awt.*;
public class Food extends Cell {

    public Food() {
        super();
        ct = CellType.FOOD;
        cellColor = Config.foodColor;
    }

    public Food(int fr, int fc) {
        super(fr, fc);
        ct = CellType.FOOD;
        cellColor = Config.foodColor;
    }

    @Override
    public void setUpRender(Graphics2D g) {
        g.fillOval(x, y, Config.cellSize, Config.cellSize);
    }
}
