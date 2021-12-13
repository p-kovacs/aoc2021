package pkovacs.aoc.y2021;

import java.util.HashSet;
import java.util.Set;

import pkovacs.aoc.AocUtils;
import pkovacs.util.InputUtils;
import pkovacs.util.data.Point;

import static java.util.stream.Collectors.toSet;

public class Day13 {

    public static void main(String[] args) {
        var input = InputUtils.readString(AocUtils.getInputPath());

        System.out.println("Part 1: " + solve(input, true).size());
        System.out.println("Part 2: " + solve(input, false).size());

        System.out.println();
        printResult(input, false);
    }

    private static Set<Point> solve(String input, boolean stopAfterFirstFold) {
        Set<Point> set = new HashSet<>();

        var blocks = InputUtils.collectLineBlocks(input);

        // Parse original points
        for (var line : blocks.get(0)) {
            var ints = InputUtils.parseInts(line);
            set.add(new Point(ints[0], ints[1]));
        }

        for (var line : blocks.get(1)) {
            if (line.contains("x=")) {
                int x = Integer.parseInt(line.split("=")[1]);
                set = set.stream()
                        .map(p -> new Point(p.x() > x ? x - (p.x() - x) : p.x(), p.y()))
                        .collect(toSet());
            } else {
                int y = Integer.parseInt(line.split("=")[1]);
                set = set.stream()
                        .map(p -> new Point(p.x(), p.y() > y ? y - (p.y() - y) : p.y()))
                        .collect(toSet());
            }
            if (stopAfterFirstFold) {
                break;
            }
        }

        return set;
    }

    private static void printResult(String input, boolean stopAfterFirstFold) {
        var set = solve(input, stopAfterFirstFold);
        int width = set.stream().mapToInt(Point::x).max().orElseThrow() + 1;
        int height = set.stream().mapToInt(Point::y).max().orElseThrow() + 1;

        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                System.out.print(set.contains(new Point(i, j)) ? '#' : ' ');
            }
            System.out.println();
        }
    }

}
