package pkovacs.aoc.y2021;

import java.util.List;
import java.util.stream.Collectors;

import pkovacs.aoc.AocUtils;
import pkovacs.util.InputUtils;
import pkovacs.util.data.CounterMap;

public class Day14 {

    public static void main(String[] args) {
        var blocks = InputUtils.collectLineBlocks(InputUtils.readString(AocUtils.getInputPath()));
        var input = blocks.get(0).get(0);
        var ruleLines = blocks.get(1);

        System.out.println("Part 1: " + solve(input, ruleLines, 10));
        System.out.println("Part 2: " + solve(input, ruleLines, 40));
    }

    private static long solve(String input, List<String> ruleLines, int stepCount) {
        // Parse rules into a Map (from->to)
        var rules = ruleLines.stream()
                .map(line -> line.split(" -> "))
                .collect(Collectors.toMap(p -> p[0], p -> p[0].charAt(0) + p[1] + p[0].charAt(1)));

        // Collect 2-length slices of the input
        var slices = new CounterMap<String>();
        for (int i = 0; i < input.length() - 1; i++) {
            slices.inc(input.substring(i, i + 2));
        }

        // Apply transformation steps
        for (int i = 0; i < stepCount; i++) {
            var newSlices = new CounterMap<String>();
            for (var slice : slices.keySet()) {
                if (rules.containsKey(slice)) {
                    var to = rules.get(slice);
                    newSlices.add(to.substring(0, 2), slices.get(slice));
                    newSlices.add(to.substring(1, 3), slices.get(slice));
                } else {
                    newSlices.add(slice, slices.get(slice)); // actually unnecessary: there is a rule for each slice
                }
            }
            slices = newSlices;
        }

        // Count characters (twice): every character occurs twice in the slices, except for the very first one and
        // very last one of the original input string
        var counts = new CounterMap<Character>();
        for (var slice : slices.keySet()) {
            counts.add(slice.charAt(0), slices.get(slice));
            counts.add(slice.charAt(1), slices.get(slice));
        }
        counts.inc(input.charAt(0));
        counts.inc(input.charAt(input.length() - 1));

        return (counts.max() - counts.min()) / 2;
    }

}
