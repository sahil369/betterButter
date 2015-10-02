package greatfoodstartshere.betterbutter;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import greatfoodstartshere.betterbutter.adapters.RecipeAdapter;
import greatfoodstartshere.betterbutter.models.Recipe;


public class BrowseRecipe extends Fragment {

    ArrayList<Recipe> browseList;

    private Toolbar mToolbar;
    Button filter, neww, popular;
    View newBar, popularBar;
    RecyclerView rv_recipes;
    LinearLayoutManager llm_recipes;
    RecipeAdapter recipeAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.browse_recipes, container, false);

        browseList = getArguments().getParcelableArrayList("list");

        Initialise(v);
        InitialiseListeners();

        //setSupportActionBar(mToolbar);

        rv_recipes.setLayoutManager(llm_recipes);
        rv_recipes.setHasFixedSize(true);

        rv_recipes.setAdapter(recipeAdapter);

        return v;
    }


    public void Initialise(View v){
        mToolbar = (Toolbar) v.findViewById(R.id.toolbar);
        filter = (Button) v.findViewById(R.id.filter);
        neww = (Button) v.findViewById(R.id.neww);
        popular = (Button) v.findViewById(R.id.popular);
        newBar = (View) v.findViewById(R.id.new_bar);
        popularBar = (View) v.findViewById(R.id.popular_bar);

        popularBar.setVisibility(View.GONE);

        rv_recipes = (RecyclerView) v.findViewById(R.id.rv_recipe);
        llm_recipes = new LinearLayoutManager(getActivity());
        recipeAdapter = new RecipeAdapter(browseList);
    }


    public void InitialiseListeners(){
        /*filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/

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
    public void onPause() {
            super.onPause();
        }
}
