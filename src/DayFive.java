import org.apache.commons.lang3.SerializationUtils;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;
import java.util.logging.Logger;

public class DayFive implements Serializable {

    public void moveItMoveIt() throws IOException{
        long startTime = System.nanoTime();
        Logger logger = Logger.getLogger("DayFive");
        File input = new File(System.getProperty("user.dir") + "/src/resources/day5_input.txt");
        Scanner reader = new Scanner(input);

        List<String> lines = new ArrayList<>();
        int lastLineIndex = 0;
        int index = 0;
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            lines.add(line);
            List<Character> crates = new ArrayList<>();
            for (char c : line.toCharArray()) {
                crates.add(c);
            }
            if (index != 0 && crates.size() > 0 && crates.get(1) == '1') {
                lastLineIndex = index;
            }
            else {
                index++;
            }
        }

        TreeMap<Integer, List<Character>> inventory = new TreeMap<>();
        List<String> lastLine = List.of(lines.get(lastLineIndex).split(" "));
        for (int i = 1; i < lastLine.size(); i = i + 3) {
            inventory.put(Integer.parseInt(lastLine.get(i)), new ArrayList<>());
        }

        for (int i = 0; i < lastLineIndex; i++) {
            String line = lines.get(i);
            if (!line.isBlank()) {
                List<Character> crates = new ArrayList<>();
                for (char c : line.toCharArray()) {
                    crates.add(c);
                }
                for (int j = 1, k = 0; j < crates.size(); j = j + 4, k = k + 3) {
                    if (!crates.get(j).equals(' ') && !crates.get(j).equals('[') && !crates.get(j).equals(']')) {
                        if (j == 1) {
                            inventory.get(j).add(crates.get(j));
                        }
                        else  {
                            inventory.get(j - k).add(crates.get(j));
                        }
                    }
                }
            }
        }

        for (Map.Entry<Integer, List<Character>> entry : inventory.entrySet()) {
            Collections.reverse(entry.getValue());
        }

        Map<Integer, List<Character>> copyOfInventory = SerializationUtils.clone(inventory);

        // part 1
        for (int i = lastLineIndex + 2; i < lines.size(); i++) {
            List<String> line = List.of(lines.get(i).split(" "));
            int amount = Integer.parseInt(line.get(1));
            int fromIndex = Integer.parseInt(line.get(3));
            int toIndex = Integer.parseInt(line.get(5));
            for (int  j = 0; j < amount; j++) {
                inventory.get(toIndex).add(inventory.get(fromIndex).get(inventory.get(fromIndex).size() - 1));
                inventory.get(fromIndex).remove(inventory.get(fromIndex).size() - 1);
            }
        }

        StringBuilder msg = new StringBuilder();
        for (Map.Entry<Integer, List<Character>> entry : inventory.entrySet()) {
            msg.append(entry.getValue().get(entry.getValue().size() - 1));
        }
        logger.info("Day 6, Part 1 - Move crates one by one final result: " + msg);

        // part 2
        for (int i = lastLineIndex + 2; i < lines.size(); i++) {
            List<String> line = List.of(lines.get(i).split(" "));
            int amount = Integer.parseInt(line.get(1));
            int fromIndex = Integer.parseInt(line.get(3));
            int toIndex = Integer.parseInt(line.get(5));
            List<Character> fromList = SerializationUtils.clone(new ArrayList<>(copyOfInventory.get(fromIndex)));
            List<Character> subList = fromList.subList(fromList.size() - amount, fromList.size());
            subList.forEach(s -> {copyOfInventory.get(toIndex).add(s);});
            subList.forEach(s -> {copyOfInventory.get(fromIndex).remove(copyOfInventory.get(fromIndex).size() - 1);});
        }

        msg.setLength(0);
        for (Map.Entry<Integer, List<Character>> entry : copyOfInventory.entrySet()) {
            msg.append(entry.getValue().get(entry.getValue().size() - 1));
        }

        logger.info("Day 6, Part 2 - Move crates in groups final result: " + msg);
        logger.info("\nRuntime: " + (float) (System.nanoTime() - startTime) / 1000000000);
    }

    public static void main(String[] args) throws IOException {
        DayFive dayFive = new DayFive();
        dayFive.moveItMoveIt();
    }
}
