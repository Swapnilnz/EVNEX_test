public class Match {

    private final String playerOne;
    private final String playerTwo;
    private Game currentGame;
    private int playerOneSetScore;
    private int playerTwoSetScore;


    /**
     * Concatenate and print the set score and current game score
     * @param setScore formatted string of the current set score, e.g. "1-1"
     * @param gameScore formatted string of the current game score, e.g. "15-15", or "1-1"
     */
    private void prettyPrintScore(String setScore, String gameScore) {
        String outputString = setScore;
        if (gameScore.length() > 0) {
            outputString += String.format(", %s", gameScore);
        }
        System.out.println(outputString);
    }


    /**
     * Print the current set and game score
     */
    public void score() {
        String setScore = String.format("%d-%d", playerOneSetScore, playerTwoSetScore);
        String gameScore = currentGame.getScore();
        prettyPrintScore(setScore, gameScore);
    }


    /**
     * If game is not finished, increment a player's score
     * @param playerName player whose score to increment
     */
    public void pointWonBy(String playerName) {
        boolean finished = checkFinished();

        if (!finished) {
            currentGame.increaseScore(playerName);
            checkGameOver();
        }
    }

    /**
     * Check if the current game is over (there is a winner).
     * If there is a winner, increment their sets score and restart the game.
     */
    private void checkGameOver() {
        String gameWinner = currentGame.gameWinner();
        if (gameWinner != null) {
            if (gameWinner.equals(playerOne)) {
                playerOneSetScore += 1;
            }
            if (gameWinner.equals(playerTwo)) {
                playerTwoSetScore += 1;
            }

            if (playerOneSetScore == 6 && playerOneSetScore == playerTwoSetScore) {
                currentGame = new Game(playerOne, playerTwo, GameType.TIEBREAK);
            } else {
                currentGame = new Game(playerOne, playerTwo, GameType.NORMAL);
            }
        }
    }

    /**
     * Check if the match is finished by checking if either is a winner.
     * @return true if the match is finished
     */
    private boolean checkFinished() {

        if (checkWinner(playerOneSetScore, playerTwoSetScore)) {
            System.out.println("Set has already been won by " + playerOne);
            return true;
        }
        if (checkWinner(playerTwoSetScore, playerOneSetScore)) {
            System.out.println("Set has already been won by " + playerTwo);
            return true;
        }
        return false;
    }

    /**
     * Check if scoreOne is a winning score
     * @param scoreOne score to check if it is winning
     * @param scoreTwo score to check against
     * @return true if scoreOne is seven (meaning tiebreaker was won), or has won at least 6 games and by a margin of 2
     */
    private boolean checkWinner(int scoreOne, int scoreTwo) {
        return (scoreOne == 7 || (scoreOne >= 6 && scoreOne - scoreTwo >= 2));
    }

    public Match(String playerOne, String playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        playerOneSetScore = 0;
        playerTwoSetScore = 0;
        currentGame = new Game(playerOne, playerTwo, GameType.NORMAL);
    }
}
