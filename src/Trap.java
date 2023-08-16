import javax.imageio.ImageIO;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

public class Trap implements IconInterface {

    private Coordinate coordinate;
    private BufferedImage[] images;
    private int currentSpriteIndex;
    private Timer animationTimer;

    public Trap(Coordinate coordinate) {
        this.coordinate = coordinate;
        images = new BufferedImage[2]; // Anzahl der Sprites anpassen (hier: 4)
        loadImage();
        currentSpriteIndex = 0;
        setupAnimationTimer();
    }

    private void setupAnimationTimer() {
        int animationDelay = 800; // Verzögerung zwischen den Animationsschritten in Millisekunden (hier: 200ms)

        animationTimer = new Timer(animationDelay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Nächstes Sprite
                currentSpriteIndex = (currentSpriteIndex + 1) % images.length;
            }
        });

        animationTimer.start();
    }

    @Override
    public void loadImage() {
        try {
            images[0] = ImageIO.read(new File("src/icons/spike03.png"));
            images[1] = ImageIO.read(new File("src/icons/spike04.png"));

        } catch (IOException exc) {
            System.out.println("Error opening image file treasure: " + exc.getMessage());
        }
    }

    @Override
    public void draw(Graphics g, ImageObserver observer, int xpos, int ypos, int breite, int hoehe) {
        int x = breite / GameField.gameField.length * xpos;
        int y = hoehe / GameField.gameField[0].length * ypos;

        BufferedImage currentSprite = images[currentSpriteIndex];
        g.drawImage(currentSprite, x, y, breite / GameField.gameField.length, hoehe / GameField.gameField[0].length, observer);
    }

    @Override
    public Coordinate getCoordinate() {
        return coordinate;
    }

    @Override
    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }
}
