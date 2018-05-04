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
//        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
//        if (position == DEFAULT_POSITION) {
//            // EXTRA_POSITION not found in intent
//            closeOnError();
//            return;
//        }
        Recipes recipesList = JsonUtils1.parseRecipesJson(JsonString.strJson);

        currentRecipeID = intent.getStringExtra(MainActivity.EXTRA_ID);
        TextView mIngredientsTextView = findViewById(R.id.ingredients);
        Log.i("detail activity", currentRecipeID);

        recipesIngredients = recipesList.getIngredients();


        String[] ingredientArr = new String[3];

        for (int i = 0; i < recipesIngredients.size(); i++) {
            String[] elements = recipesIngredients.get(i);
            String firstElementRecipe = elements[0];

            if (firstElementRecipe.equals(currentRecipeID)) {
                ingredientArr[0] = elements[1];
                ingredientArr[1] = elements[2];
                ingredientArr[2] = elements[3];
                currentRecipeIngredients.add(ingredientArr);
                Log.i("current_array", Arrays.toString(ingredientArr));
            }
        }


        int size = currentRecipeIngredients.size();
        Log.i("current_array_size", String.valueOf(size));


        StringBuilder builder2 = new StringBuilder();
        for (String[] details : currentRecipeIngredients) {
            builder2.append("* ").append(Arrays.toString(details)).append("\n");
        }
        mIngredientsTextView.setText(builder2.toString());
    }


    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

}





