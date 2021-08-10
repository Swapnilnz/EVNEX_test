import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NormalGame extends Game {

    private static final ArrayList<String> SCORENAMES = new ArrayList<>(List.of("0", "15", "30", "40"));
    private String playerOne;
    private String playerTwo;
    private int playerOneScore;
    private int playerTwoScore;

    public NormalGame(String playerOne, String playerTwo) {
        super(playerOne, playerTwo);
    }

    @Override
    public String gameWinner() {
        if (playerOneScore >= 4 && playerOneScore - playerTwoScore >= 2) {
            return playerOne;
        }
        if (playerTwoScore >= 4 && playerTwoScore - playerOneScore >= 2) {
            return playerTwo;
        }
        return null;
    }

    @Override
    public String getScore() {
        String output = "";
        if (playerOneScore >= 3 && playerTwoScore >= 3) {
            if (playerOneScore > playerTwoScore) {
                output = "Advantage " + playerOne;
            } else if (playerTwoScore > playerOneScore) {
                output = "Advantage " + playerTwo;
            } else {
                output = "Deuce";
            }
        }
        return output;
    }
}
