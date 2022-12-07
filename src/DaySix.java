import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class DaySix {
    public void siftingThroughMessage(int part) throws FileNotFoundException {
        long startTime = System.nanoTime();
        Logger logger = Logger.getLogger("DayFive");
        List<Character> in = openFile().chars().mapToObj(e -> (char) e).collect(Collectors.toList());
        int index = 0;
        for (int i = 0; i < in.size() - part; i++) {
            if (in.subList(i, i + part).stream().distinct().count() == part) {
                index = i + part;
                break;
            }
        }
        logger.info("Day 6, Part " + (part == 4 ? "1" : "2") + " - Characters to be processed before first" + part + "unique: " + index);
        logger.info("\nRuntime: " + (float) (System.nanoTime() - startTime) / 1000000000 + "\n");
    }

    private String openFile() throws FileNotFoundException {
        File input = new File(System.getProperty("user.dir") + "/src/resources/day6_input.txt");
        Scanner reader = new Scanner(input);
        String inputString = "";
        while (reader.hasNextLine()) {
            inputString = reader.nextLine();
        }
        return inputString;
    }
}
