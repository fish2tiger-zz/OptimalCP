package com.gilt.challenge;

import com.gilt.challenge.model.ColorChoiceOrder;
import com.gilt.challenge.util.ColorChoicesReaderUtil;
import org.junit.jupiter.api.Test;

import java.nio.file.FileSystems;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test the reader utility class.
 */
public class ColorChoicesReaderUtilTest {

    private String FILE_PATH_SEPARATOR = FileSystems.getDefault().getSeparator();
    private String TEST_RESOURCE_PATH = "src" + FILE_PATH_SEPARATOR + "test" + FILE_PATH_SEPARATOR + "resources" + FILE_PATH_SEPARATOR;
    private String TEST_RESOURCE_READER_PATH = TEST_RESOURCE_PATH + "reader" + FILE_PATH_SEPARATOR;

    private String NO_EXIST_FILE_PATH = "jlakdfjkldasjfljdasfjsafjalkfjlksaflajflasjf/dfasjlskajfl;sadjflajfd";
    private String EMPTY_FILE_PATH = TEST_RESOURCE_READER_PATH + "empty_file.txt";
    private String EMPTY_LINES_FILE_PATH = TEST_RESOURCE_READER_PATH + "empty_lines_file.txt";
    private String RANDOM_CONTENT_FILE_PATH = TEST_RESOURCE_READER_PATH + "file_with_random_content.txt";


    // Valid color input
    private String five_color_test = TEST_RESOURCE_PATH + "5_color_test.txt";
    private String one_color_lowercase = TEST_RESOURCE_READER_PATH + "1_color_lower_case_paint_option.txt";

    // Invalid color input
    private String one_color_missing_color_num_test = TEST_RESOURCE_READER_PATH + "1_color_missing_color_num_input.txt";

    private String malformed_input_invalid_color_num = TEST_RESOURCE_READER_PATH + "malformed_input_invalid_color_num.txt";
    private String malformed_input_invalid_color_number = TEST_RESOURCE_READER_PATH + "malformed_input_invalid_color_number.txt";
    private String malformed_input_missing_paint_option = TEST_RESOURCE_READER_PATH + "malformed_input_missing_paint_option.txt";
    private String malformed_input_invalid_paint_option = TEST_RESOURCE_READER_PATH + "malformed_input_invalid_paint_option.txt";

    private String malformed_input_multiple_paint_for_one_color = TEST_RESOURCE_READER_PATH + "malformed_input_multiple_paint_for_one_color.txt";

    @Test
    public void test_read_lines_from_no_exist_file() {
        List<String> lines = ColorChoicesReaderUtil.readLinesFromFile(null);
        assertEquals(lines.size(), 0);
        lines = ColorChoicesReaderUtil.readLinesFromFile(NO_EXIST_FILE_PATH);
        assertEquals(lines.size(), 0);
    }

    @Test
    public void test_read_lines_from_empty_file() {
        List<String> lines = ColorChoicesReaderUtil.readLinesFromFile(EMPTY_FILE_PATH);
        assertEquals(lines.size(), 0);
    }

    @Test
    public void test_read_lines_from_empty_lines_file() {
        List<String> lines = ColorChoicesReaderUtil.readLinesFromFile(EMPTY_LINES_FILE_PATH);
        assertEquals(lines.size(), 0);
    }

    @Test
    public void test_read_lines_from_random_file() {
        List<String> lines = ColorChoicesReaderUtil.readLinesFromFile(RANDOM_CONTENT_FILE_PATH);
        assertEquals(lines.size(), 3);
    }

    @Test
    public void test_read_colororder_from_correct_input() {
        ColorChoiceOrder choiceOrder = ColorChoicesReaderUtil.readColorOrderFromFile(five_color_test);
        assertTrue(choiceOrder != null);
        assertEquals(choiceOrder.toString(),
                "1 M 3 G 5 G\n" +
                        "3 M 4 G \n" +
                        "5 M");
    }

    @Test
    public void test_read_colororder_from_lowercast_paintoption_input() {
        ColorChoiceOrder choiceOrder = ColorChoicesReaderUtil.readColorOrderFromFile(one_color_lowercase);
        assertTrue(choiceOrder == null);
    }

    @Test
    public void test_read_colororder_from_empty_file() {
        ColorChoiceOrder choiceOrder = ColorChoicesReaderUtil.readColorOrderFromFile(EMPTY_FILE_PATH);
        assertTrue(choiceOrder == null);
    }

    @Test
    public void test_read_colororder_from_missing_color_num_input() {
        ColorChoiceOrder choiceOrder = ColorChoicesReaderUtil.readColorOrderFromFile(one_color_missing_color_num_test);
        assertTrue(choiceOrder == null);
    }

    @Test
    public void test_malformed_input_multiple_paint_for_one_color() {
        ColorChoiceOrder choiceOrder = ColorChoicesReaderUtil.readColorOrderFromFile(malformed_input_multiple_paint_for_one_color);
        assertTrue(choiceOrder == null);
    }

    @Test
    public void test_read_coloroder_from_malformed_input_missing_paint_option() {
        ColorChoiceOrder choiceOrder = ColorChoicesReaderUtil.readColorOrderFromFile(malformed_input_missing_paint_option);
        assertTrue(choiceOrder == null);
    }

    @Test
    public void test_read_coloroder_from_malformed_input_invalid_color_number() {
        ColorChoiceOrder choiceOrder = ColorChoicesReaderUtil.readColorOrderFromFile(malformed_input_invalid_color_number);
        assertTrue(choiceOrder == null);
    }

    @Test
    public void test_malformed_input_invalid_paint_option() {
        ColorChoiceOrder choiceOrder = ColorChoicesReaderUtil.readColorOrderFromFile(malformed_input_invalid_paint_option);
        assertTrue(choiceOrder == null);
    }

    @Test
    public void test_malformed_input_invalid_color_num() {
        ColorChoiceOrder choiceOrder = ColorChoicesReaderUtil.readColorOrderFromFile(malformed_input_invalid_color_num);
        assertTrue(choiceOrder == null);

    }
}
