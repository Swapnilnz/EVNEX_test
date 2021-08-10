import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MatchTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private String player1 = "player 1";
    private String player2 = "player 2";
    private Match match;


    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
        match = new Match(player1, player2);
    }

    @Test
    void testInitialScore() {
        match.score();
        Assertions.assertEquals("0-0", outputStreamCaptor.toString().trim());
    }

    @Test
    void testIncreasingScore() {
        match.pointWonBy(player1);
        match.score();
        Assertions.assertEquals("0-0, 15-0", outputStreamCaptor.toString().trim());
        outputStreamCaptor.reset();
        match.pointWonBy(player1);
        match.score();
        Assertions.assertEquals("0-0, 30-0", outputStreamCaptor.toString().trim());
        outputStreamCaptor.reset();
        match.pointWonBy(player1);
        match.score();
        Assertions.assertEquals("0-0, 40-0", outputStreamCaptor.toString().trim());
        outputStreamCaptor.reset();
    }

    @Test
    void testDeuce() {
        for (int i = 0; i < 3; i++) {
            match.pointWonBy(player1);
            match.pointWonBy(player2);
        }
        match.score();
        Assertions.assertEquals("0-0, Deuce", outputStreamCaptor.toString().trim());
        outputStreamCaptor.reset();
        match.pointWonBy(player1);
        match.score();
        Assertions.assertEquals(String.format("0-0, Advantage %s", player1), outputStreamCaptor.toString().trim());
    }

    @Test
    void testIncreasingSetScore() {
        // Starting at i = 1 makes modulo arithmetic easier
        for (int i = 1; i <= 24; i++) {
            match.pointWonBy(player2);
            if (i % 4 == 0) {
                match.score();
                Assertions.assertEquals(String.format("0-%s", i / 4), outputStreamCaptor.toString().trim());
                outputStreamCaptor.reset();
            }
        }
    }

    @Test
    void testNormalWin() {
        for (String player : Arrays.asList(player1, player2)) {
            for (int i = 0; i < 25; i++) {
                match.pointWonBy(player);
            }
            Assertions.assertEquals("Set has already been won by " + player, outputStreamCaptor.toString().trim());
            outputStreamCaptor.reset();
            match = new Match(player1, player2);
        }
    }

    @Test
    void testTieBreakerInvoked() {
        getScoreToSixSix();
        match.score();
        Assertions.assertEquals("6-6", outputStreamCaptor.toString().trim());
        outputStreamCaptor.reset();
        match.pointWonBy(player1);
        match.score();
        Assertions.assertEquals("6-6, 1-0", outputStreamCaptor.toString().trim());
    }

    @Test
    void testTieBreakerWin() {
        getScoreToSixSix();
        for (int i = 0; i < 8; i++) {
            match.pointWonBy(player1);
        }
        Assertions.assertEquals("Set has already been won by " + player1, outputStreamCaptor.toString().trim());
    }



    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    void getScoreToSixSix() {
        // Get score to 5-5
        for (String player : Arrays.asList(player1, player2)) {
            for (int i = 0; i < 20; i++) {
                match.pointWonBy(player);
            }
        }
        // Get score to 6-6
        for (String player : Arrays.asList(player1, player2)) {
            for (int i = 0; i < 4;i ++) {
                match.pointWonBy(player);
            }
        }
    }
}
