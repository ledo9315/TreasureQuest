import javax.swing.*;

class GameState {
    GameObjects gameObjects;
    private final GamePanel gamePanel;

    private int treasureCounter;
    private static int roundCounter;

    public GameState(GamePanel gamePanel, GameObjects gameObjects) {
        this.gameObjects = gameObjects;
        this.gamePanel = gamePanel;
    }

    public void checkGameState() {
        checkAmountOfTreasures();
        if (lose() || win()) {
            gamePanel.restart();
        }
    }

    public void checkAmountOfTreasures() {
        if (treasureCounter < gameObjects.getTreasures().length) {
            for (Treasure treasure : gameObjects.getTreasures()) {
                if (gameObjects.getPlayer().getCoordinate().equals(treasure.getCoordinate())) {
                    treasureCounter++;
                    treasure.setCoordinate(new Coordinate(-1, -1));
                }
            }
        }
    }

    public boolean win() {
        if (treasureCounter == gameObjects.getTreasures().length && gameObjects.getPlayer().getCoordinate().equals(gameObjects.getCave().getCoordinate())) {
            roundCounter++;
            return true;
        }
        return false;
    }

    public boolean lose() {
        if (gameObjects.getPlayer().getCoordinate().equals(gameObjects.getSkeleton().getCoordinate()) || gameObjects.getPlayer().getCoordinate().equals(gameObjects.getSkeleton2().getCoordinate())) {
            roundCounter = 0;
            int option = JOptionPane.showConfirmDialog(null, "Zzz... Das Skelett hat dich. Willst du nochmal spielen?");
            if (option == JOptionPane.YES_OPTION) {
                return true;
            } else {
                System.exit(0);
            }
        }

        for (Trap trap : gameObjects.getTraps()) {
            if (gameObjects.getPlayer().getCoordinate().equals(trap.getCoordinate())) {
                int option = JOptionPane.showConfirmDialog(null, "Du bist in eine Falle gelaufen. Willst du nochmal spielen?");
                if (option == JOptionPane.YES_OPTION) {
                    roundCounter = 0;
                    return true;
                } else {
                    System.exit(0);
                }
            }
        }

        for (Slime slime : gameObjects.getSlimes()) {
            if (gameObjects.getPlayer().getCoordinate().equals(slime .getCoordinate())) {
                int option = JOptionPane.showConfirmDialog(null, "Du bist in ein Slime gelaufen. Willst du nochmal spielen?");
                if (option == JOptionPane.YES_OPTION) {
                    roundCounter = 0;
                    return true;
                } else {
                    System.exit(0);
                }
            }
        }

        return false;
    }

    public int getTreasureCounter() {
        return treasureCounter;
    }

    public static int getRoundCounter() {
        return roundCounter;
    }
}