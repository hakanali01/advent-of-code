import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class DayTwo {

    private static int calculate(List<Character> noU, List<Character> opponent) {
        Logger logger = Logger.getLogger("Main");
        List<Character> rpsOpponent = List.of(new Character[] {'A', 'B', 'C'});
        List<Character> rpsU = List.of(new Character[] {'X', 'Y', 'Z'});
        int result = 0;
        for (int i = 0; i < Math.min(noU.size(), opponent.size()); i++) {
            int indexOfU = rpsU.indexOf(noU.get(i)) + 1;
            int indexOfOpponent = rpsOpponent.indexOf(opponent.get(i)) + 1;
            int diff = indexOfOpponent - indexOfU;

            if (diff == -1 || diff == 2) {
                result += indexOfU + 6;
//                logger.info("combination: " + opponent.get(i) + noU.get(i) + " / value: " + Integer.sum(indexOfU, 6) + " / result: " + result);
            }
            else if (diff == 0) {
                result += indexOfU + 3;
//                logger.info("combination: " + opponent.get(i) + noU.get(i) + " / value: " + Integer.sum(indexOfU, 3) + " / result: " + result);
            }
            else {
                result += indexOfU;
//                logger.info("combination: " + opponent.get(i) + noU.get(i) + " / value: " + indexOfU + " / result: " + result);
            }
        }
        return result;
    }

    private static void fillListsWithGenericInput(List<Character> me, List<Character> opponent) {
        opponent.add('A');
        me.add('X');
        opponent.add('A');
        me.add('Y');
        opponent.add('A');
        me.add('Z');
        opponent.add('B');
        me.add('X');
        opponent.add('B');
        me.add('Y');
        opponent.add('B');
        me.add('Z');
        opponent.add('C');
        me.add('X');
        opponent.add('C');
        me.add('Y');
        opponent.add('C');
        me.add('Z');
    }

    private static void fillListsFromFile(List<Character> me, List<Character> opponent) throws FileNotFoundException {
        File input = new File("/home/hakan/IdeaProjects/advent-of-code/src/day2_input.txt");
        Scanner reader = new Scanner(input);
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            me.add(line.charAt(2));
            opponent.add(line.charAt(0));
        }
    }

    private static void fillListsFromFilePartTwo(List<Character> me, List<Character> opponent) throws FileNotFoundException {
        File input = new File("/home/hakan/IdeaProjects/advent-of-code/src/day2_input.txt");
        Scanner reader = new Scanner(input);
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            char ctrl = line.charAt(2);
            Character opp = line.charAt(0);
            switch (opp) {
                case 'A': {
                    switch (ctrl) {
                        case 'X':
                            me.add('Z');
                            break;
                        case 'Y':
                            me.add('X');
                            break;
                        case 'Z':
                            me.add('Y');
                            break;
                    }
                }
                break;
                case 'B': {
                    me.add(ctrl);
                }
                break;
                case 'C': {
                    switch (ctrl) {
                        case 'X':
                            me.add('Y');
                            break;
                        case 'Y':
                            me.add('Z');
                            break;
                        case 'Z':
                            me.add('X');
                            break;
                    }
                }
            }
            opponent.add(opp);
        }
    }

    public void runNumbers() throws FileNotFoundException {
        Logger logger = Logger.getLogger("Main");
        List<Character> me = new ArrayList<>();
        List<Character> opponent = new ArrayList<>();

        fillListsFromFile(me, opponent);
        int result = calculate(me, opponent);
        logger.info("result part one: " + result);

        me.clear();
        opponent.clear();
        fillListsFromFilePartTwo(me, opponent);
        result = calculate(me, opponent);
        logger.info("result part two: " + result);
    }
}
