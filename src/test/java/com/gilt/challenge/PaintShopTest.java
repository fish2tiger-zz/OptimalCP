package com.gilt.challenge;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test the main class and verify the console output.
 */
public class PaintShopTest {

    @Test
    public void test_paintShop_valid_paint_plan() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        String[] args = new String[]{this.getClass().getClassLoader().getResource("5_color_rich_test.txt").getPath()};
        PaintShop.main(args);
        assertEquals("G M G M G\n", outContent.toString());
    }

    @Test
    public void test_paintShop_no_solution() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));

        String[] args = new String[]{this.getClass().getClassLoader().getResource("5_color_no_solution_test.txt").getPath()};
        PaintShop.main(args);
        assertEquals("No solution exists\n", outContent.toString());
    }

    @Test
    public void test_paintShop_no_input() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));

        String[] args = new String[]{"abcdefghijklmnopqrstuvwxyz/no_exist_file"};
        PaintShop.main(args);

        assertTrue(errContent.toString().contains("Color choice input is invalid. Unable to generate color paint plan. Exit now."));

    }

    @Test
    public void test_paintShop_missing_input() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));

        String[] args = null;
        PaintShop.main(args);
        System.out.println(errContent.toString());
        assertTrue(errContent.toString().contains("File input expected, please give a file path for color paint plan. "));
    }


}
