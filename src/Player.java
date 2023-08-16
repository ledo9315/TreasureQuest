import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

public class Player implements IconInterface {
    private Coordinate coordinate;
    private BufferedImage[] images;
    private int currentSpriteIndex;
    private Timer animationTimer;

    public Player(Coordinate coordinate) {
        this.coordinate = coordinate;
        images = new BufferedImage[6]; // Anzahl der Sprites anpassen (hier: 4)
        loadImage();
        currentSpriteIndex = 0;
        setupAnimationTimer();
    }

    private void setupAnimationTimer() {
        int animationDelay = 200; // Verzögerung zwischen den Animationsschritten in Millisekunden (hier: 200ms)

        animationTimer = new Timer(animationDelay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Nächstes Sprite
                currentSpriteIndex = (currentSpriteIndex + 1) % images.length;
            }
        });

        animationTimer.start();
    }

    public void movePlayer(char keyChar) {
        switch (keyChar) {
            case 'w':
                moveUp();
                break;
            case 'a':
                moveLeft();
                break;
            case 's':
                moveDown();
                break;
            case 'd':
                moveRight();
                break;
            default:
                break;
        }
    }

    public void moveUp() {
        int playerY = coordinate.getY();
        if (playerY > 0) {
            coordinate.setY(playerY - 1);
        }
    }

    public void moveLeft() {
        int playerX = coordinate.getX();
        if (playerX > 0) {
            coordinate.setX(playerX - 1);
        }
    }

    public void moveDown() {
        int playerY = coordinate.getY();
        if (playerY < GameField.getGameField()[0].length - 1) {
            coordinate.setY(playerY + 1);
        }
    }

    public void moveRight() {
        int playerX = coordinate.getX();
        if (playerX < GameField.getGameField().length - 1) {
            coordinate.setX(playerX + 1);
        }
    }

    @Override
    public Coordinate getCoordinate() {
        return coordinate;
    }

    @Override
    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    @Override
    public void loadImage() {
        try {
            images[0] = ImageIO.read(new File("src/icons/player01.png"));
            images[1] = ImageIO.read(new File("src/icons/player02.png"));
            images[2] = ImageIO.read(new File("src/icons/player03.png"));
            images[3] = ImageIO.read(new File("src/icons/player04.png"));
            images[4] = ImageIO.read(new File("src/icons/player05.png"));
            images[5] = ImageIO.read(new File("src/icons/player06.png"));
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

}
