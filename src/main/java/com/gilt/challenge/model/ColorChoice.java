package com.gilt.challenge.model;

import java.util.*;

/**
 * This class represents one customer's color choices for the paint shop.
 */
public class ColorChoice {
    private String[] colorchoices;

    private Map<Integer, PaintOptionEnm> mattes = new HashMap<>();
    private Map<Integer, PaintOptionEnm> glosses = new HashMap<>();

    public ColorChoice(String[] colorchoices) throws ColorChoiceException {
        this.colorchoices = colorchoices;
        for (int i = 0; i < colorchoices.length; i++) {
            if (colorchoices[i] != null) {
                // raise exception if the paint option is invalid
                String paintOption = colorchoices[i].toUpperCase();
                if (!PaintOptionEnm.paintOption.contains(paintOption))
                    throw new ColorChoiceException("Paint type :" + paintOption + " isn't valid. " +
                            "It should be either " + PaintOptionEnm.MATTE + " or " + PaintOptionEnm.GLOSS + " : " + Arrays.toString(colorchoices));

                // raise exception when there are >=2 paint types for one color
                if (glosses.containsKey(i) && !PaintOptionEnm.GLOSS.type.equals(colorchoices[i])) {
                    throw new ColorChoiceException("Color choice has conflicts in color:  " + i + 1 + " : " + Arrays.toString(colorchoices));
                }
                if (mattes.containsKey(i) && !PaintOptionEnm.MATTE.type.equals(colorchoices[i])) {
                    throw new ColorChoiceException("Color choice has conflicts in color:  " + i + 1 + " : " + Arrays.toString(colorchoices));
                }
                if (PaintOptionEnm.GLOSS.type.equals(paintOption)) {
                    glosses.put(i, PaintOptionEnm.GLOSS);
                }
                if (PaintOptionEnm.MATTE.type.equals(paintOption)) {
                    mattes.put(i, PaintOptionEnm.MATTE);
                }
            }
        }
        if (mattes.size() > 1)
            throw new ColorChoiceException("Matte color choices cannot be more than 1: " + Arrays.toString(colorchoices));

    }

    public int getChoiceNum() {
        return this.mattes.size() + this.glosses.size();
    }

    public ColorPaintPair getSingleChoice() {
        if (getChoiceNum() == 1) {
            Map<Integer, PaintOptionEnm> lastPair = mattes.size() > 0 ? mattes : glosses;
            int index = -1;
            PaintOptionEnm paint = null;
            for (Map.Entry<Integer, PaintOptionEnm> entry : lastPair.entrySet()) {
                index = entry.getKey();
                paint = entry.getValue();
            }
            if (index != -1 && paint != null) {
                return new ColorPaintPair(index, paint);
            }
        }
        return null;
    }

    public List<ColorPaintPair> getGlossColors() {
        List<ColorPaintPair> list = new ArrayList<>();
        for (Map.Entry<Integer, PaintOptionEnm> entry : this.glosses.entrySet()) {
            list.add(new ColorPaintPair(entry.getKey(), entry.getValue()));
        }
        return list;
    }

    public void setGlosses(List<ColorPaintPair> gls) {
        glosses.clear();
        for (ColorPaintPair pair : gls) {
            glosses.put(pair.getIndex(), pair.getPaint());
        }
    }

//    public List<ColorPaintPair> getMatteColors(){
//        List<ColorPaintPair> list = new ArrayList<>();
//        for(Map.Entry<Integer, PaintOptionEnm> entry: this.mattes.entrySet()){
//            list.add(new ColorPaintPair(entry.getKey(), entry.getValue()));
//        }
//        return list;
//    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < colorchoices.length; i++) {
            if (colorchoices[i] != null) {
                builder.append(i + 1).append(" ").append(colorchoices[i]);
                if (i + 1 != colorchoices.length)
                    builder.append(" ");
            }
        }
        return builder.toString();
    }

    static public String generateChoiceOutput(String[] colorchoices) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < colorchoices.length; i++) {
            if (colorchoices[i] != null) {
                builder.append(colorchoices[i]);
                if (i + 1 != colorchoices.length)
                    builder.append(" ");
            }
        }
        return builder.toString();
    }


}
