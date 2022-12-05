import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        DayOne dayOne = new DayOne();
        dayOne.readInputAndDisplayResult();

        DayTwo dayTwo = new DayTwo();
        dayTwo.runNumbers();

        DayThree dayThree = new DayThree();
        dayThree.theseElvesITellYouFfs();

        DayFour dayFour = new DayFour();
        dayFour.overlapsMoreLikeUwulaps();
    }
}
