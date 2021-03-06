package com.example.android.bakingappbyg;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.android.bakingappbyg.Utils.JsonString;
import com.example.android.bakingappbyg.Utils.JsonUtils;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_ID = "EXTRA_ID";
    public static final String EXTRA_NAME = "EXTRA_NAME";


    public static final String LOG_TAG = MainActivity.class.getName();
    public static Context context;
    private ArrayList<Recipes> recipesList = new ArrayList<>();
    private RecyclerView recipesRecyclerView;
    /**
     * Adapter for the list of recipes
     */
    private RecipeMainAdapter.OnItemClickListener mListener;
    private RecipeMainAdapter mAdapter = new RecipeMainAdapter(recipesList, mListener);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();

        // Find a reference to the {@link ListView} in the layout
        recipesRecyclerView = findViewById(R.id.list_item);
        recipesList = JsonUtils.parseRecipesJson(JsonString.strJson);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            recipesRecyclerView.setLayoutManager(new GridLayoutManager(context, 1));
        } else {
            recipesRecyclerView.setLayoutManager(new GridLayoutManager(context, 2));
        }
        mListener = new RecipeMainAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(Recipes item) {

                String currentRecipeID = item.getID();
                String currentRecipeName = item.getName();

                Intent intent1 = new Intent(getApplicationContext(), DetailActivity.class);

                intent1.putExtra(EXTRA_ID, currentRecipeID);
                intent1.putExtra(EXTRA_NAME, currentRecipeName);

                startActivity(intent1);
            }
        };
        mAdapter = new RecipeMainAdapter(recipesList, mListener);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        recipesRecyclerView.setAdapter(mAdapter);
        recipesRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }


}
