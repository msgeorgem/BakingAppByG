package com.example.android.bakingappbyg.Utils;

import android.util.Log;

import com.example.android.bakingappbyg.Recipes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static ArrayList<Recipes> parseRecipesJson(String json) {

        Recipes recipes = null;
        String mName = null;

        // Create an empty Lists that we can start adding Ingredients and Steps to
        ArrayList<Recipes> mRecipes = new ArrayList<>();
        ArrayList<String> mNames = new ArrayList<>();
        List<String> mIngredients = new ArrayList<>();
        List<String> mSteps = new ArrayList<>();

        try {

            JSONArray rootArray = new JSONArray(json);
            for (int i=0; i < rootArray.length(); i++ ) {
                JSONObject baked = rootArray.getJSONObject(i);
                mName = baked.getString("name");
                Log.i("root", mName);
//                mNames.add(mName);


                JSONArray ingredientsArray = baked.getJSONArray("ingredients");

                for (int n = 0; n < ingredientsArray.length(); n++) {
                    JSONObject ingredient = ingredientsArray.getJSONObject(n);
                    String mQuantity = ingredient.getString("quantity");
                    String mMeasure = ingredient.getString("measure");
                    String mIngredient = ingredient.getString("ingredient");
                    mIngredients.add(mMeasure);
                    mIngredients.add(mQuantity);
                    mIngredients.add(mIngredient);
                    Log.i("ingredients" + " " + n, mMeasure + " " + mQuantity + " " + mIngredient);

                }

                JSONArray stepsArray = baked.getJSONArray("steps");

                for (int m = 0; m < stepsArray.length(); m++) {
                    JSONObject step = stepsArray.getJSONObject(m);
                    String mShortDescription = step.getString("shortDescription");
                    String mDescription = step.getString("description");
                    String mVideoURL = step.getString("videoURL");
                    mSteps.add(mShortDescription);
                    mSteps.add(mDescription);
                    mSteps.add(mVideoURL);
                    Log.i("steps" + " " + m, mShortDescription + " " + mDescription + " " + mVideoURL);

                }

                mRecipes.add(new Recipes(mName, mIngredients, mSteps));

            }

        } catch (JSONException e) {
            e.printStackTrace();

        }

        return mRecipes;
    }
}