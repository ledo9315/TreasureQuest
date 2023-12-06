import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame extends JFrame {

    private final StartScreenPanel startScreenPanel;
    private GamePanel gamePanel;

    public GameFrame() {
        setTitle("GoldFieber");
        setSize(1200, 720);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        startScreenPanel = new StartScreenPanel();
        startScreenPanel.setStartGameListener(e -> {
            remove(startScreenPanel);
            gamePanel = new GamePanel();
            add(gamePanel);
            revalidate();
            repaint();
            gamePanel.requestFocus();
        });

        startScreenPanel.setExitGameListener(e -> System.exit(0));

        add(startScreenPanel);

        setVisible(true);
    }
}

