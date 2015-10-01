package greatfoodstartshere.betterbutter;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import greatfoodstartshere.betterbutter.adapters.CookBookAdapter;
import greatfoodstartshere.betterbutter.adapters.RecipeAdapter;
import greatfoodstartshere.betterbutter.models.CookBook;
import greatfoodstartshere.betterbutter.models.Recipe;
import greatfoodstartshere.betterbutter.models.User;
import greatfoodstartshere.betterbutter.volley.AppController;

public class Browse extends AppCompatActivity {

    String TAG = "Browse Activity";

    ArrayList<Recipe> browseList;

    private Toolbar mToolbar;
    Button filter, neww, popular;
    View newBar, popularBar;
    RecyclerView rv_recipes;
    LinearLayoutManager llm_recipes;
    RecipeAdapter recipeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.browse);

        Bundle bdl = getIntent().getExtras();
        browseList = bdl.getParcelableArrayList("List");

        Initialise();
        InitialiseListeners();

        setSupportActionBar(mToolbar);

        rv_recipes.setLayoutManager(llm_recipes);
        rv_recipes.setHasFixedSize(true);
        rv_recipes.setAdapter(recipeAdapter);
    }


    public void Initialise(){
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        filter = (Button) findViewById(R.id.filter);
        neww = (Button) findViewById(R.id.neww);
        popular = (Button) findViewById(R.id.popular);
        newBar = (View) findViewById(R.id.new_bar);
        popularBar = (View) findViewById(R.id.popular_bar);

        popularBar.setVisibility(View.GONE);

        rv_recipes = (RecyclerView)findViewById(R.id.rv_recipe);
        llm_recipes = new LinearLayoutManager(this);
        recipeAdapter = new RecipeAdapter(browseList);

    }


    public void InitialiseListeners(){
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        neww.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popularBar.setVisibility(View.GONE);
                newBar.setVisibility(View.VISIBLE);
            }
        });

        popular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newBar.setVisibility(View.GONE);
                popularBar.setVisibility(View.VISIBLE);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_browse, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
