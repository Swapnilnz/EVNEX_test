public class Match {

    private final String playerOne;
    private final String playerTwo;
    private Game currentGame;
    private int playerOneSetScore;
    private int playerTwoSetScore;


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

        currentGame.increaseScore(playerName);

        String gameWinner = currentGame.gameWinner();
        if (gameWinner.equals(playerOne)) {
            playerOneSetScore += 1;
        }
        if (gameWinner.equals(playerTwo)) {
            playerTwoSetScore += 1;
        }
    }

    public Match(String playerOne, String playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        playerOneSetScore = 0;
        playerTwoSetScore = 0;
        currentGame = new NormalGame(playerOne, playerTwo);
    }


}
