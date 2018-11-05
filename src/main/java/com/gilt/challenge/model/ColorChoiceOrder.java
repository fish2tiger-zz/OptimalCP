package com.gilt.challenge.model;

import java.util.*;
import java.util.List;

/**
 * ColorChoiceOrder represents the list of customer color paint preferences.
 * From each ColorChoiceOrder instance, it can be validated to generate the most
 * economic paint plan.
 */
public class ColorChoiceOrder{
    private List<ColorChoice> colorchoices = new ArrayList<>();
    private Integer colorSize;
    public ColorChoiceOrder(int colorSize, List<ColorChoice> choices){
        colorchoices.addAll(choices);
        this.colorSize = colorSize;
    }

    public String toString(){
        StringBuilder builder = new StringBuilder();

        for(Iterator<ColorChoice> iterator = colorchoices.iterator(); iterator.hasNext(); ){
            builder.append(iterator.next().toString());
            if(iterator.hasNext())
                builder.append("\n");
        }
        return builder.toString();
    }

    /**
     * The essential piece of calculating the paint plan to satisfy each
     * customer's favoured choices. This is the brute force approach to look
     * through all the customer's choices and make sure the final paint plan satisfy
     * all the customer choices. Otherwise, no solution exists.
     *
     * @return optimized paint plan
     */
    public String getPaintPlan(){
        String []finalPaintPlan = new String[colorSize];

        // update the list to remove the 'Gs'
        while(!colorchoices.isEmpty()){
            boolean hasConflicts = false;

            for(Iterator<ColorChoice> colorsItr = colorchoices.iterator(); colorsItr.hasNext();) {
                ColorChoice choice = colorsItr.next();

                // no matched color paint left,
                // as all the choices from one customer are removed because of conflicts
                if (choice.getChoiceNum() == 0)
                    return "No solution exists";

                // update the final paint option when it has to be matched
                if (choice.getChoiceNum() == 1) {
                    ColorPaintPair singleChoice = choice.getSingleChoice();
                    int index = singleChoice.getIndex();
                    if (finalPaintPlan[index] == null) {
                        finalPaintPlan[index] = singleChoice.getPaint().type;
                        // possible conflicts exist, because there is update in finalPaintPlan,
                        hasConflicts = true;
                    } else {
                        if (!finalPaintPlan[index].equals(singleChoice.getPaint().type))
                            return "No solution exists";
                    }
                    colorsItr.remove();
                }
                // remove all the 'G' in color choice which are painted as 'M'
                else {
                    List<ColorPaintPair> glosses = choice.getGlossColors();

                    // remove all the gloss colors which are painted matte already
                    for (Iterator<ColorPaintPair> iterator = glosses.iterator(); iterator.hasNext(); ) {
                        ColorPaintPair gloss= iterator.next();
                        int index = gloss.getIndex();
                        if (PaintOptionEnm.MATTE.type.equals(finalPaintPlan[index])) {
                            hasConflicts = true;
                            iterator.remove();
                        }
                    }
                    // update the glosses
                    choice.setGlosses(glosses);
                }
            }

            if(!hasConflicts){
                break; // break the matching process because no conflict any more
            }
        }
        for(int i= 0; i<finalPaintPlan.length; i++){
            if( finalPaintPlan[i] == null ){
                finalPaintPlan[i] = PaintOptionEnm.GLOSS.type;
            }
        }
        return ColorChoice.generateChoiceOutput(finalPaintPlan);
    }

}
