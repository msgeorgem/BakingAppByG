package com.example.android.bakingappbyg.Utils;

import android.util.Log;

import com.example.android.bakingappbyg.Recipes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUtils12 {

    public static Recipes parseRecipesJson(String json) {

        Recipes recipes = null;
        String mName;
        String mId;

        // Create an empty Lists that we can start adding Ingredients and Steps to
        ArrayList<Recipes> mRecipes = new ArrayList<>();
        ArrayList<String> mNames = new ArrayList<>();
        ArrayList<String[]> mIngredients = new ArrayList<String[]>();
        ArrayList<String[]> mSteps = new ArrayList<String[]>();

        try {

            JSONArray rootArray = new JSONArray(json);
            for (int i = 0; i < rootArray.length(); i++) {
                JSONObject recipe = rootArray.getJSONObject(i);
                mName = recipe.getString("name");
                mId = recipe.getString("id");
                Log.i("root", mId + " " + mName);
//                mNames.add(mName);


                JSONArray ingredientsArray = recipe.getJSONArray("ingredients");

                for (int n = 0; n < ingredientsArray.length(); n++) {
                    JSONObject ingredient = ingredientsArray.getJSONObject(n);
                    String mQuantity = ingredient.getString("quantity");
                    String mMeasure = ingredient.getString("measure");
                    String mIngredient = ingredient.getString("ingredient");
                    mIngredients.add(new String[]{mId});
                    mIngredients.add(new String[]{mQuantity});
                    mIngredients.add(new String[]{mMeasure});
                    mIngredients.add(new String[]{mIngredient});
//                    Log.i("ingredients" + " " + n, mId +" " + mQuantity + " " + mMeasure + " " + mIngredient);
                    Log.i("ingredients", String.valueOf(mIngredients));
                }

                JSONArray stepsArray = recipe.getJSONArray("steps");

                for (int m = 0; m < stepsArray.length(); m++) {
                    JSONObject step = stepsArray.getJSONObject(m);
                    String mShortDescription = step.getString("shortDescription");
                    String mDescription = step.getString("description");
                    String mVideoURL = step.getString("videoURL");
                    mSteps.add(new String[]{mId});
                    mSteps.add(new String[]{mShortDescription});
                    mSteps.add(new String[]{mDescription});
                    mSteps.add(new String[]{mVideoURL});
//                    Log.i("steps" + " " + m, mId +" " + mShortDescription + " " + mDescription + " " + mVideoURL);
                    Log.i("ingredients", String.valueOf(mSteps));
                }

                recipes = new Recipes(mId, mName, mIngredients, mSteps);

            }

        } catch (JSONException e) {
            e.printStackTrace();

        }

        return recipes;
    }
}