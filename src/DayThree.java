import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.logging.Logger;

public class DayThree {

    private void part1() throws FileNotFoundException {
        Logger logger = Logger.getLogger("DayThree");
        Map<Character, Integer> lookup = fillLookup();
        File input = new File(System.getProperty("user.dir") + "/src/resources/day3_input.txt");
        Scanner reader = new Scanner(input);
        List<Map<String, Character>> mapList = new ArrayList<>();

        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            String part1 = line.substring(0, line.length() / 2);
            String part2 = line.substring(line.length() / 2);
            for (char c : part1.toCharArray()) {
                if (StringUtils.countMatches(part1, c) > 0 && StringUtils.countMatches(part2, c) > 0) {
                    Map<String, Character> map = new TreeMap<>();
                    map.put(line, c);
                    if (!mapList.contains(map)) {
                        mapList.add(map);
                    }
                }
            }
        }

        int sum = 0;
        for (Map<String, Character> map : mapList) {
            sum += lookup.get(map.values().stream().findFirst().get());
        }
        logger.info("Sum: " + sum);
    }

    private static  Map<Character, Integer> fillLookup() {
        Map<Character, Integer> characters = new TreeMap<>();
        for (int i = 97; i < 123; i++) {
            characters.put((char) i, i - 96);
        }
        for (int i = 65; i < 91; i++) {
            characters.put((char) i, i - 38);
        }
        return characters;
    }

    public static void main(String[] args) throws FileNotFoundException {
        DayThree dayThree = new DayThree();
        dayThree.part1();
    }
}
