package com.gilt.challenge.util;

import com.gilt.challenge.model.ColorChoice;
import com.gilt.challenge.model.ColorChoiceException;
import com.gilt.challenge.model.ColorChoiceOrder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.gilt.challenge.model.PaintOptionEnm.paintOption;

/**
 * Color Reader utility class.
 */
public class ColorChoicesReaderUtil {

    public static ColorChoiceOrder readColorOrderFromFile(String filePath) {
        List<ColorChoice> choicesList = new ArrayList<>();
        ColorChoiceOrder order = null;
        try {
            List<String> lines = readLinesFromFile(filePath);
            if (lines.size() == 0)
                throw new Exception("Input file is empty. ");
            // parse the total color number
            Integer csize = Integer.parseInt(lines.get(0).trim());
            if (csize < 1) {
                throw new ColorChoiceException("Color size input should be over 1. ");
            }

            for (int i = 1; i < lines.size(); i++) {
                String line = lines.get(i);
                String[] choices = new String[csize];
                Arrays.fill(choices, null);
                String[] st_choices = line.split("\\s+");

                if (st_choices.length == 0 || st_choices.length % 2 != 0)
                    throw new ColorChoiceException("color choice input: " + line + " isn't valid");
                for (int start = 0; start < st_choices.length - 1; start += 2) {
                    String index = st_choices[start];
                    String paint = st_choices[start + 1];

                    Integer indexInteger = Integer.parseInt(index);
                    if (indexInteger < 1 || indexInteger > csize)
                        throw new ColorChoiceException("color choice input: " + line + " in color index " + indexInteger + " is not valid");
                    if (!paintOption.contains(paint)) {
                        throw new ColorChoiceException("color choice input: " + line + ". Paint option: " + paint + " is not valid. ");
                    }
                    if (choices[indexInteger - 1] != null && !choices[indexInteger - 1].equals(paint))
                        throw new ColorChoiceException("Only one paint option is allowed for one color: " + line);
                    choices[indexInteger - 1] = paint;
                }

                choicesList.add(new ColorChoice(choices));
            }

            order = new ColorChoiceOrder(csize, choicesList);
        } catch (NumberFormatException e) {
            System.err.println("Unable to parse color number, as it should be integer: " + e.getMessage());
        } catch (ColorChoiceException e) {
            System.err.println("The color choice from input isn't valid: \n" + e.getMessage());
        } catch (Exception e) {
            System.err.println("Exception occurred: " + e.getMessage());
        }

        return order;

    }

    /**
     * Read each line from a file by its give path into a list of string
     *
     * @param filePath
     * @return list of lines exist in the file
     */
    public static List<String> readLinesFromFile(String filePath) {
        List<String> lines = new ArrayList<>();
        if (filePath == null) {
            System.err.println("File path is null! Make sure you provide a file path. ");
            return lines;
        }
        File file = new File(filePath);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String str;
            while ((str = br.readLine()) != null && !str.trim().isEmpty()) {
                lines.add(str);
            }
            return lines;
        } catch (Exception e) {
            System.err.println("Exception occurred during reading the file: " + filePath + ", exception: " + e);
        }
        return lines;
    }


}
