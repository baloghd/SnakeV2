import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SnakeGUI {

    private JFrame frame;
    private Timer timer;

    private Garden drawArea;

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


        drawArea = new Garden(snake, food, stones);
        drawArea.setPreferredSize(new Dimension(450, 500));
        frame.getContentPane().add(BorderLayout.CENTER, drawArea);


        JPanel buttonPanel = new JPanel();

        JButton backgroundButton = new JButton("Change background");
        backgroundButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

            }
        });

        buttonPanel.add(backgroundButton);

        frame.getContentPane().add(BorderLayout.SOUTH, buttonPanel);


        timer = new Timer(600, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //Dimension d = drawArea.getSize();
//                for (UFO ufo : ufos) {
//                    ufo.move(d.width, d.height);
//                }
                frame.repaint();
            }
        });
        timer.start();

        frame.setSize(500, 500);
        frame.pack();
        frame.setVisible(true);
    }

}


