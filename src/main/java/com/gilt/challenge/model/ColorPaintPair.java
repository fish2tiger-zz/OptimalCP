package com.gilt.challenge.model;

/**
 * ColorPaintPair represents the one color paint preference.
 */
public class ColorPaintPair {
    public int getIndex() {
        return index;
    }

    public PaintOptionEnm getPaint() {
        return paint;
    }

    private int index;
    private PaintOptionEnm paint;

    public ColorPaintPair(int index, PaintOptionEnm paint) {
        this.index = index;
        this.paint = paint;
    }
}
