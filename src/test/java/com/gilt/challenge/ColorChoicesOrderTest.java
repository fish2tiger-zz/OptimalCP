package com.gilt.challenge;

import com.gilt.challenge.model.ColorChoiceOrder;
import com.gilt.challenge.util.ColorChoicesReaderUtil;
import org.junit.jupiter.api.Test;

import java.nio.file.FileSystems;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for ColorChoiceOrder's most cost efficient paint plan.
 */
public class ColorChoicesOrderTest {
    private String FILE_PATH_SEPARATOR = FileSystems.getDefault().getSeparator();
    private String TEST_RESOURCE_PATH = "src" + FILE_PATH_SEPARATOR + "test" + FILE_PATH_SEPARATOR + "resources" + FILE_PATH_SEPARATOR;

    private String one_color_test = TEST_RESOURCE_PATH + "1_color_test.txt";
    private String two_color_test = TEST_RESOURCE_PATH + "2_color_test.txt";
    private String five_color_test = TEST_RESOURCE_PATH + "5_color_test.txt";
    private String five_color_rich_test = TEST_RESOURCE_PATH + "5_color_rich_test.txt";
    private String three_color_test = TEST_RESOURCE_PATH + "3_color_test.txt";

    private String one_color_no_solution_test = TEST_RESOURCE_PATH + "1_color_no_solution.txt";
    private String two_color_no_solution_test = TEST_RESOURCE_PATH + "2_color_no_solution.txt";
    private String three_color_no_solution_test = TEST_RESOURCE_PATH + "3_color_no_solution.txt";
    private String five_color_no_solution_test = TEST_RESOURCE_PATH + "5_color_no_solution_test.txt";

    /**
     * 1
     * 1 G
     * <p>
     * G
     */
    @Test
    public void test_one_color_test() {
        ColorChoiceOrder colorlist = ColorChoicesReaderUtil.readColorOrderFromFile(one_color_test);
        String paint = colorlist.getPaintPlan();
        assertEquals(paint, "G");
    }

    /**
     * 1
     * 1 G
     * 1 M
     * <p>
     * No solution exists
     */
    @Test
    public void test_one_color_no_plan_exist_test() {
        ColorChoiceOrder colorlist = ColorChoicesReaderUtil.readColorOrderFromFile(one_color_no_solution_test);
        assertEquals(colorlist.getPaintPlan(), "No solution exists");
    }


    /**
     * Two color valid check
     * 2
     * 1 G 2 M
     * 1 M
     * <p>
     * M M
     */
    @Test
    public void test_two_color_testReadColorChoices() {
        ColorChoiceOrder colorlist = ColorChoicesReaderUtil.readColorOrderFromFile(two_color_test);
        String paint = colorlist.getPaintPlan();
        assertEquals(paint, "M M");
    }

    /**
     * 2
     * 1 G 2 G
     * 1 M
     * 2 M
     * <p>
     * No solution exists
     */
    @Test
    public void test_two_color_no_plan_exist_test() {
        ColorChoiceOrder colorlist = ColorChoicesReaderUtil.readColorOrderFromFile(two_color_no_solution_test);
        assertEquals(colorlist.getPaintPlan(), "No solution exists");
    }

    /**
     * 3
     * 1 G
     * 2 M 3 G
     * 2 G 3 M
     * 2 M
     * 3 M
     * <p>
     * G M M
     */
    @Test
    public void test_three_color_test_getColorPlans() {
        ColorChoiceOrder colorlist = ColorChoicesReaderUtil.readColorOrderFromFile(three_color_test);
        assertEquals(colorlist.getPaintPlan(), "G M M");
    }

    /**
     * 3
     * 1 G
     * 2 M 3 G
     * 2 G 3 M
     * 2 M
     * 3 M
     * <p>
     * G M M
     */
    @Test
    public void test_three_color_no_solution_test() {
        ColorChoiceOrder colorlist = ColorChoicesReaderUtil.readColorOrderFromFile(three_color_no_solution_test);
        assertEquals(colorlist.getPaintPlan(), "No solution exists");
    }

    /**
     * 5
     * 1 M 3 G 5 G
     * 3 M 4 G
     * 5 M
     * <p>
     * G G G G M
     */
    @Test
    public void test_five_color_test_getColorPlans() {
        ColorChoiceOrder colorlist = ColorChoicesReaderUtil.readColorOrderFromFile(five_color_test);
        assertEquals(colorlist.getPaintPlan(), "G G G G M");
    }

    @Test
    public void test_five_color_no_solution_test() {
        ColorChoiceOrder colorlist = ColorChoicesReaderUtil.readColorOrderFromFile(five_color_no_solution_test);
        assertEquals(colorlist.getPaintPlan(), "No solution exists");
    }


    /**
     * 5
     * 2 M
     * 5 G
     * 1 G
     * 5 G 1 G 4 M
     * 3 G
     * 5 G
     * 3 G 5 G 1 G
     * 3 G
     * 2 M
     * 5 G 1 G
     * 2 M
     * 5 G
     * 4 M
     * 5 G 4 M
     * <p>
     * G M G M G
     */
    @Test
    public void test_five_color_rich_test_getColorPlans() {
        ColorChoiceOrder colorlist = ColorChoicesReaderUtil.readColorOrderFromFile(five_color_rich_test);
        assertEquals(colorlist.getPaintPlan(), "G M G M G");
    }
}
