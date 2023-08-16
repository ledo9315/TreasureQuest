import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

public class Treasure implements IconInterface {

    private Coordinate coordinate;
    public BufferedImage image;


    public Treasure(Coordinate coordinate) {
        this.coordinate = coordinate;
        loadImage();
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
            image = ImageIO.read(new File("src/icons/treasure.png"));
        } catch (IOException exc) {
            System.out.println("Error opening image file treasure: " + exc.getMessage());
        }
    }

    @Override
    public void draw(Graphics g, ImageObserver observer, int xpos, int ypos, int breite, int hoehe) {
        int x = breite / GameField.gameField.length * xpos;
        int y = hoehe / GameField.gameField[0].length * ypos;

        g.drawImage(image, x, y, breite / GameField.gameField.length, hoehe / GameField.gameField[0].length, observer);
    }


    public BufferedImage getImage() {
        return image;
    }
}
