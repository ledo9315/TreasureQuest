import java.util.Random;

class GameObjects {

    private final GamePanel gamePanel;
    private final GameField gameField;
    private int FIELDSIZE = 12;


    private Player player;
    private Skeleton skeleton;
    private Skeleton2 skeleton2;
    private Cave cave;
    private Treasure[] treasures;
    private Trap[] traps;
    private Slime[] slimes;
    private int slimeAmount;



    private int startSkeleton = 5;
    private int startSkeleton2 = 8;



    private boolean[][] spielFeld;


    public GameObjects(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.gameField = new GameField(FIELDSIZE, FIELDSIZE);
        this.spielFeld = GameField.getGameField();
        this.player = new Player(neuePosition());
        this.skeleton = new Skeleton(neuePosition());
        this.cave = new Cave(neuePosition());
        this.treasures = new Treasure[2];
        this.traps = new Trap[GameState.getRoundCounter()*2];

        if(GameState.getRoundCounter() < 2) {
            slimeAmount = 1;
        }
        else {
            slimeAmount = 2;
        }
        this.slimes = new Slime[slimeAmount];

        if (GameState.getRoundCounter() >= startSkeleton) {
            this.skeleton = new Skeleton(neuePosition());
        } else {
            this.skeleton = new Skeleton(new Coordinate(-1, -1));
        }

        if (GameState.getRoundCounter() >= startSkeleton2) {
            this.skeleton2 = new Skeleton2(neuePosition());
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
            treasures[i] = new Treasure(neuePosition());
        }
    }

    private void initTraps() {
        for (int i = 0; i < traps.length; i++) {
            traps[i] = new Trap(neuePosition());
        }
    }
    private void initSlimes() {
        for (int i = 0; i < slimes.length; i++) {
            slimes[i] = new Slime(neuePosition());
        }
    }

    private Coordinate neuePosition() {
        Random random = new Random();
        int x;
        int y;

        do {
            x = random.nextInt(FIELDSIZE - 1);
            y = random.nextInt(1,FIELDSIZE - 1);
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