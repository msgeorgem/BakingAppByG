package com.example.android.bakingappbyg;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.bakingappbyg.Utils.JsonString;
import com.example.android.bakingappbyg.Utils.JsonUtils1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    public static final String LOG_TAG = DetailActivity.class.getSimpleName();
    private static final int DEFAULT_POSITION = -1;
    private String currentRecipeID;
    private String currentRecipeName;
    private List<String[]> recipesIngredients;
    private List<String[]> currentRecipeIngredients = new ArrayList<>();
    private List<String> currentRecipeSteps;
    private ArrayList<Recipes> recipesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        Recipes recipesList = JsonUtils1.parseRecipesJson(JsonString.strJson);

        currentRecipeID = intent.getStringExtra(MainActivity.EXTRA_ID);
        TextView mIngredientsTextView = findViewById(R.id.ingredients);
        Log.i("detail activity", currentRecipeID);

        recipesIngredients = recipesList.getIngredients();


        for (int i = 0; i < recipesIngredients.size(); i++) {
            String[] elements = recipesIngredients.get(i);
            String firstElementRecipe = elements[0];

            if (firstElementRecipe.equals(currentRecipeID)) {
                String[] ingredientArr = new String[3];
                ingredientArr[0] = elements[1];
                ingredientArr[1] = elements[2];
                ingredientArr[2] = elements[3];
                currentRecipeIngredients.add(ingredientArr);
            }
        }

        StringBuilder builder2 = new StringBuilder();
        for (String[] details : currentRecipeIngredients) {

            String formattedString = Arrays.toString(details)
                    .replace(",", "")  //remove the commas
                    .replace("[", "")  //remove the right bracket
                    .replace("]", "")  //remove the left bracket
                    .trim();
            Log.i("current_detail", formattedString);
            builder2.append("* ").append(formattedString).append("\n");
        }


        mIngredientsTextView.setText(builder2.toString());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

}





