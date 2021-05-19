import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;

public class SnakeGUI {

    private JFrame frame;
    private Timer timer;

    private Garden garden;

    public Snake snake;
    public Food food;
    public ArrayList<Stone> stones;
    public long starttime;

    public void showHighScoreWindow() {
        JFrame frame2 = new JFrame();
        frame2.setSize(250,300);

        JPanel main2 = new JPanel();

        DatabaseConnector dbc = new DatabaseConnector(false);
        JLabel highscores = new JLabel();
        highscores.setText(dbc.getBest10());



        JTextField playername = new JTextField();
        playername.setText("Játékos");
        main2.add(highscores);
        main2.add(playername);

        if (dbc.worstScoreTop10() <= snake.length - 2) {
            JButton saveHighscore = new JButton();
            saveHighscore.setText("Save highscore");
            saveHighscore.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dbc.saveHighscore(playername.getText(), snake.length - 2);
                    System.out.printf("highscore saved for %s\n", playername.getText());

                }
            });
            main2.add(saveHighscore);
        }

        frame2.getContentPane().add(main2);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.setTitle("Vége a játéknak!");
        frame2.setVisible(true);
    }

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

        // init kövek, kígyó, étel
        snake = new Snake();
        food = new Food();
        stones = new ArrayList<>();
        for (int i = 0; i < Config.numStones; i++) {
            Stone newstone = new Stone();
            do {
                newstone.next();
            } while ((newstone.col == food.col) && (newstone.row == food.row));
            stones.add(newstone);
        }



        int seconds = 0;
        Date now = new Date();
        starttime = now.getTime() / 1000L;

        garden = new Garden(snake, food, stones);
        garden.setPreferredSize(new Dimension(450, 500));
        frame.getContentPane().add(BorderLayout.CENTER, garden);
        JPanel buttonPanel = new JPanel();
        JLabel label = new JLabel();
        label.setText("00:00");
        JButton backgroundButton = new JButton("New game");
        backgroundButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println("new game button pressed");
                stones = new ArrayList<>();
                for (int i = 0; i < Config.numStones; i++) {
                    stones.add(new Stone());
                }
                snake = new Snake();
                garden.setStones(stones);
                garden.setSnake(snake);
                garden.requestFocus();
                garden.gameHasEnded = false;
                starttime = (new Date()).getTime() / 1000L;
                timer.restart();
            }
        });
        buttonPanel.add(label);
        buttonPanel.add(backgroundButton);

        frame.getContentPane().add(BorderLayout.SOUTH, buttonPanel);

        timer = new Timer(150, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (garden.gameHasEnded) {
                    System.out.println("A GUI IS MEGTUDTA HOGY VÉGE");
                    timer.stop();
                    showHighScoreWindow();
                } else {
                    Date now = new Date();
                    label.setText(String.format("Idő: %d",(now.getTime()/1000l) - starttime));
                    frame.repaint();
                }
            }
        });
        timer.start();

        frame.setSize(500, 500);
        frame.pack();
        frame.setVisible(true);
    }

}


