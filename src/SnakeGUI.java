import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class SnakeGUI {

    private JFrame frame;
    private Timer timer;

    private Garden garden;

    public Snake snake;
    public Food food;
    public ArrayList<Stone> stones;

    public SnakeGUI() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar bar = new JMenuBar();
        JMenu menu = new JMenu("Menü");
        JMenuItem menuitem = new JMenuItem("Új játék");
        menu.add(menuitem);
        JMenuItem menuitem2 = new JMenuItem("Highscore");
        menu.add(menuitem2);
        frame.setJMenuBar(bar);
        bar.add(menu);

        // init kövek
        snake = new Snake();
        food = new Food();
        stones = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            stones.add(new Stone());
        }


        garden = new Garden(snake, food, stones);
        garden.setPreferredSize(new Dimension(450, 500));
        frame.getContentPane().add(BorderLayout.CENTER, garden);


        JPanel buttonPanel = new JPanel();

        JButton backgroundButton = new JButton("New game");
        backgroundButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println("new game button pressed");
            }
        });

        buttonPanel.add(backgroundButton);

        frame.getContentPane().add(BorderLayout.SOUTH, buttonPanel);


        timer = new Timer(150, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (garden.gameHasEnded) {
                    System.out.println("A GUI IS MEGTUDTA HOGY VÉGE");
                    timer.stop();
                }

                frame.repaint();
            }
        });
        timer.start();

        frame.setSize(500, 500);
        frame.pack();
        frame.setVisible(true);
    }

}


