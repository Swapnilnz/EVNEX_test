public class TieBreakGame extends Game {

    private String playerOne;
    private String playerTwo;
    private int playerOneScore;
    private int playerTwoScore;

    public TieBreakGame(String playerOne, String playerTwo) {
        super(playerOne, playerTwo);
    }


    @Override
    public String gameWinner() {
        return null;
    }

    @Override
    public String getScore() {
        return null;
    }
}
