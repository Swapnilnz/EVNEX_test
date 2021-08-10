public abstract class Game {
    String playerOne;
    String playerTwo;
    int playerOneScore;
    int playerTwoScore;

    public Game(String playerOne, String playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        playerOneScore = 0;
        playerTwoScore = 0;
    }

    void increaseScore(String playerName) {
        if (playerName.equalsIgnoreCase(playerOne)) {
            playerOneScore += 1;
        } else if (playerName.equalsIgnoreCase(playerTwo)) {
            playerTwoScore += 1;
        }
    };

    abstract String gameWinner();

    abstract String getScore();
}
