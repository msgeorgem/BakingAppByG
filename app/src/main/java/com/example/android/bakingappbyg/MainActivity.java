package com.example.android.bakingappbyg;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.bakingappbyg.Utils.JsonString;
import com.example.android.bakingappbyg.Utils.JsonUtils;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Intent intent = getIntent();
//        if (intent == null) {
//            closeOnError();
//        }
//
//        int position = 0;
//        if (intent != null) {
//            position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
//        }
//        if (position == DEFAULT_POSITION) {
//            // EXTRA_POSITION not found in intent
//            closeOnError();
//            return;
//        }


        ArrayList<Recipes> recipes = JsonUtils.parseRecipesJson(JsonString.strJson);
        Log.i("json", String.valueOf(recipes));

        if (recipes == null) {
            // Bakes data unavailable
            closeOnError();
            return;
        }


        ArrayAdapter<Recipes> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, recipes);

        // Simplification: Using a ListView instead of a RecyclerView
        ListView listView = findViewById(R.id.bakes_listview);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//                launchDetailActivity(position);
            }
        });

        Bundle extras = getIntent().getExtras();
        if( extras != null && extras.containsKey("test")){
            Log.d("FCM Notification", "Contains" + extras.get("test"));
        }
    }
    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

//    private void launchDetailActivity(int position) {
//        Intent intent = new Intent(this, DetailActivity.class);
//        intent.putExtra(DetailActivity.EXTRA_POSITION, position);
//        startActivity(intent);
//    }
}
