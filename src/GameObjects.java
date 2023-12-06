import java.util.Random;

class GameObjects {

    private final int FIELD_SIZE = 12;
    private final Player player;
    private Skeleton skeleton;
    private final Skeleton2 skeleton2;
    private final Cave cave;
    private final Treasure[] treasures;
    private final Trap[] traps;
    private final Slime[] slimes;


    private final int startSkeleton = 5;
    private final int startSkeleton2 = 8;


    private final boolean[][] spielFeld;


    public GameObjects() {
        GameField gameField = new GameField(FIELD_SIZE, FIELD_SIZE);
        this.spielFeld = GameField.getGameField();
        this.player = new Player(newPosition());
        this.skeleton = new Skeleton(newPosition());
        this.cave = new Cave(newPosition());
        this.treasures = new Treasure[2];
        this.traps = new Trap[GameState.getRoundCounter() * 2];

        int slimeAmount;
        if (GameState.getRoundCounter() < 2) {
            slimeAmount = 1;
        } else {
            slimeAmount = 2;
        }
        this.slimes = new Slime[slimeAmount];

        if (GameState.getRoundCounter() >= startSkeleton) {
            this.skeleton = new Skeleton(newPosition());
        } else {
            this.skeleton = new Skeleton(new Coordinate(-1, -1));
        }

        if (GameState.getRoundCounter() >= startSkeleton2) {
            this.skeleton2 = new Skeleton2(newPosition());
        } else {
            this.skeleton2 = new Skeleton2(new Coordinate(-1, -1));
        }
    }

    public void init() {
        initTreasures();
        initTraps();
        initSlimes();
    }

    private void initTreasures() {
        for (int i = 0; i < treasures.length; i++) {
            treasures[i] = new Treasure(newPosition());
        }
    }

    private void initTraps() {
        for (int i = 0; i < traps.length; i++) {
            traps[i] = new Trap(newPosition());
        }
    }

    private void initSlimes() {
        for (int i = 0; i < slimes.length; i++) {
            slimes[i] = new Slime(newPosition());
        }
    }

    private Coordinate newPosition() {
        Random random = new Random();
        int x;
        int y;

        do {
            x = random.nextInt(FIELD_SIZE - 1);
            y = random.nextInt(1, FIELD_SIZE - 1);
        } while (spielFeld[x][y]);

        spielFeld[x][y] = true;
        return new Coordinate(x, y);
    }


    public Player getPlayer() {
        return player;
    }

    public Skeleton getSkeleton() {
        return skeleton;
    }

    public Cave getCave() {
        return cave;
    }

    public Treasure[] getTreasures() {
        return treasures;
    }

    public Trap[] getTraps() {
        return traps;
    }

    public Skeleton2 getSkeleton2() {
        return skeleton2;
    }

    public Slime[] getSlimes() {
        return slimes;
    }

    public int getStartSkeleton() {
        return startSkeleton;
    }

    public int getStartSkeleton2() {
        return startSkeleton2;
    }
}