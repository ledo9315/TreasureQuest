import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;


public class Skeleton implements IconInterface {
    private Coordinate coordinate;
    private final BufferedImage[][] images;
    private int currentSpriteIndex;
    private int direction; // 0 = links, 1 = rechts

    public Skeleton(Coordinate coordinate) {
        this.coordinate = coordinate;
        images = new BufferedImage[2][6]; // Anzahl der Sprites anpassen (hier: 6 pro Richtung)
        loadImage();
        currentSpriteIndex = 0;
        direction = 0; // Startrichtung auf links setzen
        setupAnimationTimer();
    }

    private void setupAnimationTimer() {
        int animationDelay = 800; // Verzögerung zwischen den Animationsschritten in Millisekunden (hier: 800ms)

        Timer animationTimer = new Timer();

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

    public void moveSkeleton(Coordinate snakeCoordinate, Coordinate playerCoordinate) {
        Coordinate neuePosition = new Coordinate(coordinate.getX(), coordinate.getY());

        // x
        if (playerCoordinate.getX() < snakeCoordinate.getX()) {
            neuePosition.setX(neuePosition.getX() - 1);
            setDirection(0); // Richtung auf links setzen
        } else if (playerCoordinate.getX() > snakeCoordinate.getX()) {
            neuePosition.setX(neuePosition.getX() + 1);
            setDirection(1); // Richtung auf rechts setzen
        }

        // y
        if (playerCoordinate.getY() < snakeCoordinate.getY()) {
            neuePosition.setY(neuePosition.getY() - 1);
        } else if (playerCoordinate.getY() > snakeCoordinate.getY()) {
            neuePosition.setY(neuePosition.getY() + 1);
        }

        this.coordinate = neuePosition;
    }

    public void startSkeleton(Player player, GamePanel gamePanel) {
        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                moveSkeleton(getCoordinate(), player.getCoordinate());
                gamePanel.repaint(); // Hier wird das Panel nach der Bewegung der Schlange neu gezeichnet
            }
        }, 1800, 800);
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
            // Bilder für links
            images[0][0] = ImageIO.read(new File("src/icons/skeleton-left1.png"));
            images[0][1] = ImageIO.read(new File("src/icons/skeleton-left2.png"));
            images[0][2] = ImageIO.read(new File("src/icons/skeleton-left3.png"));
            images[0][3] = ImageIO.read(new File("src/icons/skeleton-left4.png"));
            images[0][4] = ImageIO.read(new File("src/icons/skeleton-left5.png"));
            images[0][5] = ImageIO.read(new File("src/icons/skeleton-left6.png"));

            // Bilder für rechts
            images[1][0] = ImageIO.read(new File("src/icons/skeleton-right1.png"));
            images[1][1] = ImageIO.read(new File("src/icons/skeleton-right2.png"));
            images[1][2] = ImageIO.read(new File("src/icons/skeleton-right3.png"));
            images[1][3] = ImageIO.read(new File("src/icons/skeleton-right4.png"));
            images[1][4] = ImageIO.read(new File("src/icons/skeleton-right5.png"));
            images[1][5] = ImageIO.read(new File("src/icons/skeleton-right6.png"));

        } catch (IOException exc) {
            System.out.println("Error opening image file treasure: " + exc.getMessage());
        }
    }

    @Override
    public void draw(Graphics g, ImageObserver observer, int xpos, int ypos, int breite, int hoehe) {
        int x = breite / GameField.gameField.length * xpos;
        int y = hoehe / GameField.gameField[0].length * ypos;

        BufferedImage currentSprite = images[direction][currentSpriteIndex];
        g.drawImage(currentSprite, x, y, breite / GameField.gameField.length, hoehe / GameField.gameField[0].length, observer);
    }
}
