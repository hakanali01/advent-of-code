import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        DayOne dayOne = new DayOne();
        dayOne.readInputAndDisplayResult();

        DayTwo dayTwo = new DayTwo();
        dayTwo.runNumbers();

        DayThree dayThree = new DayThree();
        dayThree.theseElvesITellYouFfs();

        DayFour dayFour = new DayFour();
        dayFour.overlapsMoreLikeUwulaps();

        DayFive iLikeTo = new DayFive();
        iLikeTo.moveItMoveIt();

        DaySix daySix = new DaySix();
        daySix.siftingThroughMessage(4);
        daySix.siftingThroughMessage(14);
    }
}
