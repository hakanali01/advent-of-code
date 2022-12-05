import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.logging.Logger;

public class DayThree {

    public void theseElvesITellYouFfs() throws FileNotFoundException {
        Logger logger = Logger.getLogger("DayThree");
        Map<Character, Integer> lookup = fillLookup();
        File input = new File(System.getProperty("user.dir") + "/src/resources/day3_input.txt");
        Scanner reader = new Scanner(input);

        List<Map<String, Character>> mapList = new ArrayList<>();
        List<String> lines = new ArrayList<>();
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            lines.add(line);
            String part1 = line.substring(0, line.length() / 2);
            String part2 = line.substring(line.length() / 2);
            for (char c : part1.toCharArray()) {
                // thank you apache and commons-lang3
                if (StringUtils.countMatches(part1, c) > 0 && StringUtils.countMatches(part2, c) > 0) {
                    Map<String, Character> map = new TreeMap<>();
                    // this is to avoid duplicates
                    map.put(line, c);
                    if (!mapList.contains(map)) {
                        mapList.add(map);
                    }
                }
            }
        }

        // part 1
        int sum = 0;
        for (Map<String, Character> map : mapList) {
            sum += lookup.get(map.values().stream().findFirst().get());
        }
        logger.info("Day 3 Part 1 - Sum of rucksacks: " + sum);

        // part 2
        int badgeSum = 0;
        // thank you google and guava
        List<List<String>> tmpGroups  = Lists.partition(lines, 3);
        for (List<String> lst : tmpGroups) {
            for (char c : lst.get(0).toCharArray()) {
                if (StringUtils.countMatches(lst.get(1), c) > 0 && StringUtils.countMatches(lst.get(2), c) > 0) {
                    badgeSum += lookup.get(c);
                    break;
                }
            }
        }

        logger.info("Day 3 Part 2 - Sum of badges: " + badgeSum);
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
}
