package com.example.android.bakingappbyg;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.bakingappbyg.Utils.JsonString;
import com.example.android.bakingappbyg.Utils.JsonUtils12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    private String currentRecipeID;
    private String currentRecipeName;
    private List<String[]> currentRecipeIngredients = new ArrayList<>();
    private List<String> currentRecipeSteps;
    private ArrayList<Recipes> recipesList = new ArrayList<>();
    public static final String LOG_TAG = DetailActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        Recipes recipesList = JsonUtils12.parseRecipesJson(JsonString.strJson);
        currentRecipeID = intent.getStringExtra(MainActivity.EXTRA_ID);
        TextView mIngredientsTextView = findViewById(R.id.ingredients);


        currentRecipeIngredients = recipesList.getIngredients();
        for (int i = 0; i < currentRecipeIngredients.size(); i++) {
            String[] elements = currentRecipeIngredients.get(i);
            String firstElement = elements[0];
            String threeElements = "";
            StringBuilder builder1 = new StringBuilder();
            if (firstElement.equals(currentRecipeID)) {
                String secondElement = elements[1];
                String thirdElement = elements[2];
                String fourthElement = elements[3];
                Log.i("ingredients", Arrays.toString(elements));
                threeElements = secondElement + " " + thirdElement + " " + fourthElement;
            }
            builder1.append(threeElements).append("\n");
            mIngredientsTextView.setText(builder1);
        }
    }


    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

}





