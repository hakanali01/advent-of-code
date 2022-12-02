import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class DayOne {
    public void readInputAndDisplayResult() throws FileNotFoundException {
        Logger logger = Logger.getLogger("Main");
        File input = new File("/home/hakan/IdeaProjects/advent-of-code/src/resources/day1_input.txt");
        Scanner reader = new Scanner(input);
        List<Integer> calories = new ArrayList<>();

        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            if (!line.trim().isEmpty()) {
                calories.add(Integer.parseInt(line));
            }
            else {
                calories.add(0);
            }
        }
        Map<String, Integer> map = new HashMap<>();
        calculate(calories, map, 0, 0);
//        map.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEach(entry -> logger.info(entry.getKey() + " " + entry.getValue()));
        List<Integer> values = map
                .values()
                .stream()
                .sorted(Collections.reverseOrder()).collect(Collectors.toList());

        logger.info("Day one part 1: " + values.get(0));

        int sum = 0;
        for (int i = 0; i < 3; i++) {
            sum += values.get(i);
        }

        logger.info("Day one part 2: " + sum);
    }

    private void calculate(List<Integer> cal, Map<String, Integer> map, int sum, int i) {
        if (cal.size() > 0) {
            if (cal.get(0) != 0) {
                sum += cal.get(0);
                cal.remove(0);
                calculate(cal, map, sum, i);
            } else {
                map.put("Elf-" + i, sum);
                i++;
                cal.remove(0);
                calculate(cal, map, 0, i);
            }
        }
    }
}
