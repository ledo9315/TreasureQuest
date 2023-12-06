import java.awt.*;
import java.awt.image.ImageObserver;

public interface IconInterface {
    void loadImage();
    void draw(Graphics g, ImageObserver observer, int xpos, int ypos, int breite, int hoehe);
    Coordinate getCoordinate();
    void setCoordinate(Coordinate coordinate);


}
