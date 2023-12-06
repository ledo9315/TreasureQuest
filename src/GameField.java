public class GameField {



    public static boolean[][] gameField;

    public GameField(int xFieldSize, int yFieldSize) {
        gameField = new boolean[xFieldSize][yFieldSize];

        for (int i = 0; i < gameField.length; i++) {
            for (int j = 0; j < gameField[0].length; j++) {
                gameField[i][j] = false;
            }
        }
    }

    public static boolean[][] getGameField() {
        return gameField;
    }
}


