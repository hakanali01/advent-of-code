import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

public class DayFive {

    public void parseCrates() throws IOException {
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

        Map<Integer, List<Character>> inventory = new TreeMap<>();
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

        String logging = "";
        String finalInventory = "";
        for (Map.Entry<Integer, List<Character>> entry : inventory.entrySet()) {
            finalInventory += "\t" + entry.getKey() + ": " + entry.getValue() + "\n";
        }

        String log = "Initial inventory: {\n" + finalInventory + "}\n\n";
        logging += log;

        logger.info("Inventory: " + inventory);

        Map<Integer, List<Character>> copyOfInventory = new TreeMap<>(inventory);

        // part 1
//        for (int i = lastLineIndex + 2; i < lines.size(); i++) {
//            List<String> line = List.of(lines.get(i).split(" "));
//            int amount = Integer.parseInt(line.get(1));
//            int fromIndex = Integer.parseInt(line.get(3));
//            int toIndex = Integer.parseInt(line.get(5));
//            for (int  j = 0; j < amount; j++) {
//                System.out.println(line + " - " + copyOfInventory);
//                copyOfInventory.get(toIndex).add(copyOfInventory.get(fromIndex).get(copyOfInventory.get(fromIndex).size() - 1));
//                copyOfInventory.get(fromIndex).remove(copyOfInventory.get(fromIndex).size() - 1);
//            }
//        }

        // part 2
        int failCount = 0;
        for (int i = lastLineIndex + 2; i < lines.size(); i++) {
            List<String> line = List.of(lines.get(i).split(" "));
            int amount = Integer.parseInt(line.get(1));
            int fromIndex = Integer.parseInt(line.get(3));
            int toIndex = Integer.parseInt(line.get(5));
            List<Character> fromList = new ArrayList<>(copyOfInventory.get(fromIndex));

            log = line + " - FROM " + fromIndex + ": " + copyOfInventory.get(fromIndex) + " || TO " + toIndex + ": " + copyOfInventory.get(toIndex) + " <- BEFORE";
            logging += log + "\n";
            System.out.println(log);
            List<Character> subList = fromList.subList(fromList.size() - amount, fromList.size());
            for (Character c : subList) {
                copyOfInventory.get(toIndex).add(c);
            }
            for (Character c : subList) {
                copyOfInventory.get(fromIndex).remove(c);
            }
            log = line + " - FROM " + fromIndex + ": " + copyOfInventory.get(fromIndex) + " || TO " + toIndex + ": " + copyOfInventory.get(toIndex) + " <- AFTER\n";
            logging += log + "\n";
            System.out.println(log);

            if (amount > fromList.size())  {
                System.out.println(line + " - " + copyOfInventory + " FAILED. TOTAL FAILED TIME: " + failCount + "\n");
                failCount++;
            }
        }

        log = "Failed " + failCount + " times\n";
        logging += log + "\n";
        logger.info(log);
        log = "Final inventory " + copyOfInventory;
        logger.info(log);
        StringBuilder msg = new StringBuilder();
        finalInventory = "";
        for (Map.Entry<Integer, List<Character>> entry : copyOfInventory.entrySet()) {
            finalInventory += "\t" + entry.getKey() + ": " + entry.getValue() + "\n";
            msg.append(entry.getValue().get(entry.getValue().size() - 1));
        }
        log = "Final inventory: {\n" + finalInventory + "}\n";
        logging += log + "\n";
        log = "Final message: " + msg;
        logging += log + "\n";
        logger.info("Final message: " + msg + "\n");
        log = "\nRuntime: " + (float) (System.nanoTime() - startTime) / 1000000000;
        logging += log + "\n";
        FileWriter myWriter = new FileWriter("day5_logs.txt");
        myWriter.write("");
        myWriter.write(logging);
        myWriter.close();
        System.out.println("\nRuntime: " + (float) (System.nanoTime() - startTime)/1000000000);
    }

//    public static void main(String[] args) throws IOException {
//        DayFive dayFive = new DayFive();
//        dayFive.parseCrates();
//    }
}
