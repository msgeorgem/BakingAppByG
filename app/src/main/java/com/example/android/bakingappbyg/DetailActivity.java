package com.example.android.bakingappbyg;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
    private int currentRecipeIDInt;
    private String currentRecipeName;
    private List<String[]> recipesIngredients;
    private List<String[]> currentRecipeIngredients = new ArrayList<>();
    private List<String[]> recipesSteps;
    private List<String[]> currentRecipeSteps = new ArrayList<>();

    private ArrayList<Recipes> recipesList = new ArrayList<>();
    private ArrayList<String> stepsList = new ArrayList<>();
    private RecyclerView stepsRecyclerView;
    private Recipes recipes;

    private Context context;
    private RecipeDetailAdapter.OnItemClickListener mListener;
    private RecipeDetailAdapter mAdapter = new RecipeDetailAdapter(stepsList, mListener);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        context = getApplicationContext();

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        recipes = JsonUtils1.parseRecipesJson(JsonString.strJson);

        currentRecipeID = intent.getStringExtra(MainActivity.EXTRA_ID);
        currentRecipeIDInt = Integer.parseInt(currentRecipeID);


        TextView mIngredientsTextView = findViewById(R.id.ingredients);
        Log.i("detail activity", currentRecipeID);

        recipesIngredients = recipes.getIngredients();


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


        // Find a reference to the {@link ListView} in the layout
        stepsRecyclerView = findViewById(R.id.list_steps);
        stepsList = getShortDescription(currentRecipeIDInt);
        stepsRecyclerView.setLayoutManager(new LinearLayoutManager(context));


        mListener = new RecipeDetailAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(String item) {

//                String currentRecipeID = item.getID();
//                String currentRecipeName = item.getName();
////                ArrayList<String[]> currentRecipeIngredients = item.getIngredients();
////                ArrayList<String[]> currentRecipeSteps = item.getSteps();
//
//                Intent intent1 = new Intent(getApplicationContext(), DetailActivity.class);
//
//                intent1.putExtra(EXTRA_ID, currentRecipeID);
//                intent1.putExtra(EXTRA_NAME, currentRecipeName);
////                intent1.putParcelableArrayListExtra(EXTRA_INGREDIENTS, currentRecipeIngredients);
////                intent1.putStringArrayListExtra(EXTRA_STEPS, currentRecipeSteps);
//
//                startActivity(intent1);
            }
        };
        mAdapter = new RecipeDetailAdapter(stepsList, mListener);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        stepsRecyclerView.setAdapter(mAdapter);
        stepsRecyclerView.setItemAnimator(new DefaultItemAnimator());


    }

    private ArrayList<String> getShortDescription(int currentRecipeIDInt) {
        ArrayList<String> shortDescription = new ArrayList<>();


        recipesSteps = recipes.getSteps();
        for (int i = 0; i < recipesSteps.size(); i++) {
            String[] elements = recipesSteps.get(i);
            int firstStepElement = Integer.parseInt(elements[0]);

            if (firstStepElement == currentRecipeIDInt) {

                String sDescription = elements[1];
                shortDescription.add(sDescription);
            }
        }
        return shortDescription;
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

}





