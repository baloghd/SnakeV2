import java.awt.*;

public class Stone extends Cell {

    public Stone() {
        super();
        ct = CellType.STONE;
        cellColor = Color.BLUE;
    }

    @Override
    public void setUpRender(Graphics2D g) {
        g.fillRect(x, y, Config.cellSize, Config.cellSize);
    }
}