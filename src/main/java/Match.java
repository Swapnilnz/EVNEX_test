public class Match {

    private final String playerOne;
    private final String playerTwo;
    private Game currentGame;
    private int playerOneSetScore;
    private int playerTwoSetScore;
    private String winner;


    private void prettyPrintScore(String setScore, String gameScore) {
        String outputString = setScore;
        if (gameScore.length() > 0) {
            outputString += String.format(", %s", gameScore);
        }
        System.out.println(outputString);
    }


    public void score() {
        String setScore = String.format("%d-%d", playerOneSetScore, playerTwoSetScore);
        String gameScore = currentGame.getScore();
        prettyPrintScore(setScore, gameScore);
    }


    public void pointWonBy(String playerName) {
        boolean finished = checkFinished();

        if (!finished) {
            currentGame.increaseScore(playerName);
            checkGameOver();
        }
    }

    private void checkGameOver() {
        String gameWinner = currentGame.gameWinner();
        if (gameWinner != null) {
            if (gameWinner.equals(playerOne)) {
                playerOneSetScore += 1;
            }
            if (gameWinner.equals(playerTwo)) {
                playerTwoSetScore += 1;
            }

            if (currentGame.gameType == GameType.TIEBREAK) {
                winner = (playerOneSetScore > playerTwoSetScore) ? playerOne : playerTwo;
            }

            if (playerOneSetScore == 6 && playerOneSetScore == playerTwoSetScore) {
                currentGame = new Game(playerOne, playerTwo, GameType.TIEBREAK);
            } else {
                currentGame = new Game(playerOne, playerTwo, GameType.NORMAL);
            }
        }
    }

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

    private boolean checkWinner(int scoreOne, int scoreTwo) {
        return (scoreOne == 7 || (scoreOne >= 6 && scoreOne - scoreTwo >= 2));
    }

    public Match(String playerOne, String playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        playerOneSetScore = 0;
        playerTwoSetScore = 0;
        currentGame = new Game(playerOne, playerTwo, GameType.NORMAL);
        winner = null;
    }

//    GETTERS AND SETTERS


    public Game getCurrentGame() {
        return currentGame;
    }

    public void setCurrentGame(Game currentGame) {
        this.currentGame = currentGame;
    }

    public int getPlayerOneSetScore() {
        return playerOneSetScore;
    }

    public void setPlayerOneSetScore(int playerOneSetScore) {
        this.playerOneSetScore = playerOneSetScore;
    }

    public int getPlayerTwoSetScore() {
        return playerTwoSetScore;
    }

    public void setPlayerTwoSetScore(int playerTwoSetScore) {
        this.playerTwoSetScore = playerTwoSetScore;
    }
}
