import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class StartScreenPanel extends JPanel {

    private final JButton startGameButton;
    private final JButton exitButton;
    private BufferedImage backgroundImage;
    private BufferedImage title;
    private BufferedImage movement;

    public StartScreenPanel() {
        setLayout(new GridBagLayout());

        try {
            backgroundImage = ImageIO.read(new File("src/icons/background.png"));
        } catch (IOException e) {
            System.out.println("Error opening image file(background)");
        }

        try {
            title = ImageIO.read(new File("src/icons/title.png"));
        } catch (IOException e) {
            System.out.println("Error opening image file(title)");
        }

        try {
            movement = ImageIO.read(new File("src/icons/movement.png"));
        } catch (IOException e) {
            System.out.println("Error opening image file(title)");
        }

        JPanel buttonPanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
            }
        };
        buttonPanel.setBackground(new Color(0,0,0,0));
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 0, 10, 0);

        startGameButton = new JButton("Neues Spiel");
        startGameButton.setPreferredSize(new Dimension(180, 40));
        startGameButton.setBackground(Color.darkGray);
        startGameButton.setForeground(Color.white);
        constraints.gridx = 0;
        constraints.gridy = 0;
        buttonPanel.add(startGameButton, constraints);

        exitButton = new JButton("Exit");
        exitButton.setPreferredSize(new Dimension(180, 40));
        exitButton.setBackground(Color.darkGray);
        exitButton.setForeground(Color.white);
        constraints.gridx = 0;
        constraints.gridy = 1;
        buttonPanel.add(exitButton, constraints);

        add(buttonPanel);
    }

    public void setStartGameListener(ActionListener listener) {
        startGameButton.addActionListener(listener);
    }

    public void setExitGameListener(ActionListener listener) {
        exitButton.addActionListener(listener);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        g.drawImage(title, getWidth()/3, 200, 400, 50, this);
        g.drawImage(movement, getWidth()/3+50, 500, 300, 30, this);
    }
}
