import javax.imageio.ImageIO;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.TimerTask;

public class Slime extends Trap {

    private BufferedImage[][] images;
    private int currentSpriteIndex;
    private Timer slimeTimer;
    private int direction; // 0 = links, 1 = rechts

    public Slime(Coordinate coordinate) {
        super(coordinate);
        images = new BufferedImage[2][4]; // Anzahl der Slime-Sprites anpassen (hier: 4)
        loadImages();
        currentSpriteIndex = 0;
        setupAnimationTimer();
        currentSpriteIndex = 0;
        direction = 0; // Startrichtung auf links setzen
        startSlime();
    }

    private void setupAnimationTimer() {
        int animationDelay = 800; // Verzögerung zwischen den Animationsschritten in Millisekunden (hier: 800ms)

        java.util.Timer animationTimer = new java.util.Timer();

        animationTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                // Nächstes Sprite
                currentSpriteIndex = (currentSpriteIndex + 1) % images[direction].length;
            }
        }, animationDelay, animationDelay);
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }


    public void startSlime() {
        java.util.Timer timer = new java.util.Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                moveSlime();
            }
        }, 1200, 800);
    }

    public void moveSlime() {
        Random random = new Random();
        switch (random.nextInt(4)) {
            //left
            case 0 -> {
                if (getCoordinate().getX() > 0) {
                    getCoordinate().setX(getCoordinate().getX() - 1);
                    setDirection(0); // Richtung auf links setzen
                }
            }
            //right
            case 1 -> {
                if (getCoordinate().getX() < GameField.getGameField()[0].length - 1) {
                    getCoordinate().setX(getCoordinate().getX() + 1);
                    setDirection(1); // Richtung auf rechts setzen
                }
            }
            //up
            case 2 -> {
                if (getCoordinate().getY() > 0) {
                    getCoordinate().setY(getCoordinate().getY() - 1);
                }
            }
            //down
            case 3 -> {
                if (getCoordinate().getY() < GameField.getGameField()[0].length - 1) {
                    getCoordinate().setY(getCoordinate().getY() + 1);
                }
            }


            default -> throw new IllegalStateException("Unexpected value: " + random.nextInt(4));
        }
    }


    @Override
    public void draw(Graphics g, ImageObserver observer, int xpos, int ypos, int breite, int hoehe) {
        int x = breite / GameField.gameField.length * xpos;
        int y = hoehe / GameField.gameField[0].length * ypos;

        BufferedImage currentSprite = images[direction][currentSpriteIndex];
        g.drawImage(currentSprite, x, y, breite / GameField.gameField.length, hoehe / GameField.gameField[0].length, observer);
    }


    public void loadImages() {
        try {
            // Bilder für links
            images[0][0] = ImageIO.read(new File("src/icons/slime_spritesheet_01_left.png"));
            images[0][1] = ImageIO.read(new File("src/icons/slime_spritesheet_02_left.png"));
            images[0][2] = ImageIO.read(new File("src/icons/slime_spritesheet_03_left.png"));
            images[0][3] = ImageIO.read(new File("src/icons/slime_spritesheet_04_left.png"));



            // Bilder für rechts
            images[1][0] = ImageIO.read(new File("src/icons/slime_spritesheet_01_right.png"));
            images[1][1] = ImageIO.read(new File("src/icons/slime_spritesheet_02_right.png"));
            images[1][2] = ImageIO.read(new File("src/icons/slime_spritesheet_03_right.png"));
            images[1][3] = ImageIO.read(new File("src/icons/slime_spritesheet_04_right.png"));



        } catch (IOException exc) {
            System.out.println("Error opening image file treasure: " + exc.getMessage());
        }
    }

}
