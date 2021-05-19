import javax.swing.*;
import java.awt.*;

public class Garden extends JPanel {
    public Garden() {

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        Toolkit.getDefaultToolkit().sync();
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.setColor(Color.BLACK);

        for (int i = 0; i <= Config.cellSquareLength; i++) {
            g.drawLine(Config.x, Config.y + Config.cellSize * i, Config.x + Config.GameArea, Config.y + Config.cellSize * i);
            g.drawLine(Config.x + Config.cellSize * i, Config.y, Config.x + Config.cellSize * i, Config.y + Config.GameArea);
        }
        g.setColor(c);
        /*snake.render(g);
        food.render(g);
        boolean stillalive;
        stillalive = snake.tryEating(food);
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
