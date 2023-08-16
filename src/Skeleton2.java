import java.util.Timer;
import java.util.TimerTask;

public class Skeleton2 extends Skeleton {
    public Skeleton2(Coordinate coordinate) {
        super(coordinate);
    }

    @Override
    public void startSkeleton(Player player, GamePanel gamePanel) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                moveSkeleton(getCoordinate(), player.getCoordinate());
                gamePanel.repaint(); // Hier wird das Panel nach der Bewegung der Schlange neu gezeichnet
            }
        }, 2400, 800);
    }

}
