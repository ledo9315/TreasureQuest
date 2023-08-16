import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class GamePanel extends JPanel implements KeyListener {
    private BufferedImage backgroundImage;
    private BufferedImage woodBG;
    private GameObjects gameObjects;
    private GameState gameState;
    private SoundManager soundManager;


    public GamePanel() {
        soundManager = new SoundManager();
        soundManager.loadSoundtrack("src/8bitAdventureSoundtrack.wav");
        soundManager.playSoundtrack();

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                gameState.checkGameState();
            }
        }, 1000, 10);


        restart();
        addKeyListener(this);
        setFocusable(true);

        try {
            backgroundImage = ImageIO.read(new File("src/icons/background.png"));
        } catch (IOException e) {
            System.out.println("Error opening image file(background)");
        }

        try {
            woodBG = ImageIO.read(new File("src/icons/wood-bg.png"));
        } catch (IOException e) {
            System.out.println("Error opening image file(background)");
        }

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        draw(g, getWidth(), getHeight());
    }

    public void draw(Graphics g, int width, int height) {

        Treasure[] treasures = gameObjects.getTreasures();
            for (Treasure treasure : treasures) {
                if (treasure != null) {
                    treasure.draw(g, this, treasure.getCoordinate().getX(), treasure.getCoordinate().getY(), width, height);
                }
            }

        Trap[] traps = gameObjects.getTraps();
        for (Trap trap : traps) {
            if (trap != null) {
                trap.draw(g, this, trap.getCoordinate().getX(), trap.getCoordinate().getY(), width, height);
            }
        }

        Slime[] slimes = gameObjects.getSlimes();
        for (Slime slime : slimes) {
            if (slime != null) {
                slime.draw(g, this, slime.getCoordinate().getX(), slime.getCoordinate().getY(), width, height);
            }
        }


        gameObjects.getCave().draw(g, this, gameObjects.getCave().getCoordinate().getX(), gameObjects.getCave().getCoordinate().getY(), width, height);
        gameObjects.getSkeleton().draw(g, this, gameObjects.getSkeleton().getCoordinate().getX(), gameObjects.getSkeleton().getCoordinate().getY(), width, height);
        gameObjects.getSkeleton2().draw(g, this, gameObjects.getSkeleton2().getCoordinate().getX(), gameObjects.getSkeleton2().getCoordinate().getY(), width, height);
        gameObjects.getPlayer().draw(g, this, gameObjects.getPlayer().getCoordinate().getX(), gameObjects.getPlayer().getCoordinate().getY(), width, height);
        g.drawImage(woodBG, 0, 0, 130, 50, this);
        g.drawImage(woodBG, getWidth() - 160, 0, 160, 50, this);

        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawImage(treasures[0].getImage(), getWidth() - 135, 10, 50, 30, this);
        g.drawString(gameState.getTreasureCounter() + " von " + treasures.length, width - 85, 30);
        g.drawString("Runde " + GameState.getRoundCounter(), 30, 30);
        repaint();
    }


    public void restart() {
        removeAll(); //Entfernt alle Objekte aus dem Container
        revalidate();
        GameObjects gameObjects = new GameObjects(this);
        this.gameObjects = gameObjects;
        gameObjects.init();
        this.gameState = new GameState(this, gameObjects);

        if (GameState.getRoundCounter() >= gameObjects.getStartSkeleton()) {
            gameObjects.getSkeleton().startSkeleton(gameObjects.getPlayer(), this);
        }
        if (GameState.getRoundCounter() >= gameObjects.getStartSkeleton2()) {
            gameObjects.getSkeleton2().startSkeleton(gameObjects.getPlayer(), this);
        }

    }


    public void keyTyped(KeyEvent e) {
        gameObjects.getPlayer().movePlayer(e.getKeyChar());
        repaint();
    }


    public void keyPressed(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {

    }
}


