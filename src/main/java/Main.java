public class Main {
    public static void main(String[] args) {
        Match match = new Match("player 1", "player 2");
//        match.pointWonBy("player 1");
//        match.pointWonBy("player 2");
//        // this will return "0-0, 15-15"
//        match.score();
//
//        match.pointWonBy("player 1");
//        match.pointWonBy("player 1");
//        // this will return "0-0, 40-15"
//        match.score();
//
//        match.pointWonBy("player 2");
//        match.pointWonBy("player 2");
//        // this will return "0-0, Deuce"
//        match.score();
//
//        match.pointWonBy("player 1");
//        // this will return "0-0, Advantage player 1"
//        match.score();
//
//        match.pointWonBy("player 1");
//        // this will return "1-0"
//        match.score();
        for (int i = 0; i < 20; i ++) {
            match.pointWonBy("player 1");
        }

        for (int i = 0; i < 24; i ++) {
            match.pointWonBy("player 2");
        }

        for (int i = 0; i < 3; i ++) {
            match.pointWonBy("player 1");
        }



        match.score();
    }
}
