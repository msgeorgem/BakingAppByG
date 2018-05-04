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

                    String[] ingredients = new String[4];
                    ingredients[0] = mId;
                    ingredients[1] = ingredient.getString("quantity");
                    ingredients[2] = ingredient.getString("measure");
                    ingredients[3] = ingredient.getString("ingredient");
                    mIngredients.add(ingredients);

//                    Log.i("ingredients" + " " + n, mId +" " + mQuantity + " " + mMeasure + " " + mIngredient);
                    Log.i("ingredients", String.valueOf(mIngredients));
                }

                JSONArray stepsArray = recipe.getJSONArray("steps");

                for (int m = 0; m < stepsArray.length(); m++) {
                    JSONObject step = stepsArray.getJSONObject(m);

                    String[] steps = new String[4];
                    steps[0] = mId;
                    steps[1] = step.getString("shortDescription");
                    steps[2] = step.getString("description");
                    steps[3] = step.getString("videoURL");
                    mSteps.add(steps);
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