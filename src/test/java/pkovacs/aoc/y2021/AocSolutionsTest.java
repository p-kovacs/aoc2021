package pkovacs.aoc.y2021;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.function.Consumer;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class AocSolutionsTest {

    private final PrintStream origOut = System.out;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    private static Stream<Arguments> test() {
        return Stream.of(
                new Arguments("Day01", Day01::main, "1301", "1346"),
                new Arguments("Day02", Day02::main, "1693300", "1857958050"),
                new Arguments("Day03", Day03::main, "2003336", "1877139"),
                new Arguments("Day04", Day04::main, "16674", "7075"),
                new Arguments("Day05", Day05::main, "5167", "17604"),
                new Arguments("Day06", Day06::main, "387413", "1738377086345"),
                new Arguments("Day07", Day07::main, "325528", "85015836"),
                new Arguments("Day08", Day08::main, "362", "1020159"),
                new Arguments("Day09", Day09::main, "526", "1123524"),
                new Arguments("Day10", Day10::main, "167379", "2776842859"),
                new Arguments("Day11", Day11::main, "1599", "418"),
                new Arguments("Day12", Day12::main, "3292", "89592"),
                new Arguments("Day13", Day13::main, "790", "96"),
                new Arguments("Day14", Day14::main, "2112", "3243771149914"),
                new Arguments("Day15", Day15::main, "393", "2823"),
                new Arguments("Day16", Day16::main, "981", "299227024091"),
                new Arguments("Day17", Day17::main, "10878", "4716"),
                new Arguments("Day18", Day18::main, "3935", "4669"),
                new Arguments("Day19", Day19::main, "303", "9621"),
                new Arguments("Day20", Day20::main, "5489", "19066"),
                new Arguments("Day21", Day21::main, "752247", "221109915584112"),
                new Arguments("Day22", Day22::main, "607573", "1267133912086024"),
                new Arguments("Day23", Day23::main, "18170", "50208"),
                new Arguments("Day24", Day24::main, "92915979999498", "21611513911181"),
                new Arguments("Day25", Day25::main, "321", "0")
        );
    }

    @ParameterizedTest
    @MethodSource
    public void test(Arguments args) {
        args.mainMethod().accept(null);
        assertSolution1(args.expected1());
        assertSolution2(args.expected2());
    }

    @BeforeEach
    public void changeSystemOut() {
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    public void restoreSystemOut() {
        System.setOut(origOut);
    }

    void assertSolution1(String expected) {
        assertSolution(0, expected);
    }

    void assertSolution2(String expected) {
        assertSolution(1, expected);
    }

    private void assertSolution(int index, String expected) {
        var output = outputStream.toString(StandardCharsets.UTF_8);
        var parts = output.split(System.lineSeparator())[index].split(": ");
        var value = parts.length < 2 ? "" : parts[1].trim();
        Assertions.assertEquals(expected, value);
    }

    private static record Arguments(String name, Consumer<String[]> mainMethod, String expected1, String expected2) {
        @Override
        public String toString() {
            return name;
        }
    }

}
