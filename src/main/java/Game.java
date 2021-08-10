import java.util.ArrayList;
import java.util.List;

/**
 * Holds state of Game of one Set. Can be of type 'Normal', or 'Tie breaker'.
 */
public class Game {
    String playerOne;
    String playerTwo;
    int playerOneScore;
    int playerTwoScore;
    GameType gameType;

    private static final ArrayList<String> SCORENAMES = new ArrayList<>(List.of("0", "15", "30", "40"));

    public Game(String playerOne, String playerTwo, GameType gameType) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.gameType = gameType;
        playerOneScore = 0;
        playerTwoScore = 0;
    }

    /**
     * Add 1 to a player's game score
     * @param playerName player's whose score to add to
     */
    void increaseScore(String playerName) {
        if (playerName.equalsIgnoreCase(playerOne)) {
            playerOneScore += 1;
        } else if (playerName.equalsIgnoreCase(playerTwo)) {
            playerTwoScore += 1;
        }
    };

    /**
     * Return the player's name who has won the game, or null if game is still going
     * @return String of player name, or null
     */
    public String gameWinner() {
        int scoreThreshold = 4;
        if (gameType == GameType.TIEBREAK) {
            scoreThreshold = 7;
        }
        if (playerOneScore >= scoreThreshold && playerOneScore - playerTwoScore >= 2) {
            return playerOne;
        }
        if (playerTwoScore >= scoreThreshold && playerTwoScore - playerOneScore >= 2) {
            return playerTwo;
        }
        return null;
    }

    /**
     * Get a stringified of the game, e.g. "15-15", or "1-1" if tiebreaker game.
     * @return a formatted string of the game's score (empty if game has just started)
     */
    public String getScore() {
        if (playerOneScore == 0 && playerTwoScore == 0) {
            return "";
        }

        if (gameType == GameType.NORMAL) {
            return getNormalScore();
        }

        if (gameType == GameType.TIEBREAK) {
            return String.format("%s-%s", playerOneScore, playerTwoScore);
        }

        return null;
    }

    /**
     * Get a formatted string of the game's score if it is a normal game (non-tie-breaker)
     * @return formatted string of the game score
     */
    private String getNormalScore() {
        if (playerOneScore >= 3 && playerTwoScore >= 3) {
            if (playerOneScore > playerTwoScore) {
                return "Advantage " + playerOne;
            } else if (playerTwoScore > playerOneScore) {
                return "Advantage " + playerTwo;
            } else {
                return "Deuce";
            }
        }
        return String.format("%s-%s", SCORENAMES.get(playerOneScore), SCORENAMES.get(playerTwoScore));
    }

//    GETTERS AND SETTERS

    public int getPlayerOneScore() {
        return playerOneScore;
    }

    public void setPlayerOneScore(int playerOneScore) {
        this.playerOneScore = playerOneScore;
    }

    public int getPlayerTwoScore() {
        return playerTwoScore;
    }

    public void setPlayerTwoScore(int playerTwoScore) {
        this.playerTwoScore = playerTwoScore;
    }
}
