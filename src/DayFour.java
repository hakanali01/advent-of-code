import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class DayFour {
    public void overlapsMoreLikeUwulaps() throws FileNotFoundException {
        Logger logger = Logger.getLogger("DayFour");
        File input = new File(System.getProperty("user.dir") + "/src/resources/day4_input.txt");
        Scanner reader = new Scanner(input);

        int countFullOverlap = 0;
        int partialOverlap = 0;
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            List<String> pairs = List.of(line.split(","));
            List<String> sections1 = List.of(pairs.get(0).split("-"));
            // firstOfOne-secondOfOne
            int foo = Integer.parseInt(sections1.get(0));
            int soo = Integer.parseInt(sections1.get(1));
            List<String> sections2 = List.of(pairs.get(1).split("-"));
            // firstOfTwo-secondOfTwo
            int fot = Integer.parseInt(sections2.get(0));
            int sot = Integer.parseInt(sections2.get(1));
            if ((foo <= fot && sot <= soo) || (foo >= fot && soo <= sot)) {
                countFullOverlap++;
            }
            boolean partial = (fot >= foo && fot <= soo) || (foo >= fot && foo <= sot) ||
                    (soo >= fot && soo <= sot) || (sot >= foo && sot <= soo);
            if (partial) {
                partialOverlap++;
            }
        }

        logger.info("Day 4 - Part 1, Full Overlap Count: " + countFullOverlap);
        logger.info("Day 4 - Part 2, Partial Overlap Count: " + partialOverlap);
    }
}
