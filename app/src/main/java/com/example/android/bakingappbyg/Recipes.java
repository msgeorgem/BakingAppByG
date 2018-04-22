package com.example.android.bakingappbyg;

import java.util.List;

public class Recipes {

    private String mName;
    private List<String> mIngredients = null;
    private List<String> mSteps = null;
    private String mQuantity;
    private String mMeasure;

    private String mShortDescription;
    private String mDescription;
    private String mVideoURL;


    /**
     * No args constructor for use in serialization
     */
    public Recipes() {
    }
    public Recipes(String name, List<String> ingredients, List<String> steps) {

        this.mName = name;
        this.mIngredients = ingredients;
        this.mSteps = steps;


    }

    public String getName() {
        return mName;
    }
    public List<String> getIngredients() {
        return mIngredients;
    }
    public List<String> getSteps() {
        return mSteps;
    }



//    public String getQuantity() {
//        return mQuantity;
//    }
//    public String getMeasure() {
//        return mMeasure;
//    }
//    public String getIngredient() {
//        return mIngredient;
//    }
//    public String getShortDescription() {
//        return mShortDescription;
//    }
//    public String getDescription() {
//        return mDescription;
//    }
//    public String getVideoURL() {
//        return mVideoURL;
//    }

}
