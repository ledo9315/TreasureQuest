import java.awt.*;
import java.awt.image.ImageObserver;

public interface IconInterface {
    public void loadImage();
    public void draw(Graphics g, ImageObserver observer, int xpos, int ypos, int breite, int hoehe);
    public Coordinate getCoordinate();
    public void setCoordinate(Coordinate coordinate);


}
