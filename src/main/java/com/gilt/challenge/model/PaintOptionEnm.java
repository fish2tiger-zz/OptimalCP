package com.gilt.challenge.model;

import java.util.HashSet;
import java.util.Set;

/**
 * This class represents the enum of the paint option.
 */
public enum PaintOptionEnm {
    GLOSS("G"),
    MATTE("M");
    public static Set<String> paintOption = new HashSet<>();

    static {
        paintOption.add("G");
        paintOption.add("M");
    }

    String type;

    PaintOptionEnm(String type) {
        this.type = type;
    }

    static boolean isValid(String type) {
        return paintOption.contains(type);
    }
}
