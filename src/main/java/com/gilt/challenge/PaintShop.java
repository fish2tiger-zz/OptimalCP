package com.gilt.challenge;

import com.gilt.challenge.model.ColorChoiceOrder;
import com.gilt.challenge.util.ColorChoicesReaderUtil;

/**
 * Main class as the entry for taking in the color choices and
 * compute the optimal paint plan.
 */
public class PaintShop {
    public static void main(String[] args) {
        if (args != null && args.length > 0) {
            String filePath = args[0];
            ColorChoiceOrder order = ColorChoicesReaderUtil.readColorOrderFromFile(filePath);
            if (order == null) {
                System.err.println("Color choice input is invalid. Unable to generate color paint plan. Exit now.");
                return;
            }
            System.out.println(order.getPaintPlan());
        } else {
            System.err.println("File input expected, please give a file path for color paint plan. ");
        }
    }
}
